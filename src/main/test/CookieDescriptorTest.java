import junit.framework.Assert;
import org.testng.annotations.Test;
import utils.CookieDescriptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
//import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by dmitry on 19.04.14.
 */
public class CookieDescriptorTest {

    @Test
    public void testIsCookiePushed(){
        HttpServletRequest request = mock(HttpServletRequest.class);
        String nameOfCookie = "sessionId";
        String valueOfCookie ="1";
        Cookie cookies[] = {new Cookie(nameOfCookie,valueOfCookie)};
        CookieDescriptor cookie = new CookieDescriptor(cookies);
        Assert.assertEquals(cookie.getCookieByName(nameOfCookie), valueOfCookie);
    }
}
