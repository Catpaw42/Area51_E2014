package database.dto;

import java.sql.Connection;

import database.DataSourceConnector;
import database.dao.RettighederDao;
import database.dao.mysql.RettighederDaoImpl;
import database.interfaces.IDataSourceConnector.ConnectionException;



/**
 *  En bruger, der også har sine rettigheder
 * @author Martin
 * TODO test
 */
public class RettighedsBruger extends Bruger {
	
	private Rettigheder[] rettigheder;
	protected static final String GET_RETTIGHEDER = "bruger_id=?";

	
	public RettighedsBruger(Bruger bruger) {
		Connection conn = null;
		try {
			conn = DataSourceConnector.getConnection();
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RettighederDao retDao = new RettighederDaoImpl(conn);
		this.rettigheder = retDao.findDynamic(GET_RETTIGHEDER, 0, -1, new Object[]{bruger.getBrugerId()});
		
	}

	public Rettigheder[] getRettigheder() {
		return rettigheder;
	}


	public void setRettigheder(Rettigheder[] rettigheder) {
		this.rettigheder = rettigheder;
	}
	
	public boolean harRettighed(Rettigheder.Rettighed rettighed){
		for(int i = 0; i<rettigheder.length; i++){
			if(rettighed.equals(rettigheder[i])){
				return true;
			}
		}
		return false;
	}
	

	
	
}
