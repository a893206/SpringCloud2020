import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

/**
 * @author cr
 * @date 2020-10-22 20:02
 */
public class Test1 {
    @Test
    void testZoneDateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        //2020-10-22T20:08:01.217+08:00[Asia/Shanghai]
        System.out.println("zonedDateTime = " + zonedDateTime);
    }
}
