import messageSystem.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Ruslan on 28.04.2014.
 */
public class MessageSystemTest {

    @Test
    public void testMsgQueue() {
        Address firstAddress = new Address();
        Abonent firstAbonent = mock(Abonent.class);
        when(firstAbonent.getAddress()).thenReturn(firstAddress);
        String firstName = "first";

        Address secondAddress = new Address();
        Abonent secondAbonent = mock(Abonent.class);
        when(secondAbonent.getAddress()).thenReturn(secondAddress);
        String secondName = "second";

        MessageSystem messageSystem = new MessageSystemImpl();
        messageSystem.addService(firstAddress, firstName);
        messageSystem.addService(secondAddress, secondName);

        Assert.assertSame(messageSystem.getAddressByName(firstName), firstAddress);
        Assert.assertSame(messageSystem.getAddressByName(secondName), secondAddress);

        Msg msgOne = mock(Msg.class);
        Msg msgTwo = mock(Msg.class);
        Msg msgThree = mock(Msg.class);
        Msg msgFour = mock(Msg.class);
        Msg msgFive = mock(Msg.class);

        int firstAbonentMsqQueueSize = 3;
        int secondAbonentMsqQueueSize = 2;

        messageSystem.putMsg(firstAddress, msgOne);
        messageSystem.putMsg(firstAddress, msgTwo);
        messageSystem.putMsg(firstAddress, msgThree);
        messageSystem.putMsg(secondAddress, msgFour);
        messageSystem.putMsg(secondAddress, msgFive);

        Assert.assertEquals(messageSystem.getMsgQueueByName(firstName).size(), firstAbonentMsqQueueSize);
        Assert.assertSame(messageSystem.getMsgQueueByName(firstName).poll(), msgOne);
        Assert.assertSame(messageSystem.getMsgQueueByName(firstName).poll(), msgTwo);
        Assert.assertSame(messageSystem.getMsgQueueByName(firstName).poll(), msgThree);
        Assert.assertEquals(messageSystem.getMsgQueueByName(secondName).size(), secondAbonentMsqQueueSize);
        Assert.assertSame(messageSystem.getMsgQueueByName(secondName).poll(), msgFour);
        Assert.assertSame(messageSystem.getMsgQueueByName(secondName).poll(), msgFive);
    }

    @Test
    public void testExecForAbonent() {
        final boolean[] executed = {false};
        Address address = new Address();
        Abonent abonent = mock(Abonent.class);
        when(abonent.getAddress()).thenReturn(address);
        String name = "name";

        Msg msg = mock(Msg.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                executed[0] = true;
                return null;
            }
        }).when(msg).exec(abonent);

        MessageSystem messageSystem = new MessageSystemImpl();
        messageSystem.addService(address, name);
        messageSystem.putMsg(address, msg);
        messageSystem.execForAbonent(abonent);
        if (executed[0] = false) {
            Assert.fail("Execute msg block unreached");
        }
    }
}
