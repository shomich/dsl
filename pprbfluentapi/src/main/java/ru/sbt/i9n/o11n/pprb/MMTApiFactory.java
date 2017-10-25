package ru.sbt.i9n.o11n.pprb;

/**
 * Created by SIGMA\sbt-galiullin-ts on 14.10.17.
 */
public class MMTApiFactory {

    public <T> T getApi(Class<T> api) {
        try {
            return api.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
