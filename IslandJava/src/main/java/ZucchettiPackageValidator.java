import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class ZucchettiPackageValidator {
    private static final Set<String> ALLOWED_FILE_EXTENSION = Set.of("zip","7z","gz");

    //method that check if file passed is an allowed kind of file
    public boolean isValid(String fileNameWithPath) {
        if(fileNameWithPath == null || fileNameWithPath.isEmpty()){
            return false;
        }
        String extension = FilenameUtils.getExtension(fileNameWithPath);
        return ALLOWED_FILE_EXTENSION.contains(extension.toLowerCase());
    }

    //method that check if file passed is located in file system
    public boolean exists(String fileNameWithPath) {
        Path completePathWithFile = Paths.get(fileNameWithPath);
        return Files.exists(completePathWithFile) & Files.isRegularFile(completePathWithFile);
    }

}
