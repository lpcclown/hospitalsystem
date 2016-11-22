package edu.fiu.dbms.hospital.basicentity;

public class Nurse {
	private String nurseId;
	private String checkInDate;
	private String nursecost;

	public Nurse(String id, String checkInDate, String nurseCost) {
		super();
		this.nurseId = id;
		this.checkInDate = checkInDate;
		this.nursecost = nurseCost;
	}

	public String getId() {
		return nurseId;
	}

	public void setId(String id) {
		this.nurseId = id;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getNursecost() {
		return nursecost;
	}

	public void setNursecost(String nurseCose) {
		this.nursecost = nurseCose;
	}

	public Nurse() {

	}

	@Override
	public String toString() {
		return "Nurse [nurseId=" + nurseId + ", checkInDate=" + checkInDate + ", nurseCose=" + nursecost + "]";
	}

}
