import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZucchettiPackageValidatorTest {

    //test that is a zip package
    @Test
    public void isAZipPackage() {
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertTrue(validator.isValid("zucchetti.zip"), ".zip file should be accepted");
    }

    //test that is not a Zip package
    @Test
    public void isNotAZipPackage(){
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertFalse(validator.isValid("zucchetti.iso"),".iso file shouldn't be accepted");
    }

    //test that path not contain the file
    @Test
    public void fileFromAbsolutePathNotExists(@TempDir Path tempDir) throws IOException {
        Path fileFromTmpDir =tempDir.resolve("notZucchetti.zip");
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertFalse(validator.exists(fileFromTmpDir.toAbsolutePath().toString()),"File not should being in that path");
    }

    //test that path contain the file
    @Test
    public void fileFromAbsolutePathExists(@TempDir Path tempDir) throws IOException {
        Path fileFromTmpDir = Files.createFile(tempDir.resolve("Zucchetti.zip"));
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertTrue(validator.exists(fileFromTmpDir.toAbsolutePath().toString()),"File should being in that path ");
    }
}
