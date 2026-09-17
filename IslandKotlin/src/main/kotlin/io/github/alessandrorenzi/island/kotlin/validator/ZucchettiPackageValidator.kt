package io.github.alessandrorenzi.island.kotlin.validator

private val ALLOWED_FILE_EXTENSION = setOf("zip", "7z", "gz")

class ZucchettiPackageValidator {

    fun isValid(fileNameWithPath: String): Boolean {
        val result = ALLOWED_FILE_EXTENSION.any { extension -> fileNameWithPath.endsWith(extension, ignoreCase = true) }
        return result
    }
}
