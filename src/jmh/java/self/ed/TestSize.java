package self.ed;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static self.ed.Generator.*;

public class TestSize {
    // 2 items in the collection
    // Json: 668
    // Json gzip: 287
    // Proto: 248

    // 200 items in the collection
    // Json: 47990
    // Proto: 19058

    // 5000 items in the collection
    // Json file: 1195190
    // Proto file: 475058

    // Very long string value:
    // Json: 1050652
    // Proto: 1050234

    // Single character field names in POJO:
    // Json: 455
    // Proto: 248

    // Single character values:
    // Json: 433
    // Proto: 101
    public static void main(String[] args) throws IOException {
        Path out = Paths.get("tmp");
        File jsonOut = out.resolve("json").toFile();
        File protoOut = out.resolve("proto").toFile();

        FileUtils.deleteQuietly(out.toFile());
        Files.createDirectories(out);
        Files.createFile(jsonOut.toPath());
        Files.createFile(protoOut.toPath());

        Generator.Task task = generateTask();
        String json = new ObjectMapper().writeValueAsString(toPojoTask(task));
        System.out.println("Json: " + json.length());
        System.out.println("Proto: " + toProtoTask(task).toByteArray().length);
        toProtoTask(task).writeTo(new FileOutputStream(protoOut));
        FileUtils.writeStringToFile(jsonOut, json, UTF_8);
        System.out.println("Json file: " + jsonOut.length());
        System.out.println("Proto file: " + protoOut.length());
    }
}
