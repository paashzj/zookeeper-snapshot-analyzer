package resources

import java.util.Locale

val strings = when (Locale.getDefault().language) {
    "en" -> EnStrings
    else -> EnStrings
}

interface Strings {
    val title: String
}

object EnStrings : Strings {
    override val title: String
        get() = "zookeeper snapshot analyzer"
}
