package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

/**
 * Created by shomich on 26.09.17.
 */
public abstract class HttpCallState<T, R> extends BaseState<T, R> {
    public HttpCallState() {
        super();
    }

    public interface CallbackHandler<T, R> {
        MessageAndContext<R> onResponse(int code, String response, MessageAndContext<T> in);
    }
}
