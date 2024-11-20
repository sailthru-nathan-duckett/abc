package com.nduckett.abc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.core.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class Config extends Configuration {
    @JsonProperty
    private String environment;

    @JsonProperty
    private String sentryDsn;

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    public String getEnvironment() {
        return environment;
    }

    public String getSentryDsn() {
        return sentryDsn;
    }

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }

}
