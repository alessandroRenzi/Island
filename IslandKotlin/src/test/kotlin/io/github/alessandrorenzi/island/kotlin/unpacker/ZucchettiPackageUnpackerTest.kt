package io.github.alessandrorenzi.island.kotlin.unpacker

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.*


class ZucchettiPackageUnpackerTest {

    @Test
    fun assertFalseWithAZipPackageThatNotAValidZipPackage(){
        val fileSystem = Jimfs.newFileSystem(Configuration.unix())
        //Higher Order function (apply)
        val dummyZipPath = fileSystem.getPath("zucchetti.zip").apply {
            writeText("this is not a real.zip file") }
        val dummyExtractingPath = dummyZipPath / "extracted"
        val zucchettiUnpacker = ZucchettiPackageUnpacker()
        val result = zucchettiUnpacker.unpack(compressedFilePath = dummyZipPath, destinationDirectory = dummyExtractingPath)
        assertFalse(result)
    }

    @Test
    fun assertTrueWithAZipPackageThatIsAValidZipPackage(@TempDir tempDir: Path){
        val dummyZipPath = tempDir / "/"
    }
}