package database.interfaces;

import database.dto.Bruger;

public interface IDataBaseController
{
	boolean validateUser(Bruger loginUser) throws DatabaseException;

	Bruger getUserFromUsername(String username) throws DatabaseException, UserNotFoundException;
	
	public class DatabaseException extends Exception
	{

	}
	
	public class UserNotFoundException extends Exception
	{

	}
}
