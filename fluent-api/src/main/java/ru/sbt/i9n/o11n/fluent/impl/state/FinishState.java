package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public class FinishState<T> extends BaseState<T,T> {

    @Override
    public void execute() {
        System.out.println("execute finish");
    }

    @Override
    public MessageAndContext<T> out() {
        return in();
    }
}
