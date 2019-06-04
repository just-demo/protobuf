package demo.proto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TryOptionTest {
    @Test
    public void testMessageOption() {
        assertEquals("Message option", TryOption.CustomOptionsMessage.getDescriptor().getOptions().getExtension(TryOption.messageOption));
    }

    @Test
    public void testFieldOption() {
        assertEquals("Field option", TryOption.CustomOptionsMessage.getDescriptor().findFieldByNumber(1).getOptions().getExtension(TryOption.fieldOption));
    }
}
