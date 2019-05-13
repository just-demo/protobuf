package self.ed;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import self.ed.util.JsonJacksonUtils;
import self.ed.util.JsonProtoUtils;

import java.io.IOException;

import static self.ed.TaskGenerator.*;

public class SerializationBenchmark {
    @State(Scope.Thread)
    public static class InputState {
        private Task task;
        @Setup //(Level.Invocation)
        public void setup() {
            task = generateTask();
        }
    }

    @Benchmark
    public void testProtoToBytes(InputState state) {
        toProtoTask(state.task).toByteArray();
    }

    @Benchmark
    public void testProtoToJson(InputState state) throws IOException {
        JsonProtoUtils.protoToJson(toProtoTask(state.task));
    }

    @Benchmark
    public void testProtoToJson_Jackson(InputState state) throws IOException {
        JsonJacksonUtils.protoToJson(toProtoTask(state.task));
    }

    @Benchmark
    public void testPojoToJsonBytes(InputState state) throws IOException {
        JsonJacksonUtils.pojoToJsonBytes(toPojoTask(state.task));
    }

    @Benchmark
    public void testPojoToJson(InputState state) throws IOException {
        JsonJacksonUtils.pojoToJson(toPojoTask(state.task));
    }
}
