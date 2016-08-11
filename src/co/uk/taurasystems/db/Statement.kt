package co.uk.taurasystems.db

object Statement {

    @JvmStatic fun getInsertStatement(tableName: String, columnNames: Array<String>, values: Array<String>): String? {
        if (columnNames.size != values.size) {
            println("Number of columns does not match number of values...")
            return null
        }
        var statement = "INSERT INTO "
        statement += tableName + "("
        for (i in columnNames.indices) {
            val name = columnNames[i]
            statement += name
            if (i + 1 < columnNames.size) statement += ","
        }
        statement += ") "
        statement += "values" + "("
        for (i in values.indices) {
            val name = values[i]
            statement += name
            if (i + 1 < values.size) statement += ","
        }
        statement += ")"
        return statement
    }
}
