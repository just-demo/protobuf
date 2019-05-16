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
    // Json: 668
    // Json gzip: 287
    // Proto: 248
    public static void main(String[] args) throws IOException {
        Path out = Paths.get("tmp");
        File jsonOut = out.resolve("json").toFile();
        File protoOut = out.resolve("proto").toFile();

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
