package com.companyname.template.health;

import com.google.inject.Singleton;
import io.sentry.Sentry;
import ru.vyarus.dropwizard.guice.module.installer.feature.health.NamedHealthCheck;

@Singleton
public class BasicHealthCheck extends NamedHealthCheck {
    @Override
    public String getName() {
        return "status";
    }

    @Override
    protected Result check() {
        return Result.builder()
                .healthy()
                .withDetail("sentry_enabled", Sentry.isEnabled())
                .build();
    }
}
