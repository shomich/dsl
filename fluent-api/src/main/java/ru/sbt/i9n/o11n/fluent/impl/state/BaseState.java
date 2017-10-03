package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.State;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public abstract class BaseState<T, R> implements State<T, R> {

    private MessageAndContext<T> in;

    public BaseState() {
    }

    @Override
    public void setIn(MessageAndContext<T> in) {
        this.in = in;
    }

    protected MessageAndContext<T> in() {
        return in;
    }

}
