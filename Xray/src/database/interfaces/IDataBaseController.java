package database.interfaces;

import dto.DTOUser;

public interface IDataBaseController {

	boolean validateUser(DTOUser loginUser);

}
