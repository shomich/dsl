package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.State;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public class EmptyState implements State {
    @Override
    public void execute() {

    }

    @Override
    public void setIn(MessageAndContext in) {

    }

    @Override
    public MessageAndContext out() {
        return null;
    }


    @Override
    public String name() {
        return null;
    }


    @Override
    public String nextState() {
        return null;
    }
}
