package io.github.alessandrorenzi.island.kotlin.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import kotlin.io.path.Path
import kotlin.io.path.createFile
import kotlin.io.path.createTempDirectory
import kotlin.io.path.div
import kotlin.io.path.pathString


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
    fun fileFromAbsolutePathNotExists() {
        val validator = ZucchettiPackageValidator()
        val tempDir = createTempDirectory()
        //overloading operatore in classe Path (effettua la resolve)
        val fileFromTempDir = tempDir / "notZucchetti.zip"
        assertFalse(validator.exists(fileFromTempDir.pathString))
    }

    @Test
    fun fileFromAbsolutePathExists(){
        val validator = ZucchettiPackageValidator()
        val tempDir = createTempDirectory()
        val fileFromTmpDir = (tempDir / "zucchetti.zip").createFile();
        assertTrue(validator.exists(fileFromTmpDir.pathString))
    }
}

