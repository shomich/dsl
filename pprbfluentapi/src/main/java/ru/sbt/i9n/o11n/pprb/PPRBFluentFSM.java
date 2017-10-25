package ru.sbt.i9n.o11n.pprb;

import ru.sbt.i9n.o11n.fluent.FluentFSM;
import ru.sbt.i9n.o11n.fluent.State;

import java.util.List;
import java.util.function.Function;

/**
 * Created by SIGMA\sbt-galiullin-ts on 16.10.17.
 */
public class PPRBFluentFSM extends FluentFSM {
    private PPRBFluentFSM(List<State> states) {
        super(states);
    }

    public static class PPRBFluentFSMBuilder extends FSMBaseStateLinear<PPRBFluentFSMBuilder> {

        private final MMTApiFactory mmtApiFactory;

        public PPRBFluentFSMBuilder() {
            mmtApiFactory = new MMTApiFactory();
        }

        public PPRBFluentFSMBuilder(MMTApiFactory mmtApiFactory) {
            this.mmtApiFactory = mmtApiFactory;
        }

        public <T, R> PPRBFluentFSMBuilder mmtCall(Class<T> api, Function<T, R> function) {
            getStates().add(new MMTCallState<>(mmtApiFactory, api, function));
            return this;
        }
    }
}
