package dto;

import java.sql.Timestamp;
import java.util.Date;

public class DTOexRequest {
	
	public enum Status{PENDING, CANCELED, APPROVED, DECLINED};
	
	private int rekvisitionId;
	private int asessorId;
	private Status status;
	private int patientId;
	private int requestorId;
	private Timestamp dateCreated;
	private Timestamp dateCanceled;
	private Timestamp dateApproved;
	private Timestamp dateDeclined;
	
	//##############REKVISITION DATA###############
//	private String
	
	//#############################################
	
	private static int NOID = -1;
	
	public DTOexRequest(int rekvisitionId, int assessorId, int patientId, int requestorId, Status status, Timestamp dateCreated, Timestamp dateCanceled, Timestamp dateApproved, Timestamp dateDeclined){
		this.rekvisitionId = rekvisitionId <= 0 ? NOID : rekvisitionId;
		this.asessorId = assessorId;
		this.patientId = patientId;
		this.requestorId = requestorId;
		this.status = status == null ? Status.PENDING : status;
		this.dateCreated = (dateCreated == null) ? new Timestamp(new Date().getTime()) : dateCreated;
		this.dateCanceled = dateCanceled;
		this.dateApproved = dateApproved;
		this.dateDeclined = dateDeclined;
	}

}
