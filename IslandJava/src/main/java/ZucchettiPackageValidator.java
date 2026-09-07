import org.apache.commons.io.FilenameUtils;

import java.util.Objects;

public class ZucchettiPackageValidator {
    public boolean isValid(String s) {
        String extension = FilenameUtils.getExtension(s);
        return Objects.equals(extension, "zip");
    }
}
