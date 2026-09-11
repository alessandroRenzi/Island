import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ZucchettiPackageValidator {

    //method that check if file passed is an allowed kind of file
    public boolean isValid(String fileNameWithPath) {
        String extension = FilenameUtils.getExtension(fileNameWithPath);
        return Objects.equals(extension, "zip");
    }

    //method that check if file passed is located in file system
    public boolean exists(String fileNameWithPath) {
        Path completePathWithFile = Paths.get(fileNameWithPath);
        return Files.exists(completePathWithFile) && Files.isRegularFile(completePathWithFile);
    }

}
