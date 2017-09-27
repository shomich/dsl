package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.StateDecider;

/**
 * Created by timmy on 20/08/17.
 */
public class RouterState<T> extends BaseState<T,T> {

    private final StateDecider stateDecider;
    private String nextState;

    public RouterState(String name, StateDecider stateDecider) {
        super(name, name);
        this.stateDecider = stateDecider;
    }

    @Override
    public void execute() {
        nextState = stateDecider.next(in());
        System.out.println("execute " + name());
    }

    @Override
    public String nextState() {
        return nextState;
    }

    @Override
    public MessageAndContext<T> out() {
        return in();
    }

}
