package de.booxware.tech.features;

import org.springframework.stereotype.Component;
import org.togglz.core.activation.Parameter;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import java.util.Calendar;

@Component
public class Activation implements ActivationStrategy {
    @Override
    public String getId() {
        return "even";
    }

    @Override
    public String getName() {
        return "even";
    }

    @Override
    public boolean isActive(FeatureState featureState, FeatureUser user) {
        return Calendar.getInstance().get(Calendar.MINUTE) % 2 == 0;
    }

    @Override
    public Parameter[] getParameters() {
        return new Parameter[0];
    }
}
