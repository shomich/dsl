package ru.sbt.i9n.o11n.fluent;

/**
 * Created by SIGMA\sbt-galiullin-ts on 30.09.17.
 */

import ru.sbt.i9n.o11n.fluent.impl.state.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Created by timmy on 17/09/17.
 */
public class FluentFSM {


    private final java.util.Map<String, Integer> stateMap = new HashMap<>();
    private final List<State> states;

    protected FluentFSM(List<State> states) {
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

    public static class FSMBaseStateLinear<T extends FSMBaseStateLinear<T>> {
        private final List<State> states = new ArrayList<>();

        protected List<State> getStates() {
            return states;
        }

        public T start() {
            states.add(new StartState<>());
            return (T)this;
        }

        public <Q, R> T map(Map<Q, R> mapper) {
            states.add(new MapState<>(mapper));
            return (T)this;
        }

        public <Q> T route(StateDecider<Q> decider) {
            states.add(new RouterState<>(decider));
            return (T)this;
        }

        public T httpCall(String uri, HttpGetState.CallbackHandler<String, String> callbackHandler) {
            states.add(new HttpGetState(uri, callbackHandler));
            return (T)this;
        }

        public T label(String name) {
            states.add(new LabelState(name));
            return (T)this;
        }

//        public <T, R> FSMBaseStateLinear mmtCall(Class<T> api, Function<T, R> function) {
//            states.add(new MMTCallState<>(api, function));
//            return this;
//        }

        public T finish() {
            states.add(new LabelState("finish"));
            states.add(new FinishState());
            return (T)this;
        }

        public FluentFSM build() {
            return new FluentFSM(states);
        }
    }
}
