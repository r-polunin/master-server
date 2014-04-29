import messageSystem.Address;
import messageSystem.AddressService;
import messageSystem.AddressServiceImpl;
import org.hibernate.annotations.common.AssertionFailure;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by Ruslan on 27.04.2014.
 */
public class AddressServiceTest {

    private AddressService service;

    @BeforeClass
    public void setUp() {
        service = new AddressServiceImpl();
    }

    @Test
    public void testAddGetService() {
        Address expectedAddress = mock(Address.class);
        String name = "test address";
        service.addService(expectedAddress, name);
        Address address = service.getAddressByName(name);
        Assert.assertSame(address, expectedAddress);
    }
    @Test
    public void getWrongName() {
        String nameOne = "wrong name";
        String nameTwo = null;
        String nameThree = "";
        try {
            service.getAddressByName(nameOne);
            Assert.fail("wrong name one passed");
        } catch(RuntimeException e) {
            String expected = "Service " + nameOne + " not found";
            Assert.assertEquals(e.getMessage(),expected);
        }

        try {
            service.getAddressByName(nameTwo);
            Assert.fail("wrong name two passed");
        } catch(Exception e) {
            String expected = "Service null not found";
            Assert.assertEquals(e.getMessage(),expected);
        }

        try {
            service.getAddressByName(nameThree);
            Assert.fail("wrong name three passed");
        } catch(RuntimeException e) {
            String expected = "Service " + nameThree + " not found";
            Assert.assertEquals(e.getMessage(),expected);
        }
    }

    @Test
    public void testSameNameServices() {
        Address expectedAddressOne = mock(Address.class);
        Address expectedAddressTwo = mock(Address.class);
        Address expectedAddressThree = mock(Address.class);
        String name = "same test address";
        service.addService(expectedAddressOne, name);
        service.addService(expectedAddressTwo, name);
        service.addService(expectedAddressThree, name);
        Address address = service.getAddressByName(name);
        Assert.assertSame(address, expectedAddressOne);
        address = service.getAddressByName(name);
        Assert.assertSame(address, expectedAddressThree);
        address = service.getAddressByName(name);
        Assert.assertSame(address, expectedAddressTwo);
    }
}
