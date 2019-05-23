package self.ed.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;

import java.io.IOException;

import static self.ed.util.JsonUtils.*;
import static self.ed.Generator.*;

public class TestSerialization {
    @State(Scope.Thread)
    public static class InputState {
        private Task task;
        @Setup(Level.Invocation)
        public void setup() {
            task = generateTask();
        }
    }

    @Benchmark
    public void newProto(InputState state) {
        toProtoTask(state.task);
    }

    @Benchmark
    public void newProto_Rebuild(InputState state) {
        toProtoTask(state.task).toBuilder().build();
    }

    @Benchmark
    public void newPojo(InputState state) {
        toPojoTask(state.task);
    }

    @Benchmark
    public void serializeProtoToBytes(InputState state) {
        toProtoTask(state.task).toByteArray();
    }

    @Benchmark
    public void serializeProtoToJson(InputState state) throws IOException {
        PROTO_PRINTER.print(toProtoTask(state.task));
    }

    @Benchmark
    public void serializeProtoToJson_Jackson(InputState state) throws IOException {
        PROTO_MAPPER.writeValueAsString(toProtoTask(state.task));
    }

    @Benchmark
    public void serializePojoToJsonBytes(InputState state) throws IOException {
        POJO_MAPPER.writeValueAsBytes(toPojoTask(state.task));
    }

    @Benchmark
    public void serializePojoToJson(InputState state) throws IOException {
        POJO_MAPPER.writeValueAsString(toPojoTask(state.task));
    }

    @Benchmark
    public void serializePojoToJsonBytes_NewObjectMapper(InputState state) throws IOException {
        new ObjectMapper().writeValueAsBytes(toPojoTask(state.task));
    }

    @Benchmark
    public void serializePojoToJson_NewObjectMapper(InputState state) throws IOException {
        new ObjectMapper().writeValueAsString(toPojoTask(state.task));
    }
}
