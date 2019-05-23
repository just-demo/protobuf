package self.ed.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import self.ed.pojo.PojoTask;
import self.ed.proto.ProtoTask;

import java.io.IOException;

import static self.ed.util.JsonUtils.*;
import static self.ed.Generator.*;

public class TestDeserialization {
    @State(Scope.Thread)
    public static class InputState {
        private byte[] protoBytes;
        private String protoJson;
        private String protoJsonJackson;
        private byte[] pojoBytes;
        private String pojoJson;

        @Setup(Level.Invocation)
        public void setup() throws IOException {
            Task task = generateTask();
            ProtoTask protoTask = toProtoTask(task);
            PojoTask pojoTask = toPojoTask(task);
            protoBytes = protoTask.toByteArray();
            protoJson = PROTO_PRINTER.print(protoTask);
            protoJsonJackson = PROTO_MAPPER.writeValueAsString(protoTask);
            pojoBytes = POJO_MAPPER.writeValueAsBytes(pojoTask);
            pojoJson = POJO_MAPPER.writeValueAsString(pojoTask);
        }
    }

    @Benchmark
    public void deserializeProtoFromBytes(InputState state) throws IOException {
        ProtoTask.parseFrom(state.protoBytes);
    }

    @Benchmark
    public void deserializeProtoFromJson(InputState state) throws IOException {
        ProtoTask.Builder builder = ProtoTask.newBuilder();
        PROTO_PARSER.merge(state.protoJson, builder);
        builder.build();
    }

    @Benchmark
    public void deserializeProtoFromJson_ObjectMapper(InputState state) throws IOException {
        PROTO_MAPPER.readValue(state.protoJsonJackson, ProtoTask.class);
    }

    @Benchmark
    public void deserializePojoFromJsonBytes(InputState state) throws IOException {
        POJO_MAPPER.readValue(state.pojoBytes, PojoTask.class);
    }

    @Benchmark
    public void deserializePojoFromJson(InputState state) throws IOException {
        POJO_MAPPER.readValue(state.pojoJson, PojoTask.class);
    }

    @Benchmark
    public void deserializePojoFromJsonBytes_NewObjectMapper(InputState state) throws IOException {
        new ObjectMapper().readValue(state.pojoBytes, PojoTask.class);
    }

    @Benchmark
    public void deserializePojoFromJson_NewObjectMapper(InputState state) throws IOException {
        new ObjectMapper().readValue(state.pojoJson, PojoTask.class);
    }
}
