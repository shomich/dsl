package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

import java.util.function.Function;

/**
 * Created by timmy on 12/10/17.
 */
public class MMTCallState<T, R> extends BaseState<T, R> {

    private final Function<T, R> methodCall;
    private R result;

    public MMTCallState(Function<T, R> consumer) {
        methodCall = consumer;
    }

    @Override
    public void execute() {
        methodCall.apply(null);
    }

    @Override
    public MessageAndContext<R> out() {
        return new MessageAndContext<R>(result, in().getContext());
    }
}
