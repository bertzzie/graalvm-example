package com.bertzzie.devex.graal.controllers;

import com.bertzzie.devex.graal.polyglot.GraalVMJavascript;
import com.bertzzie.devex.graal.polyglot.GraalVMR;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GraalController {

    GraalVMR r;

    GraalVMJavascript js;

    public GraalController(GraalVMR r,
                           GraalVMJavascript js) {
        this.r = r;
        this.js = js;
    }

    @RequestMapping(
        path = "/graal/r",
        produces = MediaType.TEXT_HTML_VALUE
    )
    public Mono<String> r() {
        return r.cloudPlot();
    }

    @RequestMapping(
        path = "/graal/js",
        produces = MediaType.TEXT_PLAIN_VALUE
    )
    public Mono<String> js(@RequestParam(value = "c", defaultValue = "red") String color,
                           @RequestParam(value = "s", defaultValue = "1") Integer saturation) {
        return js.darkSaturate(color, saturation);
    }
}
