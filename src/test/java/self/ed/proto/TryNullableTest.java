package self.ed.proto;

import com.google.protobuf.Int32Value;
import com.google.protobuf.InvalidProtocolBufferException;
import demo.proto.nullable.User;
import demo.proto.nullable.UserOneof;
import demo.proto.nullable.UserWrapper;
import org.junit.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static com.google.protobuf.util.JsonFormat.printer;
import static org.junit.Assert.*;

public class TryNullableTest {
    @Test
    public void testNullable_Default() throws InvalidProtocolBufferException {
        User user = User.newBuilder().build();
        assertEquals(0, user.getId());
        assertNotNull(UserMapper.INSTANCE.map(user).getId());
        assertEquals("{}", printer().omittingInsignificantWhitespace().print(user));
        assertEquals("{\"id\":0}", printer().omittingInsignificantWhitespace().includingDefaultValueFields().print(user));

        user = User.newBuilder()
                .setId(0)
                .build();
        assertEquals(0, user.getId());
        assertNotNull(UserMapper.INSTANCE.map(user).getId());
        assertEquals("{}", printer().omittingInsignificantWhitespace().print(user));
        assertEquals("{\"id\":0}", printer().omittingInsignificantWhitespace().includingDefaultValueFields().print(user));
    }

    @Test
    public void testNullable_Wrapper() throws InvalidProtocolBufferException {
        UserWrapper user = UserWrapper.newBuilder().build();
        assertFalse(user.hasId());
        assertEquals(0, user.getId().getValue());
        assertNull(UserMapper.INSTANCE.map(user).getId());
        assertEquals("{}", printer().omittingInsignificantWhitespace().print(user));
        assertEquals("{}", printer().omittingInsignificantWhitespace().includingDefaultValueFields().print(user));

        user = UserWrapper.newBuilder()
                .setId(Int32Value.of(0))
                .build();
        assertTrue(user.hasId());
        assertEquals(0, user.getId().getValue());
        assertNotNull(UserMapper.INSTANCE.map(user).getId());
        assertEquals("{\"id\":0}", printer().omittingInsignificantWhitespace().print(user));
        assertEquals("{\"id\":0}", printer().omittingInsignificantWhitespace().includingDefaultValueFields().print(user));
    }

    @Test
    public void testNullable_Oneof() throws InvalidProtocolBufferException {
        UserOneof user = UserOneof.newBuilder().build();
        assertEquals(UserOneof.OptCase.OPT_NOT_SET, user.getOptCase());
        assertEquals(0, user.getId());
        assertNotNull(UserMapper.INSTANCE.map(user).getId());
        assertEquals("{}", printer().omittingInsignificantWhitespace().print(user));
        assertEquals("{}", printer().omittingInsignificantWhitespace().includingDefaultValueFields().print(user));

        user = UserOneof.newBuilder()
                .setId(0)
                .build();
        assertEquals(UserOneof.OptCase.ID, user.getOptCase());
        assertEquals(0, user.getId());
        assertNotNull(UserMapper.INSTANCE.map(user).getId());
        assertEquals("{\"id\":0}", printer().omittingInsignificantWhitespace().print(user));
        assertEquals("{\"id\":0}", printer().omittingInsignificantWhitespace().includingDefaultValueFields().print(user));
    }

    @Test(expected = NullPointerException.class)
    public void testSetNull() {
        Int32Value id = null;
        UserWrapper.Builder userBuilder = UserWrapper.newBuilder();
        userBuilder.setId(id);

    }

    @Test
    public void testClearOnNull() {
        Int32Value id = null;
        UserWrapper.Builder userBuilder = UserWrapper.newBuilder();
        if (id == null) {
            userBuilder.clearId();
        } else {
            userBuilder.setId(id);
        }

        userBuilder.build();
        assertFalse(userBuilder.hasId());
    }

    @Mapper
    interface UserMapper {
        UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

        PojoUser map(User user);

        PojoUser map(UserWrapper user);

        PojoUser map(UserOneof user);

        default Integer map(Int32Value value) {
            return value.getValue();
        }
    }

    public static class PojoUser {
        Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
