package core;

import org.testng.annotations.Test;

public class jsonTest {

        @Test
        public void testJsonData() {
            System.out.println(JsonUtil.getData("TC_01", "from"));
            System.out.println(JsonUtil.getData("TC_01", "to"));
            System.out.println(JsonUtil.getData("TC_02", "departureDate"));
        }
}
