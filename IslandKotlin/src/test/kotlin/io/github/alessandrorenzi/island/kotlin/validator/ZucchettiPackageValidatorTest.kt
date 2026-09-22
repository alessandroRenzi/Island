package io.github.alessandrorenzi.island.kotlin.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class ZucchettiPackageValidatorTest {

    @Test
    @DisplayName("Should return true on .zip file")
    fun assertTrueIsAZipPackage() {
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
        assertTrue(validator.isValid("zucchetti.zip"))
    }

    @Test
    fun assertFalseWithNotAZipPackage() {
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
        assertFalse(validator.isValid("zucchetti.iso"))
    }

    @Test
    fun assertTrueWithA7ZipPackage() {
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
        assertTrue(validator.isValid("zucchetti.7z"))
    }

    @Test
    fun assertTrueWithATarGzPackage() {
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
        assertTrue(validator.isValid("zucchetti.tar.gz"))
    }

    /* NOT NEEDED -- fun isValid has String parameter that not accept null values
    @Test
    fun assertFalseWithNullPackage(){
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
            assertFalse(validator.isValid(null))
        }
    */

    @Test
    fun assertFalseWithBlanKPackageReference() {
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
        assertFalse(validator.isValid(""))
    }

    @Test
    fun fileFromAbsolutePathNotExists(@TempDir tempDir: Path) {
        val fileFromTmpDir = tempDir.resolve("notZucchetti.zip")
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
        assertFalse(validator.exists(fileFromTmpDir.toAbsolutePath().toString())
        )
    }

    @Test
    fun fileFromAbsolutePathExists(@TempDir tempDir: Path){
        val fileFromTmpDir = tempDir.resolve("zucchetti.zip")
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator();
        assertTrue(validator.exists(fileFromTmpDir.toAbsolutePath().toString()))
    }
}

