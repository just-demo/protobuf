package self.ed.proto;

import org.junit.Test;
import self.ed.proto.nested.LevelOne;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TryNestedTest {
    @Test
    public void testNested() {
        LevelOne.LevelTwo.LevelThree three = LevelOne.LevelTwo.LevelThree.newBuilder()
                .setValue("demo")
                .build();

        LevelOne.LevelTwo two = LevelOne.LevelTwo.newBuilder()
                .build();

        LevelOne one = LevelOne.newBuilder()
                .build();

        LevelOne composite = LevelOne.newBuilder()
                .setOne(one)
                .setTwo(two)
                .setThree(three)
                .build();

        assertTrue(composite.hasOne());
        assertTrue(composite.hasTwo());
        assertTrue(composite.hasThree());
        assertEquals("demo", composite.getThree().getValue());
    }
}
