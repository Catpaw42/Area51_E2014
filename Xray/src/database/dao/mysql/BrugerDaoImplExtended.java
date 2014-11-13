package database.dao.mysql;

import java.sql.Connection;

import database.dao.RettighederDao;
import database.dto.Bruger;
import database.dto.Rettigheder;
import database.dto.RettighedsBruger;

public class BrugerDaoImplExtended extends BrugerDaoImpl {

	protected static final String USER_LOGIN_CONDITION = "bruger_navn=? AND kodeord=?";
	protected static final String GET_RETTIGHEDER = "bruger_id=?";

	public BrugerDaoImplExtended(Connection conn) {
		super(conn);
	}

	public boolean validate(String brugernavn, String kodeord){

		Bruger[] br = findDynamic(USER_LOGIN_CONDITION, 0, -1, new Object[]{brugernavn, kodeord});
		if(br.length != 0) return true;

		return false;
	}
	
	public Bruger findByUserName(String brugernavn, String kodeord){
		Bruger[] br = findDynamic(USER_LOGIN_CONDITION, 0, -1, new Object[]{brugernavn, kodeord});
		if(br.length != 0 || br[0].equals(null)){
			RettighederDao rDao = new RettighederDaoImpl(conn);
			Rettigheder[] rettigheder = rDao.findDynamic(GET_RETTIGHEDER, 0, -1, new Object[]{br[0].getBrugerId()});
			br[0].setRettigheder(rettigheder);
			return br[0];
		}
		return null;
	}
	
    public Bruger findByPrimaryKey( int brugerId ) {
    	Bruger b = findOne( PK_CONDITION, brugerId);
    	RettighederDao rDao = new RettighederDaoImpl(conn);
    	Rettigheder[] rettigheder = rDao.findDynamic(GET_RETTIGHEDER, 0, -1, new Object[]{b.getBrugerId()});
    	b.setRettigheder(rettigheder);
        return b;
    }
	
}
