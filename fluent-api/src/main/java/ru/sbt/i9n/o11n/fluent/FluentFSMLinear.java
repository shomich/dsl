package ru.sbt.i9n.o11n.fluent;

/**
 * Created by SIGMA\sbt-galiullin-ts on 30.09.17.
 */

import ru.sbt.i9n.o11n.fluent.impl.MapContext;
import ru.sbt.i9n.o11n.fluent.impl.state.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by timmy on 17/09/17.
 */
public class FluentFSMLinear {


    private final java.util.Map<String, State> stateMap = new HashMap<>();
    private final State start;
    private final List<State> states;

    private FluentFSMLinear(List<State> states) {
        this.states = states;
        for (State state : states) {
            stateMap.put(state.name(), state);
        }
        start = stateMap.get("start");
    }

    public MessageAndContext execute(MessageAndContext input) {
        State current = states.get(0);
        State previous = current;
        MessageAndContext out = input;
        Context context = new MapContext();
        for (int i = 0; i < states.size(); i++) {
            if (!(previous instanceof FinishState)) {
                current = states.get(i);
                current.setIn(out);
                current.execute();
                out = current.out();
                previous = current;
            }
        }
        return out;
    }

    public static class FSMBaseStateLinear {
        private final List<State> states = new ArrayList<>();

        public FSMBaseStateLinear start() {
            states.add(new StartState<>(""));
            return this;
        }

        public FSMBaseStateLinear map(String name, Map mapper) {
            states.add(new MapState<>(name, "", mapper));
            return this;
        }

        public FSMBaseStateLinear route(String name, StateDecider decider) {
            states.add(new RouterState<>(name, decider));
            return this;
        }

        public FSMBaseStateLinear httpCall(String name, String uri, HttpGetState.CallbackHandler callbackHandler) {
            states.add(new HttpGetState(name, "", uri, callbackHandler));
            return this;
        }

        public FSMBaseStateLinear label(String name) {
            states.add(new LabelState(name, ""));
            return this;
        }

        public FSMBaseStateLinear finish() {
            states.add(new FinishState());
            return this;
        }

        public FluentFSMLinear build() {
            return new FluentFSMLinear(states);
        }
    }
}
