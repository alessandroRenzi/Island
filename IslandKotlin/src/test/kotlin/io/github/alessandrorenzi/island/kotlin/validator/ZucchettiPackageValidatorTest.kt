package io.github.alessandrorenzi.island.kotlin.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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
    fun assertTrueWithATarGzPackage(){
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
    }

