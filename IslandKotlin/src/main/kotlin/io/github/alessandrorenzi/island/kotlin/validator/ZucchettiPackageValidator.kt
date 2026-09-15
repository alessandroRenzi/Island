package io.github.alessandrorenzi.island.kotlin.validator

class ZucchettiPackageValidator {
    fun isValid(fileNameWithPath: String): Boolean {
        val result = fileNameWithPath.endsWith("zip", ignoreCase = true) ||
                     fileNameWithPath.endsWith("7z", ignoreCase = true)
        return result
    }
}
