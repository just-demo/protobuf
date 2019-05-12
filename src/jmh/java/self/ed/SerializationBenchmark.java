package self.ed;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.IOException;

import static self.ed.TaskGenerator.*;
import static self.ed.util.JsonUtils.*;

public class SerializationBenchmark {
    @State(Scope.Thread)
    public static class InputState {
        private Task task;
        @Setup //(Level.Invocation)
        public void setup() {
            task = generateTask();
        }
    }

    /*
SerializationBenchmark.testPojoToBytes   thrpt   25  234762.860 ▒ 14669.551  ops/s
SerializationBenchmark.testPojoToJson    thrpt   25  278092.578 ▒ 19190.469  ops/s
SerializationBenchmark.testProtoToBytes  thrpt   25  369064.602 ▒ 33769.606  ops/s

Benchmark                                      Mode  Cnt       Score       Error  Units
DeserializationBenchmark.testBytesToProto     thrpt   25  413900.761 ▒ 40523.913  ops/s
DeserializationBenchmark.testJsonBytesToPojo  thrpt   25  181332.251 ▒  8214.575  ops/s
DeserializationBenchmark.testJsonToPojo       thrpt   25  128620.730 ▒ 11053.229  ops/s
DeserializationBenchmark.testJsonToProto      thrpt   25   26353.502 ▒   726.098  ops/s
SerializationBenchmark.testPojoToJson         thrpt   25  246307.179 ▒ 26495.157  ops/s
SerializationBenchmark.testPojoToJsonBytes    thrpt   25  275030.490 ▒ 20901.826  ops/s
SerializationBenchmark.testProtoToBytes       thrpt   25  409726.584 ▒  9523.740  ops/s
SerializationBenchmark.testProtoToJson        thrpt   25   31775.571 ▒   943.770  ops/s

     */

    @Benchmark
    public void testProtoToBytes(InputState state) {
        toProtoTask(state.task).toByteArray();
    }

    @Benchmark
    public void testProtoToJson(InputState state) throws IOException {
        protoToJson(toProtoTask(state.task));
    }

    @Benchmark
    public void testPojoToJsonBytes(InputState state) throws IOException {
        pojoToJsonBytes(toPojoTask(state.task));
    }

    @Benchmark
    public void testPojoToJson(InputState state) throws IOException {
        pojoToJson(toPojoTask(state.task));
    }
}
