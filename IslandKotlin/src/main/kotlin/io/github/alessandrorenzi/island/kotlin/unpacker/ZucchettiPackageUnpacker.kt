package io.github.alessandrorenzi.island.kotlin.unpacker

import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import java.nio.file.Path
import java.util.zip.ZipInputStream
import kotlin.io.path.createDirectories
import kotlin.io.path.createDirectory
import kotlin.io.path.extension
import kotlin.io.path.inputStream
import kotlin.io.path.outputStream

class ZucchettiPackageUnpacker {

    fun unpack(compressedFilePath: Path, destinationDirectory: Path): Boolean {
        return try {
            //create directory if not exists
            destinationDirectory.createDirectory()
            //get inputStream from compressedFile that used with ZipInputStream
            if(compressedFilePath.extension == "gz"){
                TarArchiveInputStream(compressedFilePath.inputStream()).use { gzInputStream ->
                    var entrygz = gzInputStream.nextEntry
                    while (entrygz != null) {
                        val finalPathGz = destinationDirectory.resolve(entrygz.name)
                        if (entrygz.isDirectory){
                            finalPathGz.createDirectories()
                        }else
                            finalPathGz.parent?.createDirectories()
                            finalPathGz.outputStream().use { outputStream ->
                                gzInputStream.copyTo(outputStream)
                            }
                    //NOT COMPLETED***

                    }
                }
            }else {
                ZipInputStream(compressedFilePath.inputStream()).use { zipInputStream ->
                    //use var because is mutable
                    var entry = zipInputStream.nextEntry
                    while (entry != null) {
                        val finalPath = destinationDirectory.resolve(entry.name)
                        if (entry.isDirectory) {
                            finalPath.createDirectories()
                        } else {
                            //? Safe call kotlin operator (avoid null values)
                            finalPath.parent?.createDirectories()
                            finalPath.outputStream().use { outputStream ->
                                zipInputStream.copyTo(outputStream)
                            }
                        }
                        zipInputStream.closeEntry()
                        entry = zipInputStream.nextEntry
                    }
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}
