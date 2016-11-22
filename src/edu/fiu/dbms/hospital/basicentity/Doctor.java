package edu.fiu.dbms.hospital.basicentity;

public class Doctor {
	@Override
	public String toString() {
		return "Doctor [doctorID=" + doctorID + ", serviceTime=" + serviceTime + ", doctorCost=" + doctorCost + "]";
	}

	private String doctorID;
	private String serviceTime;
	private String doctorCost;

	public Doctor(String doctorID, String serviceTime, String doctorCost) {
		super();
		this.doctorID = doctorID;
		this.serviceTime = serviceTime;
		this.doctorCost = doctorCost;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getDoctorCost() {
		return doctorCost;
	}

	public void setDoctorCost(String doctorCost) {
		this.doctorCost = doctorCost;
	}

	public Doctor() {
		super();
	}

}
