package io.github.alessandrorenzi.island.kotlin.unpacker

import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.ZipInputStream
import kotlin.io.path.inputStream

class ZucchettiPackageUnpacker {

    fun unpack(compressedFilePath: Path, destinationDirectory: Any): Boolean {
 // 1. `compressedFilePath.inputStream()` genera un oggetto `InputStream` (flusso di byte grezzi).
// 2. `.use { fileInputStream ->` assegna quell'InputStream alla variabile `fileInputStream`.
        compressedFilePath.inputStream().use { fileInputStream ->

            // PASSAGGIO 2
            // 1. `ZipInputStream(fileInputStream)` prende quei byte grezzi e li incapsula in un decodificatore ZIP.
            // 2. `.use { zipInputStream ->` assegna il decodificatore ZIP alla variabile `zipInputStream`.
            ZipInputStream(fileInputStream).use { zipInputStream ->

                // PASSAGGIO 3
                // Qui sei nel blocco più interno. Hai a disposizione ENTRAMBI gli oggetti,
                // ma per leggere i file ZIP devi invocare i metodi su `zipInputStream`:
                var entry = zipInputStream.nextEntry
        return false
    }


}
