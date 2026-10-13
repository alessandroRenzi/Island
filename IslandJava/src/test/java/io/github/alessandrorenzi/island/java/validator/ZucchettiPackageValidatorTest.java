package io.github.alessandrorenzi.island.java.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZucchettiPackageValidatorTest {

    @Test
    public void assertTrueIsAZipPackage() {
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertTrue(validator.isValid("zucchetti.zip"), "zip file should be accepted");
    }

    @Test
    public void assertTrueWithA7ZipPackage(){
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertTrue(validator.isValid("zucchetti.7z"),"7Zip file should be accepted");
    }

    @Test
    public void assertTrueWithATarGzPackage(){
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertTrue(validator.isValid("zucchetti.tar.gz"),"gz file should be accepted");
    }

    @Test
    public void assertFalseWithNotAZipPackage(){
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertFalse(validator.isValid("zucchetti.iso"),"iso file shouldn't be accepted");
    }

    @Test
    public void assertFalseWithNullFile(){
       ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
       assertFalse(validator.isValid(null),"null file shouldn't be accepted");
    }

    @Test
    public void assertFalseWithBlankStringOfFile(){
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertFalse(validator.isValid(""),"Blank string pointing to  file shouldn't be accepted");
    }

    //test that path not contain the file
    @Test
    public void fileFromAbsolutePathNotExists(@TempDir Path tempDir) {
        Path fileFromTmpDir = tempDir.resolve("notZucchetti.zip");
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertFalse(validator.exists(fileFromTmpDir.toAbsolutePath().toString()),"File not should being in that path");
    }

    @Test
    public void fileFromAbsolutePathExists(@TempDir Path tempDir) throws IOException {
        Path fileFromTmpDir = Files.createFile(tempDir.resolve("Zucchetti.zip"));
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertTrue(validator.exists(fileFromTmpDir.toAbsolutePath().toString()),"File should being in that path ");
    }

    @Test
    public void AssertFalseWithAnExistingFileButNotRegularFileIsADirectory(@TempDir Path tempDir){
        ZucchettiPackageValidator validator = new ZucchettiPackageValidator();
        assertFalse(validator.exists(tempDir.toAbsolutePath().toString()));
    }
}
