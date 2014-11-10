package tests;
import com.spoledge.audao.db.dao.DaoException;

import database.dao.BrugerDao;
import database.dao.mysql.BrugerDaoImpl;
import database.dto.Bruger;
import database.interfaces.IDataSourceConnector.ConnectionException;
import database.DataSourceConnector;

import java.sql.*;
// TODO Make this work
public class DatabaseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BrugerDao brugerdao = new BrugerDaoImpl(conn);
		Bruger bruger = new Bruger();
		try {
			brugerdao.insert(bruger);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}