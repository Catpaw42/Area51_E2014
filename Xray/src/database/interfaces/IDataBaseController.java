package database.interfaces;

import dto.DTOUser;

public interface IDataBaseController
{
	boolean validateUser(DTOUser loginUser) throws DatabaseException;

	DTOUser getUserFromUsername(String username) throws DatabaseException, UserNotFoundException;
	
	public class DatabaseException extends Exception
	{

	}
	
	public class UserNotFoundException extends Exception
	{

	}
}
