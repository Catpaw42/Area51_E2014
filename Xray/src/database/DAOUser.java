package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.interfaces.IDAOUser;
import dto.DTOUser;

public class DAOUser extends DataSourceConnector implements IDAOUser  {
	
	public DTOUser storeUser(){
		//TODO Fix!
		try (Connection c = getConnection();){
			String query = "INSERT ";
			try (PreparedStatement pst= c.prepareStatement(query)){
				pst.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		return null;
	}

}
