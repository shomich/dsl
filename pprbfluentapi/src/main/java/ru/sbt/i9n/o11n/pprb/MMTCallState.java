package ru.sbt.i9n.o11n.pprb;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.impl.state.BaseState;

import java.util.function.Function;

/**
 * Created by timmy on 12/10/17.
 */
public class MMTCallState<T, R> extends BaseState<T, R> {

    private final Class<T> mmtApi;
    private final Function<T, R> methodCall;
    private R result;

    public MMTCallState(Class<T> api, Function<T, R> function) {
        mmtApi = api;
        methodCall = function;
    }

    @Override
    public void execute() {
        T apiImpl = new MMTApiFactory().getApi(mmtApi);
        methodCall.apply(apiImpl);
    }

    @Override
    public MessageAndContext<R> out() {
        return new MessageAndContext<R>(result, in().getContext());
    }
}
