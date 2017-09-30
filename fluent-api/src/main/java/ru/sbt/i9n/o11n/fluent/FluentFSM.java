package ru.sbt.i9n.o11n.fluent;

import ru.sbt.i9n.o11n.fluent.impl.MapContext;
import ru.sbt.i9n.o11n.fluent.impl.state.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by timmy on 17/09/17.
 */
public class FluentFSM {


    private final java.util.Map<String, State> stateMap = new HashMap<>();
    private final State start;

    private FluentFSM(List<State> states) {
        for (State state : states) {
            stateMap.put(state.name(), state);
        }
        start = stateMap.get("start");
    }

    public MessageAndContext execute(MessageAndContext input) {
        State current = start;
        State previous = current;
        MessageAndContext out = input;
        Context context = new MapContext();
        while (current != null
                && !(previous instanceof FinishState)) {
            current.setIn(out);
            current.execute();
            out = current.out();
            previous = current;
            current = stateMap.get(current.nextState());
        }
        return out;
    }

    public static class FSMBaseState {
        private final List<State> states = new ArrayList<>();

        public FSMBaseState start(String nextState) {
            states.add(new StartState<>(nextState));
            return this;
        }

        public FSMBaseState map(String name, String next, Map mapper) {
            states.add(new MapState<>(name, next, mapper));
            return this;
        }

        public FSMBaseState route(String name, StateDecider decider) {
            states.add(new RouterState<>(name, decider));
            return this;
        }

        public FSMBaseState httpCall(String name, String next, String uri, HttpGetState.CallbackHandler callbackHandler) {
            states.add(new HttpGetState(name, next, uri, callbackHandler));
            return this;
        }

        public FSMBaseState finish() {
            states.add(new FinishState());
            return this;
        }

        public FluentFSM build() {
            return new FluentFSM(states);
        }
    }

}
