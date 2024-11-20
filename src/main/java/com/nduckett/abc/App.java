package com.nduckett.abc;

import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import io.sentry.Sentry;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class App extends Application<Config> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstrap.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)));

        // Guice DI support for Dropwizard
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig()
                .build());

        // Swagger configuration
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Config configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });
    }


    @Override
    public void run(Config config, Environment environment) throws Exception {
        Sentry.init(options -> {
            options.setEnabled(config.getSentryDsn() != null);
            options.setDsn(config.getSentryDsn());
            options.setEnvironment(config.getEnvironment());
        });
    }
}
