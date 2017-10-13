package ru.sbt.i9n.o11n.fluent.functions;

/**
 * Created by timmy on 13/10/17.
 */
public interface Functions {

    @FunctionalInterface
    interface QuadriFunction<T, P, U, S, R> {
        R apply(T t, P p, U u, S s);
    }

    @FunctionalInterface
    interface TriFunction<T, U, S, R> {
        R apply(T t, U u, S s);
    }

}
