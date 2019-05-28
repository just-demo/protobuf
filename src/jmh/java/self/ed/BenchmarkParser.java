package self.ed;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.stream.Stream;

import static org.apache.commons.lang3.StringUtils.leftPad;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class BenchmarkParser {
    static String benchmark = "TestDeserialization.deserializePojoFromJson                       thrpt   25      182256.382 ±      4001.003  ops/s\n" +
            "TestDeserialization.deserializePojoFromJsonBytes                  thrpt   25      226991.594 ±      5757.821  ops/s\n" +
            "TestDeserialization.deserializePojoFromJsonBytes_NewObjectMapper  thrpt   25       11220.487 ±       140.649  ops/s\n" +
            "TestDeserialization.deserializePojoFromJson_NewObjectMapper       thrpt   25       11207.175 ±       162.374  ops/s\n" +
            "TestDeserialization.deserializeProtoFromBytes                     thrpt   25      630372.753 ±      9374.984  ops/s\n" +
            "TestDeserialization.deserializeProtoFromJson                      thrpt   25       49135.439 ±       555.230  ops/s\n" +
            "TestDeserialization.deserializeProtoFromJson_ObjectMapper         thrpt   25       61371.534 ±       837.948  ops/s\n" +
            "TestInstantiation.newJacksonMapper                                thrpt   25     1657036.830 ±     83695.733  ops/s\n" +
            "TestInstantiation.newProtoParser                                  thrpt   25  3582456346.811 ± 156270107.215  ops/s\n" +
            "TestInstantiation.newProtoPrinter                                 thrpt   25  3643403013.139 ±  44039199.392  ops/s\n" +
            "TestSerialization.newPojo                                         thrpt   25    11880572.379 ±    816207.208  ops/s\n" +
            "TestSerialization.newProto                                        thrpt   25     4672526.875 ±    402248.431  ops/s\n" +
            "TestSerialization.newProto_Rebuild                                thrpt   25     4525399.011 ±     33799.570  ops/s\n" +
            "TestSerialization.serializePojoToJson                             thrpt   25      620003.405 ±      4351.272  ops/s\n" +
            "TestSerialization.serializePojoToJsonBytes                        thrpt   25      607724.002 ±     19228.509  ops/s\n" +
            "TestSerialization.serializePojoToJsonBytes_NewObjectMapper        thrpt   25        7327.699 ±        86.577  ops/s\n" +
            "TestSerialization.serializePojoToJson_NewObjectMapper             thrpt   25        7381.435 ±        72.776  ops/s\n" +
            "TestSerialization.serializeProtoToBytes                           thrpt   25      841617.472 ±      4744.149  ops/s\n" +
            "TestSerialization.serializeProtoToJson                            thrpt   25       64985.082 ±      2184.085  ops/s\n" +
            "TestSerialization.serializeProtoToJson_Jackson                    thrpt   25      103933.720 ±      1407.224  ops/s";
    public static void main(String[] args) {
        for (String line: benchmark.split("\n")) {
            String[] cols = line.split("\\s+");
            System.out.println(rightPad(cols[0], 65) + leftPad(f1000(cols[3]), 15) + leftPad(f1000(cols[5]), 15));
        }
    }

    private static String f1000(String str) {
        return new DecimalFormat("0.00").format(new BigDecimal(str).divide(new BigDecimal("1000")));
    }

}
