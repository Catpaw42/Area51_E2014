package database.dao.mysql;

import java.sql.Connection;

import database.dto.Bruger;

public class BrugerDaoImplExtended extends BrugerDaoImpl {

	protected static final String USER_LOGIN_CONDITION = "bruger_navn=? AND kodeord=?";

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
			return br[0];
		}
		return null;
	}
	
}
