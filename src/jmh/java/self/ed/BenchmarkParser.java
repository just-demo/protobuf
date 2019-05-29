package self.ed;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class BenchmarkParser {
    static String benchmark = "TestDeserialization.bytesToProto_Protobuf       thrpt   25  749639.241 ± 15633.363  ops/s\n" +
            "TestDeserialization.jsonToPojo_Jackson          thrpt   25  219149.582 ±  2501.725  ops/s\n" +
            "TestDeserialization.jsonToPojo_Jackson_NoCache  thrpt   25   12011.588 ±   739.354  ops/s\n" +
            "TestDeserialization.jsonToProto_Jackson         thrpt   25   51997.936 ±   513.459  ops/s\n" +
            "TestDeserialization.jsonToProto_Protobuf        thrpt   25   40824.312 ±   595.746  ops/s\n" +
            "TestSerialization.pojoToJson_Jackson            thrpt   25  528482.500 ±  7472.607  ops/s\n" +
            "TestSerialization.pojoToJson_Jackson_NoCache    thrpt   25    6222.373 ±    54.745  ops/s\n" +
            "TestSerialization.protoToBytes_Protobuf         thrpt   25  708904.513 ±  8327.902  ops/s\n" +
            "TestSerialization.protoToJson_Jackson           thrpt   25   89316.938 ±  1165.873  ops/s\n" +
            "TestSerialization.protoToJson_Protobuf          thrpt   25   56641.151 ±   739.160  ops/s";

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
