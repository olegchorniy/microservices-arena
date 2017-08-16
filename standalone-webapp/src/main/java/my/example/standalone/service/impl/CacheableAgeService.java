package my.example.standalone.service.impl;

import my.example.standalone.conversion.AgeEntity;
import my.example.standalone.service.AgeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheableAgeService implements AgeService {

    @Override
    @Cacheable("names")
    public AgeEntity getAge(String name) {

        System.out.println("getAge for [" + name + "] is called");

        switch (name) {
            case "XXX":
                sleep(1000);
                return new AgeEntity(22);

            case "ZZZ":
                sleep(2000);
                return new AgeEntity(23);

            case "BigDelay":
                sleep(5 * 60 * 1000);
                return new AgeEntity(100_000);
        }

        return null;
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
