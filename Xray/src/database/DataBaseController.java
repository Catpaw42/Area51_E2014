package database;

import java.sql.Connection;

import javax.sql.DataSource;

import database.DataSourceConnector;
import database.dao.mysql.BrugerDaoImpl;
import database.dto.Bruger;
import database.interfaces.IDataBaseController;

public class DataBaseController implements IDataBaseController {


	@Override
	public Bruger getUserFromUsername(String username)
			throws DatabaseException, UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateUser(Bruger loginUser) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
