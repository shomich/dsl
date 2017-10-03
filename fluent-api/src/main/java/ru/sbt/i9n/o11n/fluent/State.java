package ru.sbt.i9n.o11n.fluent;

/**
 * Created by SIGMA\sbt-galiullin-ts on 10.08.17.
 * Marker interface
 */
public interface State<T, R> {

    void execute();

    void setIn(MessageAndContext<T> in);

    MessageAndContext<R> out();

}
