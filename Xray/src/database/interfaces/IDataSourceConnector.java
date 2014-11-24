package database.interfaces;


public interface IDataSourceConnector {


	
	@SuppressWarnings("serial")
	public class ConnectionException extends Exception {
			String errorMessage = null;

		public ConnectionException(String string) {
			this.errorMessage = string;
		}
		
	}

}