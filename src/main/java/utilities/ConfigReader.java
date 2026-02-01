package utilities;

import java.io.FileInputStream;
import java.util.Properties;

//This class loads configuration properties from a `config.properties` file and provides a
// method to retrieve values based on a given key. It uses a static block to load the properties
// when the class is first accessed.

public class ConfigReader {
        private static Properties properties;

        static {
            try {
                FileInputStream file = new FileInputStream("src/test/resources/config.properties");
                properties = new Properties();
                properties.load(file);
            } catch (Exception e) {
                System.out.println("Failed to load config.properties");
                e.printStackTrace();
            }
        }

        public static String getProperty(String key) {
            return properties.getProperty(key);
        }
}
