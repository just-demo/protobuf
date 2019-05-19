package self.ed.proto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static self.ed.proto.TryCustomOptions.fieldOption;
import static self.ed.proto.TryCustomOptions.messageOption;

public class TryCustomOptionsTest {
    @Test
    public void testMessageOption() {
        assertEquals("Message option", TryCustomOptions.CustomOptionsMessage.getDescriptor().getOptions().getExtension(messageOption));
    }

    @Test
    public void testFieldOption() {
        assertEquals("Field option", TryCustomOptions.CustomOptionsMessage.getDescriptor().findFieldByNumber(1).getOptions().getExtension(fieldOption));
    }
}
