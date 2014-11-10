package database.dao.mysql;

import java.sql.Connection;

import database.dto.Bruger;

public class BrugerDaoImplExtended extends BrugerDaoImpl {

	protected static final String VALIDATE_USER_CONDITION = "bruger_navn=? AND kodeord=?";

	public BrugerDaoImplExtended(Connection conn) {
		super(conn);
	}

	public boolean validate(String brugernavn, String kodeord){

		Bruger[] br = findDynamic(VALIDATE_USER_CONDITION, 0, -1, new Object[]{brugernavn, kodeord});
		if(br.length != 0) return true;

		return false;
	}
}
