package core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class JsonUtil {

        private static JsonNode testData;

        static {
            try {
                ObjectMapper mapper = new ObjectMapper();
                testData = mapper.readTree(
                        new File("src/test/resources/testdata/flight_testdata.json")
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static String getData(String testCaseId, String key) {
            return testData.get(testCaseId).get(key).asText();
        }
}
