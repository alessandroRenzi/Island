import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZucchettiPackageValidatorTest {

    @Test
    public void isAZipPackage() {
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertTrue(validator.isValid("zucchetti.zip"), "Should be .zip file");
    }
}
