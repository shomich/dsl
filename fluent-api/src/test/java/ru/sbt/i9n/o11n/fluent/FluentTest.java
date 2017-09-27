package ru.sbt.i9n.o11n.fluent;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.i9n.o11n.fluent.impl.MapContext;

/**
 * Created by timmy on 17/09/17.
 */
public class FluentTest {

//    Object startMessage = "start";
//    ArrayList<State> states = new ArrayList<State>() {{
//        add(new StartState<>("map"));
//        add(new MapState<>("map", "route", new Mapper<String, String>() {
//            @Override
//            public String map(String in) {
//                context().put("next", "finish");
//                return in;
//            }
//
//        }));
//        add(new RouterState<>("route", new MessageDecider() {
//            @Override
//            public String next() {
//                return context().get("next");
//            }
//        }));
//        add(new FinishState());
//    }};
//
//    Object out = executeScript(states, startMessage);
//
//        Assert.assertEquals(startMessage, out);

    @Test
    public void contextDeciderTest() {
        FluentFSM fsm = new FluentFSM.FluentFSMBuilder()
                .start("map")
                .map("map", "route", new Map<String, String>() {
                    @Override
                    public MessageAndContext<String> map(MessageAndContext<String> in) {
                        Context context = in.getContext();
                        context.put("next", "finish");
                        return new MessageAndContext<>(in.getMessage(), context);
                    }
                })
                .route("route", new StateDecider<String>() {
                    @Override
                    public String next(MessageAndContext<String> messageAndContext) {
                        return messageAndContext.getContext().get("next");
                    }
                })
                .finish().build();

        MessageAndContext test = fsm.execute(new MessageAndContext<>("test", new MapContext()));
        Assert.assertEquals("test", test.getMessage());
    }
}
