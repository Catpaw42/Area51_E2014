package dto;

public class DTOUser {
	
	private String username;
	private String password;
	private String department;
	private String name;
	/**
	 * 
	 * @param username String
	 * @param password String
	 * @param department String - afdeling for ansat
	 * @param name String - er ikke nødvendig, men hvis ønsket kan den være med, som så kan bruges til at se hvem er logget på
	 */
	public DTOUser(String username, String password, String department, String name){
		this.username = username;
		this.password = password;
		this.department = department;
		this.name = name;
	}
	/**
	 * nuværende mainconstructor da name ikke er en nødvendighed
	 * @param username String
	 * @param password String
	 * @param department String
	 */
	public DTOUser(String username, String password, String department){
		this(username, password, department, null);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
