package dto;

public class DTOexRequest {
	
	public enum Status{PENDING, CANCELED, APPROVED, DECLINED};
	
	private int asessorId;
	private Status status;
	private int patientId;
	private int requestorId;
	
	public DTOexRequest(int assessorId, int patientId, int requestorId, Status status){
		
	}

}
