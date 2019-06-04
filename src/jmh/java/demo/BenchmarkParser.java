package demo;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class BenchmarkParser {
    static String benchmark = "TestDeserialization.bytesToProto_Protobuf          thrpt   25  632827.027 ±  8408.943  ops/s\n" +
            "TestDeserialization.jsonToPojo_Jackson             thrpt   25  188470.423 ±  5015.404  ops/s\n" +
            "TestDeserialization.jsonToPojo_Jackson_NoCache     thrpt   25   11285.449 ±   136.573  ops/s\n" +
            "TestDeserialization.jsonToProto_Jackson            thrpt   25   51609.453 ±   618.650  ops/s\n" +
            "TestDeserialization.jsonToProto_Protobuf           thrpt   25   41075.191 ±   502.794  ops/s\n" +
            "TestSerialization.pojoToJson_Jackson               thrpt   25  521748.714 ±  7266.815  ops/s\n" +
            "TestSerialization.pojoToJson_Jackson_NoCache       thrpt   25    6228.282 ±    92.065  ops/s\n" +
            "TestSerialization.protoToBytes_Protobuf            thrpt   25  715850.351 ±  8349.113  ops/s\n" +
            "TestSerialization.protoToJson_Jackson              thrpt   25   89574.020 ±  1071.878  ops/s\n" +
            "TestSerialization.protoToJson_Protobuf             thrpt   25   55836.573 ±   693.589  ops/s";

    public static void main(String[] args) {
        Arrays.stream(benchmark.split("\n"))
                .map(line -> line.split("\\s+"))
                .sorted(BenchmarkParser::compareLines)
                .forEach(cols -> System.out.println(rightPad(fName(cols[0]), 65) + leftPad(f1000(cols[3]), 15) + leftPad(f1000(cols[5]), 15)));
    }

    private static int compareLines(String[] cols1, String[] cols2) {
        int cmp = cols2[0].split("\\.")[0].compareTo(cols1[0].split("\\.")[0]);
        return cmp != 0 ? cmp : new BigDecimal(cols2[3]).compareTo(new BigDecimal(cols1[3]));
    }

    private static String fName(String name) {
        String[] parts = name.split("\\.")[1].split("_", 2);
        String[] direction = parts[0].split("To");
        String description = parts[1].replace("NoCache", "not cached").replace("_", ", ");
        return rightPad(direction[0].toLowerCase(), 5) + " > " + direction[1].toLowerCase() + " (" + description + ")";
    }

    private static String f1000(String str) {
        return new DecimalFormat("0.00").format(new BigDecimal(str).divide(new BigDecimal("1000")));
    }

}
