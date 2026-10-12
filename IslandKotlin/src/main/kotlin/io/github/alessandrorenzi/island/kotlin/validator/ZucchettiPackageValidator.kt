package io.github.alessandrorenzi.island.kotlin.validator


import kotlin.io.path.Path
import kotlin.io.path.exists
import kotlin.io.path.isRegularFile

private val ALLOWED_FILE_EXTENSION = setOf("zip", "7z", "gz")

class ZucchettiPackageValidator {

    fun isValid(fileNameWithPath: String): Boolean {
        val result = ALLOWED_FILE_EXTENSION.any { extension -> fileNameWithPath.endsWith(extension, ignoreCase = true) }
        return result
    }

    fun exists(fileNameWithPath: String): Boolean {
        val completePathWithFile = Path(fileNameWithPath)
        return completePathWithFile.exists() && completePathWithFile.isRegularFile()
    }

/*    fun exists(fileNameWithPath: String): Boolean =
        Path(fileNameWithPath).let { path ->
            path.exists() && path.isRegularFile()
        }
*/
}
