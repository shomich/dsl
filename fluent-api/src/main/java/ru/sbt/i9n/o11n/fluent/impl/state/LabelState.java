package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

/**
 * Created by SIGMA\sbt-galiullin-ts on 30.09.17.
 */
public class LabelState extends BaseState {
    private final String name;

    public LabelState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        System.out.println("Execute label " + name);
    }

    @Override
    public MessageAndContext out() {
        return in();
    }
}
