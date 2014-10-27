package dto;

public class DTOPatient {
	
	private String cpr;
	private String name;
	private String address;
	
	public DTOPatient(String cpr, String name, String address){
		this.cpr = cpr;
		this.name = name;
		this.address = address;
	}

	public String getCpr() {
		return cpr;
	}

	public void setCPR(String cpr) {
		this.cpr = cpr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
