package self.ed.proto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static self.ed.proto.TryOption.fieldOption;
import static self.ed.proto.TryOption.messageOption;

public class TryOptionTest {
    @Test
    public void testMessageOption() {
        assertEquals("Message option", TryOption.CustomOptionsMessage.getDescriptor().getOptions().getExtension(messageOption));
    }

    @Test
    public void testFieldOption() {
        assertEquals("Field option", TryOption.CustomOptionsMessage.getDescriptor().findFieldByNumber(1).getOptions().getExtension(fieldOption));
    }
}
