package com.example

import groovy.transform.CompileStatic
import io.micronaut.runtime.Micronaut
import io.micronaut.security.rules.IpPatternsRule

@CompileStatic
class Application {

    static void main(String[] args) {
        Micronaut.run(Application, args);
    }
}
