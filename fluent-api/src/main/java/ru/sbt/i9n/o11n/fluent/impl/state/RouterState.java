package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.StateDecider;

/**
 * Created by timmy on 20/08/17.
 */
public class RouterState<T> extends BaseState<T,T> {

    private final StateDecider<T> stateDecider;
    private String nextState;

    public RouterState(StateDecider<T> stateDecider) {
        this.stateDecider = stateDecider;
    }

    @Override
    public void execute() {
        nextState = stateDecider.next(in());
        System.out.println("execute router: " + nextState);
    }

    public String nextState() {
        return nextState;
    }

    @Override
    public MessageAndContext<T> out() {
        return in();
    }

}
