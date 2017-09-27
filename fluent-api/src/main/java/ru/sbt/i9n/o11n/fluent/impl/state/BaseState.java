package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.Context;
import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.State;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public abstract class BaseState<T, R> implements State<T, R> {

    private final String name;
    private final String nextState;
    private MessageAndContext<T> in;
    private Context context;

    public BaseState(String name, String nextState) {
        this.name = name;
        this.nextState = nextState;
    }

    @Override
    public void setIn(MessageAndContext<T> in) {
        this.in = in;
    }

    protected MessageAndContext<T> in() {
        return in;
    }

    @Override
    public String nextState() {
        return nextState;
    }

    @Override
    public String name() {
        return name;
    }
}
