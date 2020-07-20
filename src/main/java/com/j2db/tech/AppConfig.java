package com.j2db.tech;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Value("${STATSD_PREFIX}")
    private String applicationPrefix;
    private Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        String hostname = "localhost";
        String ip = "127.0.0.1";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("Could not determine hostname and IP address");
        }
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("host", hostname));
        tags.add(Tag.of("ip", ip));
        tags.add(Tag.of("application", applicationPrefix));
        return registry -> registry.config()
                .commonTags(tags);
    }


}
