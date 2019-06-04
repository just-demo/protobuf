package demo.mapper;

import demo.pojo.PojoItem;
import demo.pojo.PojoSubItem;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import demo.proto.ProtoSubItem;
import demo.proto.ProtoItem;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class ItemMapperTest {
    private static final EnhancedRandom RANDOM = new EnhancedRandomBuilder()
            .collectionSizeRange(0, 0)
            .build();

    @Test
    public void mapItem() {
        PojoItem input = random(PojoItem.class);
        ProtoItem inter = ItemMapper.INSTANCE.map(input);
        PojoItem output = ItemMapper.INSTANCE.map(inter);
        assertEquals(input, output);
    }

    @Test
    public void mapItem_WithSubItem() {
        PojoItem input = random(PojoItem.class);
        input.setSubItems(singletonList(random(PojoSubItem.class)));
        ProtoItem inter = ItemMapper.INSTANCE.map(input);
        PojoItem output = ItemMapper.INSTANCE.map(inter);
        assertEquals(input, output);
    }

    @Test
    public void mapSubItem() {
        PojoSubItem input = random(PojoSubItem.class);
        ProtoSubItem inter = ItemMapper.INSTANCE.map(input);
        PojoSubItem output = ItemMapper.INSTANCE.map(inter);
        assertEquals(input, output);
    }

    private <T> T random(Class<T> type) {
        return RANDOM.nextObject(type);
    }
}