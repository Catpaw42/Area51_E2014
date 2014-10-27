package dto;

public class DTOPatient {
	
	private int patientId;
	private String cpr;
	private String name;
	private String address;
	private String phoneNo;
	private static int NOID = -1;
	
	
	public DTOPatient(int patientId, String cpr, String name, String address, String phoneNo){
		this.setPatientId(patientId < 0 ? NOID : patientId);
		this.cpr = cpr;
		this.name = name;
		this.address = address;
		this.setPhoneNo(phoneNo);
	}
	
	public DTOPatient(String cpr, String name, String address){
		this(NOID, cpr, name, address, null);
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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
