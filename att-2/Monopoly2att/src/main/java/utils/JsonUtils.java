package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class JsonUtils {
    private ObjectMapper mapper;

    public JsonUtils() {
        this.mapper = new ObjectMapper();
    }

    public void writeToJsonFile(String filename, Object object) {
        try {
            mapper.writeValue(new File(filename), object);
        } catch (IOException e) {
            System.err.print(e);
        }
    }

    public void readJsonFile(String filename, Object object) {
        try {
            Object objectValue = mapper.readValue(Paths.get(filename).toFile(), object.getClass());
            System.out.println(objectValue);
        } catch (IOException e) {
            System.err.print(e);
        }
    }
}
