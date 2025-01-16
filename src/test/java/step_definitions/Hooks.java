package step_definitions;

import io.cucumber.java.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Hooks {
    public static Properties properties;
    public static HashMap<String, Object> dataMap;

    @BeforeAll
    public static void before_all(){
        properties = readProperties();
        dataMap = new HashMap<>();
    }

    private static Properties readProperties(){
        Properties properties = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;

    }
}
