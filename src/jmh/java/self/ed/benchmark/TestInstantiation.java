package self.ed.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;
import org.openjdk.jmh.annotations.Benchmark;

public class TestInstantiation {
    @Benchmark
    public void newProtoPrinter()  {
        JsonFormat.printer();
    }

    @Benchmark
    public void newProtoParser()  {
        JsonFormat.parser();
    }

    @Benchmark
    public void newJacksonMapper()  {
        new ObjectMapper();
    }
}
