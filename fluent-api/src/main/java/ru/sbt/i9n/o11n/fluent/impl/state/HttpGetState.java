package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by shomich on 28.09.17.
 */
public class HttpGetState extends HttpCallState<String, String> {
    private final String USER_AGENT = "Mozilla/5.0";
    private final String uri;
    private final CallbackHandler<String, String> callbackHandler;
    private MessageAndContext out;

    public HttpGetState(String uri, CallbackHandler<String, String> callbackHandler) {
        this.uri = uri;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public void execute() {
        try {
            CodeAndResp codeAndResp = sendGet(uri);
            out = callbackHandler.onResponse(codeAndResp.code, codeAndResp.resp, in());
        } catch (Exception e) {
            out = callbackHandler.onResponse(-1, e.toString(), in());
        }
    }

    @Override
    public MessageAndContext<String> out() {
        return out;
    }

    private CodeAndResp sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new CodeAndResp(responseCode, response.toString());
    }

    private static class CodeAndResp {
        private final int code;
        private final String resp;

        public CodeAndResp(int code, String resp) {
            this.code = code;
            this.resp = resp;
        }
    }

}
