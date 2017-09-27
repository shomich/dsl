package ru.sbt.i9n.o11n.fluent.impl.state;

/**
 * Created by shomich on 26.09.17.
 */
public abstract class HttpCallState<T, R> extends BaseState<T, R> {
    public HttpCallState(String name, String nextState) {
        super(name, nextState);
    }

}
