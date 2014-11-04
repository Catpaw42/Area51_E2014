package database.dao.mysql;

import com.spoledge.audao.db.dao.DaoException;

public class DuplicateEntryException extends DaoException {

	DuplicateEntryException(String message) {
		super(message);
	}

}
