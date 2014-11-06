package database;

import database.dto.Bruger;
import database.interfaces.IDataBaseController;

public class DataBaseController implements IDataBaseController {

	@Override
	public boolean validateUser(Bruger loginUser) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bruger getUserFromUsername(String username)
			throws DatabaseException, UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
