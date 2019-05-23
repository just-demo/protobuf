package self.ed.util;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.randomizers.text.StringRandomizer;

public class RandomUtils {
    private static final EnhancedRandom ENHANCED_RANDOM = new EnhancedRandomBuilder()
            .randomize(String.class, StringRandomizer.aNewStringRandomizer(10, 10, 0))
            .collectionSizeRange(0, 0)
            .build();

    public static <T> T random(Class<T> type, String... excludedFields) {
        return ENHANCED_RANDOM.nextObject(type, excludedFields);
    }
}
