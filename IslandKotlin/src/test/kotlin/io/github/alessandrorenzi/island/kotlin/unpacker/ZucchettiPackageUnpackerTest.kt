package io.github.alessandrorenzi.island.kotlin.unpacker

import com.google.common.jimfs.Configuration
import com.google.common.jimfs.Jimfs
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.path.div
import kotlin.io.path.outputStream
import kotlin.io.path.writeText

class ZucchettiPackageUnpackerTest {

    @Test
    fun assertFalseWithAZipPackageThatNotAValidZipPackage() {
        val fileSystem = Jimfs.newFileSystem(Configuration.unix())
        //Higher Order function (apply)
        val dummyZipPath = fileSystem.getPath("zucchetti.zip").apply {
            writeText("this is not a real.zip file")
        }
        val dummyExtractingPath = dummyZipPath / "extracted"
        val zucchettiUnpacker = ZucchettiPackageUnpacker()
        val result =
            zucchettiUnpacker.unpack(compressedFilePath = dummyZipPath, destinationDirectory = dummyExtractingPath)
        assertFalse(result)
    }

    @Test
    fun assertTrueWithAZipPackageThatIsAValidZipPackage() {
        val fileSystem = Jimfs.newFileSystem(Configuration.unix())
        val dummyZipPath = fileSystem.getPath("/zucchetti.zip")
        val dummyExtractingPath = fileSystem.getPath("/extracted")
        //build a real .zip file
        ZipOutputStream(dummyZipPath.outputStream()).use { zip ->
            zip.putNextEntry(ZipEntry("txtFile.txt"))
            zip.write("I am a txt file than i am going to be compressed".toByteArray())
            zip.closeEntry()
        }
        val zucchettiUnpacker = ZucchettiPackageUnpacker()
        val result =
            zucchettiUnpacker.unpack(compressedFilePath = dummyZipPath, destinationDirectory = dummyExtractingPath)
        assertTrue(result)
    }

    @Test
    fun assertFalseWithAGzPackageThatIsNotAValidGzPackage() {
        val fileSystem = Jimfs.newFileSystem(Configuration.unix())
        //Higher Order function (apply)
        val dummyTarGzPath = fileSystem.getPath("zucchetti.tar.gz").apply {
            writeText("this is not a real .tar.gz file")
        }
        val dummyExtractingPath = fileSystem.getPath("/extracted")
        val zucchettiPackageUnpackerTest = ZucchettiPackageUnpacker()
        val result = zucchettiPackageUnpackerTest.unpack(
            compressedFilePath = dummyTarGzPath,
            destinationDirectory = dummyExtractingPath
        )
        assertFalse(result)
    }


    @Test
    fun assertTrueWithAGZPackageThatIsAValidGzPackage() {
        val fileSystem = Jimfs.newFileSystem(Configuration.unix())
        val dummyTarGzPath = fileSystem.getPath("/zucchetti.tar.gz")
        val dummyExtractingPath = fileSystem.getPath("/extracted")
        val fileContent = "I am a txt ftile than i am going to be compressed".toByteArray()
        val fileEntry = TarArchiveEntry("txtFile.txt").apply {
            size = fileContent.size.toLong()
        }
        GzipCompressorOutputStream(dummyTarGzPath.outputStream()).use { gzipOut ->
            TarArchiveOutputStream(gzipOut).use { tarOut ->
                tarOut.putArchiveEntry(fileEntry)
                tarOut.write(fileContent)
                tarOut.closeArchiveEntry()
            }

        }
        val zucchettiUnpacker = ZucchettiPackageUnpacker()
        val result = zucchettiUnpacker.unpack(dummyTarGzPath, dummyExtractingPath)
        assertTrue(result)
    }
}