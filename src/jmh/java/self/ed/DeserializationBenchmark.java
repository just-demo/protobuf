package self.ed;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import self.ed.util.JsonJacksonUtils;
import self.ed.util.JsonProtoUtils;
import self.ed.pojo.PojoTask;
import self.ed.proto.ProtoTask;

import java.io.IOException;

import static self.ed.TaskGenerator.*;

public class DeserializationBenchmark {
    @State(Scope.Thread)
    public static class InputState {
        private byte[] protoBytes;
        private String protoJson;
        private String protoJsonJackson;
        private byte[] pojoBytes;
        private String pojoJson;

        @Setup //(Level.Invocation)
        public void setup() throws IOException {
            Task task = generateTask();
            ProtoTask protoTask = toProtoTask(task);
            PojoTask pojoTask = toPojoTask(task);
            protoBytes = protoTask.toByteArray();
            protoJson = JsonProtoUtils.protoToJson(protoTask);
            protoJsonJackson = JsonJacksonUtils.protoToJson(protoTask);
            pojoBytes = JsonJacksonUtils.pojoToJsonBytes(pojoTask);
            pojoJson = JsonJacksonUtils.pojoToJson(pojoTask);
        }
    }

    @Benchmark
    public void testBytesToProto(InputState state) throws IOException {
        ProtoTask.parseFrom(state.protoBytes);
    }

    @Benchmark
    public void testJsonToProto(InputState state) throws IOException {
        JsonProtoUtils.jsonToProto(state.protoJson, ProtoTask.newBuilder()).build();
    }

    @Benchmark
    public void testJsonToProto_Jackson(InputState state) throws IOException {
        JsonJacksonUtils.jsonToProto(state.protoJsonJackson, ProtoTask.class);
    }

    @Benchmark
    public void testJsonBytesToPojo(InputState state) throws IOException {
        JsonJacksonUtils.jsonBytesToPojo(state.pojoBytes, PojoTask.class);
    }

    @Benchmark
    public void testJsonToPojo(InputState state) throws IOException {
        JsonJacksonUtils.jsonToPojo(state.pojoJson, PojoTask.class);
    }
}
