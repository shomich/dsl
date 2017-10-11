package ru.sbt.i9n.o11n.fluent;

/**
 * Created by SIGMA\sbt-galiullin-ts on 30.09.17.
 */

import ru.sbt.i9n.o11n.fluent.impl.state.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by timmy on 17/09/17.
 */
public class FluentFSM {


    private final java.util.Map<String, Integer> stateMap = new HashMap<>();
    private final List<State> states;

    private FluentFSM(List<State> states) {
        this.states = states;
        for (int i = 0; i < states.size(); i++) {
            State state = states.get(i);
            if (state instanceof LabelState) {
                stateMap.put(((LabelState) state).getName(), i);
            }
        }

    }

    public MessageAndContext execute(MessageAndContext input) {
        State current = states.get(0);
        State previous = current;
        MessageAndContext out = input;
        int i = 0;
        while (!(previous instanceof FinishState)) {
            if (previous instanceof RouterState) {
                i = stateMap.get(((RouterState) previous).nextState()) + 1; //plus 1 to skip label an go forward
            }
            current = states.get(i++);
            current.setIn(out);
            current.execute();
            out = current.out();
            previous = current;
        }

        return out;
    }

    public static class FSMBaseStateLinear {
        private final List<State> states = new ArrayList<>();

        public FSMBaseStateLinear start() {
            states.add(new StartState<>());
            return this;
        }

        public <T, R> FSMBaseStateLinear map(Map<T, R> mapper) {
            states.add(new MapState<>(mapper));
            return this;
        }

        public <T> FSMBaseStateLinear route(StateDecider<T> decider) {
            states.add(new RouterState<>(decider));
            return this;
        }

        public FSMBaseStateLinear httpCall(String uri, HttpGetState.CallbackHandler<String, String> callbackHandler) {
            states.add(new HttpGetState(uri, callbackHandler));
            return this;
        }

        public FSMBaseStateLinear label(String name) {
            states.add(new LabelState(name));
            return this;
        }

        public FSMBaseStateLinear mmtCall(Class<?> api, String methodName, Object... args) {
            states.add(new MMTCallState<>(api, methodName, args));
            return this;
        }

        public FSMBaseStateLinear finish() {
            states.add(new LabelState("finish"));
            states.add(new FinishState());
            return this;
        }

        public FluentFSM build() {
            return new FluentFSM(states);
        }
    }
}
