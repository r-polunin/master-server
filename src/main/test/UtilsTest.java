import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Caster;
import utils.CookieDescriptor;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ruslan on 29.04.2014.
 */
public class UtilsTest {
    @Test
    public void casterTest() {
        Map<String, String> map = new HashMap<String, String>();
        String keyOne = "one";
        String keyTwo = "two";
        String keyThree = "three";

        map.put(keyOne, null);
        map.put(keyTwo, null);
        map.put(keyThree, null);
        String[] keys = Caster.castKeysToStrings(map);

        for (String key: keys) {
            if (!map.containsKey(key)) {
                Assert.fail("Key not exists in map");
            } else {
                map.remove(key);
            }
        }

    }

    @Test
    public void cookieDescriptorTest() {
        Cookie cookies[] = new Cookie[2];
        String cookieOneName = "name1";
        String cookieTwoName = "name2";
        Cookie cookieOne = new Cookie(cookieOneName,"cookie1");
        Cookie cookieTwo = new Cookie(cookieTwoName,"cookie2");
        cookies[0] = cookieOne;
        cookies[1] = cookieTwo;

        CookieDescriptor cd = new CookieDescriptor(cookies);
        Assert.assertEquals(cd.getCookieByName(cookieOneName), cookies[0].getValue());
        Assert.assertEquals(cd.getCookieByName(cookieTwoName), cookies[1].getValue());
    }
}
