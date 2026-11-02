package io.github.alessandrorenzi.island.kotlin.unpacker

import java.nio.file.Path
import java.util.zip.ZipInputStream
import kotlin.io.path.createDirectories
import kotlin.io.path.createDirectory
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream

class ZucchettiPackageUnpacker {

    fun unpack(compressedFilePath: Path, destinationDirectory: Path): Boolean {
        return try {
            //create directory if not exists
            destinationDirectory.createDirectory();
            //get inputStream from compressedFile that used with ZipInputStream
            ZipInputStream(compressedFilePath.inputStream()).use { zipInputStream ->
                //use var because is mutable
                var entry = zipInputStream.nextEntry
                while (entry != null) {
                    val finalPath = destinationDirectory.resolve(entry.name)
                    if (entry.isDirectory) {
                        finalPath.createDirectories()
                    } else {
                        //? Safe call kotlin operator (avoid null values)
                        finalPath.parent?.createDirectories();
                        finalPath.outputStream().use { outputStream ->
                            zipInputStream.copyTo(outputStream)
                        }
                    }
                    zipInputStream.closeEntry()
                    entry = zipInputStream.nextEntry
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}
