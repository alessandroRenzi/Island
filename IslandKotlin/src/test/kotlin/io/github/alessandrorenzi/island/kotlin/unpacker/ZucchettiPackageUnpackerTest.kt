package io.github.alessandrorenzi.island.kotlin.unpacker

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import kotlin.io.path.*

class ZucchettiPackageUnpackerTest {

    @Test
    fun assertFalseWithAZipPackageThatNotAValidZipPackage(@TempDir tempDir: Path){
        val dummyZipPath = tempDir / "/zucchetti.zip"
        dummyZipPath.writeText("this is not a real zip file, it's a text file")
        val dummyExtractingPath = tempDir / "extracted"
        val zucchettiUnpacker = ZucchettiPackageUnpacker()
        val result = zucchettiUnpacker.unpack(compressedFilePath = dummyZipPath, destinationDirectory = dummyExtractingPath)
        assertFalse(result)
    }

    @Test
    fun assertTrueWithAZipPackageThatIsAValidZipPackage(@TempDir tempDir: Path){
        val dummyZipPath = tempDir / "/"
    }
}