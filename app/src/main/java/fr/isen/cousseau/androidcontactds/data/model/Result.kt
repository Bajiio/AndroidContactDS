package fr.isen.cousseau.androidcontactds.data.model

data class Result(var results: Array<Contact>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result

        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        return results.contentHashCode()
    }
}
