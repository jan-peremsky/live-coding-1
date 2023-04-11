package com.wag.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "live-coding")
public interface LiveCodingConfig {

    @WithDefault("4")
    int parallelism();

}