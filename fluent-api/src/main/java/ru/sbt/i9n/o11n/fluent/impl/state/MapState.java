package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.Map;
import ru.sbt.i9n.o11n.fluent.MessageAndContext;

/**
 * Created by SIGMA\sbt-galiullin-ts on 09.08.17.
 */
public class MapState<T, R> extends BaseState<T,R> {

    private final Map<T, R> mapper;
    private MessageAndContext<R> out;

    public MapState(Map<T, R> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void execute() {
        System.out.println("execute mapper");
        this.out = mapper.map(in());
    }

    @Override
    public MessageAndContext<R> out() {
        return this.out;
    }
}
