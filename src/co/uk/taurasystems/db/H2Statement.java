package co.uk.taurasystems.db;

public class H2Statement {

	public enum Type {
		INSERT("INSERT INTO ");
		private final String text;
		private Type(String text) {
			this.text = text;
		}
		@Override
		public String toString() {
			return text;
		}
	}
	
	protected String getRawString() {return null;}
}
