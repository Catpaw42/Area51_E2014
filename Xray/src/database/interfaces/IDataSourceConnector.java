package database.interfaces;

import javax.naming.NamingException;

public interface IDataSourceConnector {


	
	@SuppressWarnings("serial")
	public class ConnectionException extends Exception {
			String errorMessage = null;

		public ConnectionException(String string) {
			this.errorMessage = string;
		}
		
	}

}