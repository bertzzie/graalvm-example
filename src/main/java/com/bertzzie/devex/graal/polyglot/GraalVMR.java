package com.bertzzie.devex.graal.polyglot;

import org.graalvm.polyglot.Context;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class GraalVMR {

    private final ResourceLoader loader;

    public GraalVMR (ResourceLoader loader) {
        this.loader = loader;
    }

    public Mono<String> cloudPlot() {
        return Mono.create(sink -> {
            Context context = Context.newBuilder().allowAllAccess(true).build();
            Resource res = loader.getResource("classpath:static/cloudplot.r");

            String content = "";
            try (Reader reader = new InputStreamReader(res.getInputStream(), UTF_8)) {
                content = FileCopyUtils.copyToString(reader);
            } catch (IOException ex) {
                sink.error(new UncheckedIOException(ex));
            }

            sink.success(context.eval("R", content).asString());
        });
    }
}
