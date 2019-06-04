package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import demo.proto.intvslong.Demo;

public class Int32vs64Main {
    public static void main(String[] args) throws InvalidProtocolBufferException, JsonProcessingException {
        Demo demo = Demo.newBuilder()
                .setA(123)
                .setB(123)
                .build();
        System.out.println(JsonFormat.printer().omittingInsignificantWhitespace().print(demo)); // {"a":123,"b":"123"}
        System.out.println(new ObjectMapper().registerModule(new ProtobufModule()).writeValueAsString(demo));

        Demo.Builder builder = Demo.newBuilder();
//        JsonFormat.parser().merge("{\"a\":123,\"b\":\"Infinity\"}", builder); // an error is thrown
        System.out.println(builder.build());
    }
}
