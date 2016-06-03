package co.uk.taurasystems.db;

public class H2InsertStatement extends H2Statement {

	private Type type;
	private String tableName;
	private String[] columnNames;
	private String[] values;
	
	public H2InsertStatement(Type type, String tableName, String[] columnNames, String[] values) {
		this.type = type;
		this.tableName = tableName;
		this.columnNames = columnNames;
		this.values = values;
	}
	
	public String getRawString() {
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
