package dto;

import java.util.ArrayList;

public class DTOUser {
	public enum Permission{ADMIN, ASSESSOR, BOOKING, REQUEST};
	
	
	private int userId;
	private String username;
	private String password;
	private String department;
	private String name;
	private ArrayList<Permission> permissions;
	
	@SuppressWarnings("serial")
	private static ArrayList<Permission> defaultPermission = new ArrayList<Permission>(){{add(Permission.REQUEST);}};
	private static int NOID = -1; // if newly created user, it is not possible to set id, so this is used. ID is set in database with autoincrement
	
	/**
	 * 
	 * @param userid
	 * @param username
	 * @param password
	 * @param department
	 * @param name
	 * @param permissions
	 */
	public DTOUser(int userid, String username, String password, String department, String name, ArrayList<Permission> permissions){
		this.userId = userid;
		this.username = username;
		this.password = password;
		this.department = department;
		this.name = name;
		this.setPermissions((permissions == null) ? defaultPermission : permissions);
	}
	/**
	 * creates a user that has no userid. Userid is set to -1 and permissions are set to default permissions which is Permission.REKVISITION
	 * @param username String
	 * @param password String
	 * @param department String
	 * @param name String - is not necessary but is handy if the user is Assessor
	 */
	public DTOUser(String username, String password, String department, String name){
		this(NOID, username, password, department, name, null);
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
	public ArrayList<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(ArrayList<Permission> permissions) {
		this.permissions = permissions;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
