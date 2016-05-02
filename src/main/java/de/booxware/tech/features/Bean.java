package de.booxware.tech.features;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Bean {

    @Scheduled(fixedRate = 2000)
    public void setup() {
        if (App.MyFeatures.FEATURE_EVEN.isActive()) {
            System.out.println("even");
        } else {
            System.out.println("odd");
        }

        if (App.MyFeatures.FEATURE_GRADUAL.isActive()) {
            System.out.println("1");
        } else {
            System.out.println("2");
        }
    }
}
