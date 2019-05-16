package self.ed.mapper;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import self.ed.pojo.PojoSubTask;
import self.ed.pojo.PojoTask;
import self.ed.proto.ProtoSubTask;
import self.ed.proto.ProtoTask;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class TaskMapperTest {
    private static final EnhancedRandom RANDOM = new EnhancedRandomBuilder()
            .collectionSizeRange(0, 0)
            .build();

    @Test
    public void mapTask() {
        PojoTask input = random(PojoTask.class);
        ProtoTask inter = TaskMapper.INSTANCE.map(input);
        PojoTask output = TaskMapper.INSTANCE.map(inter);
        assertEquals(input, output);
    }

    @Test
    public void mapTask_WithSubTask() {
        PojoTask input = random(PojoTask.class);
        input.setSubTasks(singletonList(random(PojoSubTask.class)));
        ProtoTask inter = TaskMapper.INSTANCE.map(input);
        PojoTask output = TaskMapper.INSTANCE.map(inter);
        assertEquals(input, output);
    }

    @Test
    public void mapSubTask() {
        PojoSubTask input = random(PojoSubTask.class);
        ProtoSubTask inter = TaskMapper.INSTANCE.map(input);
        PojoSubTask output = TaskMapper.INSTANCE.map(inter);
        assertEquals(input, output);
    }

    private <T> T random(Class<T> type) {
        return RANDOM.nextObject(type);
    }
}