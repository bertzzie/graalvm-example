package com.bertzzie.devex.graal.polyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GraalVMJavascript {

    private final Value context;
    private final ResourceLoader loader;

    public GraalVMJavascript (ResourceLoader loader) {
        this.loader = loader;

        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        Resource res = loader.getResource("classpath:static/chroma-service.js");

        String content = "";
        try (Reader reader = new InputStreamReader(res.getInputStream(), UTF_8)) {
            content = FileCopyUtils.copyToString(reader);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }

        context = ctx.eval("js", content);
    }

    public Mono<String> darkSaturate(String color, Integer saturation) {
        return Mono.create(sink -> {
            try {
                File source = this.loader.getResource("classpath:static/chroma.min.js").getFile();
                String result = this.context.getMember("darkenSaturateHex")
                        .execute(source, color, saturation)
                        .asString();
                sink.success(result);
            } catch (PolyglotException ex) {
                sink.error(ex);
            } catch (IOException ex) {
                sink.error(new IOException("Chroma.min.js not found", ex));
            }
        });
    }
}
