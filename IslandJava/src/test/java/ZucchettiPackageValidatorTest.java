import org.junit.jupiter.api.Test;

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
}
