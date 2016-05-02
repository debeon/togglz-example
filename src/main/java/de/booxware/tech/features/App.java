package de.booxware.tech.features;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.togglz.core.Feature;
import org.togglz.core.activation.GradualActivationStrategy;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.annotation.ActivationParameter;
import org.togglz.core.annotation.DefaultActivationStrategy;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.SingleUserProvider;
import org.togglz.core.user.UserProvider;

import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class App {

    public enum MyFeatures implements Feature {

        @EnabledByDefault
        @Label("First Feature")
        @DefaultActivationStrategy(id = "gradual", parameters = {@ActivationParameter(name = "percentage", value = "50")})
        FEATURE_GRADUAL,

        @EnabledByDefault
        @Label("Second Feature")
        @DefaultActivationStrategy(id = "even")
        FEATURE_EVEN;

        public boolean isActive() {
            return FeatureContext.getFeatureManager().isActive(this);
        }
    }

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(MyFeatures.class);
    }

    @Bean
    public UserProvider userProvider() {
        return new UserProvider() {
            @Override
            public FeatureUser getCurrentUser() {
                return new SimpleFeatureUser(UUID.randomUUID().toString());
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
