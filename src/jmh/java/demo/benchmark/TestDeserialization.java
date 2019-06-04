package demo.benchmark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import demo.pojo.PojoItem;
import demo.proto.ProtoItem;

import java.io.IOException;

import static demo.util.JsonUtils.*;
import static demo.Generator.*;

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
            Item item = generateItem();
            ProtoItem protoItem = toProtoItem(item);
            PojoItem pojoItem = toPojoItem(item);
            protoBytes = protoItem.toByteArray();
            protoJson = PROTO_PRINTER.print(protoItem);
            protoJsonJackson = PROTO_MAPPER.writeValueAsString(protoItem);
            pojoBytes = POJO_MAPPER.writeValueAsBytes(pojoItem);
            pojoJson = POJO_MAPPER.writeValueAsString(pojoItem);
        }
    }

    @Benchmark
    public void bytesToProto_Protobuf(InputState state) throws IOException {
        ProtoItem.parseFrom(state.protoBytes);
    }

    @Benchmark
    public void jsonToProto_Protobuf(InputState state) throws IOException {
        ProtoItem.Builder builder = ProtoItem.newBuilder();
        PROTO_PARSER.merge(state.protoJson, builder);
        builder.build();
    }

    @Benchmark
    public void jsonToProto_Jackson(InputState state) throws IOException {
        PROTO_MAPPER.readValue(state.protoJsonJackson, ProtoItem.class);
    }

    @Benchmark
    public void jsonBytesToPojo_Jackson(InputState state) throws IOException {
        POJO_MAPPER.readValue(state.pojoBytes, PojoItem.class);
    }

    @Benchmark
    public void jsonToPojo_Jackson(InputState state) throws IOException {
        POJO_MAPPER.readValue(state.pojoJson, PojoItem.class);
    }

    @Benchmark
    public void jsonBytesToPojo_Jackson_NoCache(InputState state) throws IOException {
        new ObjectMapper().readValue(state.pojoBytes, PojoItem.class);
    }

    @Benchmark
    public void jsonToPojo_Jackson_NoCache(InputState state) throws IOException {
        new ObjectMapper().readValue(state.pojoJson, PojoItem.class);
    }
}
