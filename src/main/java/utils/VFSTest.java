package utils;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by dmitry on 22.04.14.
 */
public class VFSTest {
    @Test
    public void testWriteToFileAndReadFile(){
        String path = "/test/somedata";
        VFS.writeToFile(path, "some");
        VFS.writeToEndOfFile(path, "data");
        Assert.assertEquals(VFS.readFile(path), "somedata");
    }
}
