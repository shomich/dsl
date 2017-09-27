package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

/**
 * Created by SIGMA\sbt-galiullin-ts on 09.08.17.
 */
public class StartState<T> extends BaseState<T,T> {


    public StartState(String nextState) {
        super("start", nextState);
    }

    @Override
    public void execute() {
        System.out.println("execute " + name());
    }


    @Override
    public MessageAndContext<T> out() {
        return in();
    }
}
