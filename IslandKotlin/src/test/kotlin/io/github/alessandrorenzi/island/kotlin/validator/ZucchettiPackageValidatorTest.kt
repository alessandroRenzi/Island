package io.github.alessandrorenzi.island.kotlin.validator

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ZucchettiPackageValidatorTest {

    @Test
    @DisplayName("Should return true on .zip file")
    fun isAZipPackage(){
        val validator: ZucchettiPackageValidator = ZucchettiPackageValidator()
        assertTrue(validator.isValid("zucchetti.zip"))
    }
}