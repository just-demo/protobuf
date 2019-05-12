package self.ed;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import self.ed.pojo.PojoTask;
import self.ed.proto.ProtoTask;

import java.io.IOException;

import static self.ed.TaskGenerator.*;
import static self.ed.util.JsonUtils.*;

public class DeserializationBenchmark {

    /*
    Benchmark                            Mode  Cnt       Score       Error  Units
    DeserializationBenchmark.testJson   thrpt   25  138405.068 ▒  6631.113  ops/s
    DeserializationBenchmark.testProto  thrpt   25  481333.437 ▒ 17241.904  ops/s

    Benchmark                                Mode  Cnt       Score       Error  Units
    DeserializationBenchmark.testJson       thrpt   25  116667.386 ▒ 11387.898  ops/s
    DeserializationBenchmark.testJsonBytes  thrpt   25  153175.029 ▒ 11618.605  ops/s
    DeserializationBenchmark.testProto      thrpt   25  450941.537 ▒ 20677.231  ops/s

    --- if don't cache ObjectMapper() ---
    Benchmark                                Mode  Cnt       Score       Error  Units
    DeserializationBenchmark.testJson       thrpt   25    6205.768 ▒   296.988  ops/s
    DeserializationBenchmark.testJsonBytes  thrpt   25    5969.414 ▒   199.040  ops/s
    DeserializationBenchmark.testProto      thrpt   25  399315.436 ▒ 17747.834  ops/s

     */
    @State(Scope.Thread)
    public static class InputState {
        private byte[] protoBytes;
        private String protoJson;
        private byte[] pojoBytes;
        private String pojoJson;

        @Setup //(Level.Invocation)
        public void setup() throws IOException {
            Task task = generateTask();
            ProtoTask protoTask = toProtoTask(task);
            PojoTask pojoTask = toPojoTask(task);
            protoBytes = protoTask.toByteArray();
            protoJson = protoToJson(protoTask);
            pojoBytes = pojoToJsonBytes(pojoTask);
            pojoJson = pojoToJson(pojoTask);
        }
    }

    @Benchmark
    public void testBytesToProto(InputState state) throws IOException {
        ProtoTask.parseFrom(state.protoBytes);
    }

    @Benchmark
    public void testJsonToProto(InputState state) throws IOException {
        jsonToProto(state.protoJson, ProtoTask.newBuilder()).build();
    }

    @Benchmark
    public void testJsonBytesToPojo(InputState state) throws IOException {
        jsonBytesToPojo(state.pojoBytes, PojoTask.class);
    }

    @Benchmark
    public void testJsonToPojo(InputState state) throws IOException {
        jsonToPojo(state.pojoJson, PojoTask.class);
    }
}
