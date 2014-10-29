package database;

import database.interfaces.IDataBaseController;
import dto.DTOUser;

public class DataBaseController implements IDataBaseController {

	@Override
	public boolean validateUser(DTOUser loginUser) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DTOUser getUserFromUsername(String username)
			throws DatabaseException, UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
