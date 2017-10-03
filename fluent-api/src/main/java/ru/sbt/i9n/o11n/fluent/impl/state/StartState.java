package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

/**
 * Created by SIGMA\sbt-galiullin-ts on 09.08.17.
 */
public class StartState<T> extends BaseState<T,T> {

    @Override
    public void execute() {
        System.out.println("execute start");
    }


    @Override
    public MessageAndContext<T> out() {
        return in();
    }
}
