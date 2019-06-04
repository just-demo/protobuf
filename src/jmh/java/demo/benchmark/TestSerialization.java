package demo.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;

import static demo.util.JsonUtils.*;
import static demo.Generator.*;

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
    public void protoToBytes_Protobuf(InputState state) {
        toProtoItem(state.item).toByteArray();
    }

    @Benchmark
    public void protoToJson_Protobuf(InputState state) throws IOException {
        PROTO_PRINTER.print(toProtoItem(state.item));
    }

    @Benchmark
    public void protoToJson_Jackson(InputState state) throws IOException {
        PROTO_MAPPER.writeValueAsString(toProtoItem(state.item));
    }

    @Benchmark
    public void pojoToJsonBytes_Jackson(InputState state) throws IOException {
        POJO_MAPPER.writeValueAsBytes(toPojoItem(state.item));
    }

    @Benchmark
    public void pojoToJson_Jackson(InputState state) throws IOException {
        POJO_MAPPER.writeValueAsString(toPojoItem(state.item));
    }

    @Benchmark
    public void pojoToJsonBytes_Jackson_NoCache(InputState state) throws IOException {
        new ObjectMapper().writeValueAsBytes(toPojoItem(state.item));
    }

    @Benchmark
    public void pojoToJson_Jackson_NoCache(InputState state) throws IOException {
        new ObjectMapper().writeValueAsString(toPojoItem(state.item));
    }
}
