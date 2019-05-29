package self.ed.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;

import static self.ed.util.JsonUtils.*;
import static self.ed.Generator.*;

public class TestSerialization {
    @State(Scope.Thread)
    public static class InputState {
        private Item item;
        @Setup(Level.Invocation)
        public void setup() {
            item = generateItem();
        }
    }

    @Benchmark
    public void newProto(InputState state) {
        toProtoItem(state.item);
    }

    @Benchmark
    public void newProto_Rebuild(InputState state) {
        toProtoItem(state.item).toBuilder().build();
    }

    @Benchmark
    public void newPojo(InputState state) {
        toPojoItem(state.item);
    }

    @Benchmark
    public void serializeProtoToBytes(InputState state) {
        toProtoItem(state.item).toByteArray();
    }

    @Benchmark
    public void serializeProtoToJson(InputState state) throws IOException {
        PROTO_PRINTER.print(toProtoItem(state.item));
    }

    @Benchmark
    public void serializeProtoToJson_Jackson(InputState state) throws IOException {
        PROTO_MAPPER.writeValueAsString(toProtoItem(state.item));
    }

    @Benchmark
    public void serializePojoToJsonBytes(InputState state) throws IOException {
        POJO_MAPPER.writeValueAsBytes(toPojoItem(state.item));
    }

    @Benchmark
    public void serializePojoToJson(InputState state) throws IOException {
        POJO_MAPPER.writeValueAsString(toPojoItem(state.item));
    }

    @Benchmark
    public void serializePojoToJsonBytes_NewObjectMapper(InputState state) throws IOException {
        new ObjectMapper().writeValueAsBytes(toPojoItem(state.item));
    }

    @Benchmark
    public void serializePojoToJson_NewObjectMapper(InputState state) throws IOException {
        new ObjectMapper().writeValueAsString(toPojoItem(state.item));
    }
}
