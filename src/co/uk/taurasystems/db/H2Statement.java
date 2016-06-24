package co.uk.taurasystems.db;

public class H2Statement {

	public static String getInsertStatement(String tableName, String[] columnNames, String[] values) {
		String statement = "INSERT INTO ";
		statement += tableName + "(";
		for (int i = 0; i < columnNames.length; i++) {
			String name = columnNames[i];
			statement += name;
			if (i + 1 < columnNames.length) statement += ",";
		}
		statement += ") ";
		statement += "values" + "(";
		for (int i = 0; i < values.length; i++) {
			String name = values[i];
			statement += name;
			if (i + 1 < values.length) statement += ",";
		}
		statement += ")";
		return statement;
	}
}
