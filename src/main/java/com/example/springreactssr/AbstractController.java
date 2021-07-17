package com.example.springreactssr;

import com.oracle.truffle.js.scriptengine.GraalJSScriptEngine;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public abstract class AbstractController {
    public String render(HttpServletRequest request, String scriptPath, ServerApi serverApi) throws ScriptException, IOException {
        GraalJSScriptEngine engine = this.initializeEngine(request, scriptPath, serverApi);
        StringJoiner joiner = new StringJoiner("");
        joiner.add("<div id=\"root\">");
        joiner.add(engine.eval("window.renderAppOnServer()").toString());
        joiner.add("</div>\n");
        joiner.add(String.format("<script src=\"%s\"></script>\n", scriptPath.replace("/static", "")));
        joiner.add("<script type=\"module\">function renderWhenAvailable() {window.renderApp ? window.renderApp() : window.setTimeout(renderWhenAvailable, 100)}renderWhenAvailable()</script>");
        return joiner.toString();
    }

    private String readFile(String path) throws IOException {
        InputStream in = getClass().getResource(path).openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        System.out.println(path + " loaded.");
        return reader.lines().collect(Collectors.joining());
    }

    private GraalJSScriptEngine initializeEngine(HttpServletRequest request, String scriptPath, ServerApi serverApi) throws ScriptException, IOException {
        GraalJSScriptEngine engine = GraalJSScriptEngine.create(
                null,
                Context
                        .newBuilder("js")
                        .allowHostAccess(HostAccess.ALL)
                        .allowHostClassLookup(s -> true)
        );

        engine.eval(String.format("window = { location: { hostname: 'localhost' }, isServer: true, requestUrl: \"%s\" }", request.getRequestURI()));

        if (!Objects.isNull(serverApi)) {
            engine.put("api", serverApi);
            engine.eval("window.api = api");
        }

        engine.eval("navigator = {}");
        engine.eval(this.readFile(scriptPath));
        return engine;
    }
}
