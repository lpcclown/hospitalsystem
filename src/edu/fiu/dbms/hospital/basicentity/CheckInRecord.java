package edu.fiu.dbms.hospital.basicentity;

public class CheckInRecord {
	@Override
	public String toString() {
		return "CheckInRecord [checkInRecordId=" + checkInRecordId + ", patientId=" + patientId + ", nurseId=" + nurseId
				+ ", height=" + height + ", weight=" + weight + ", bloodPressure=" + bloodPressure
				+ ", bodyTemperature=" + bodyTemperature + ", checkInTime=" + checkInTime + "]";
	}

	private int checkInRecordId;
	private int patientId;
	private int nurseId;
	private float height;
	private float weight;
	private String bloodPressure;
	private String bodyTemperature;
	private String checkInTime;

	public CheckInRecord() {
	}

	public CheckInRecord(int patientId, int nurseId, float height, float weight, String bloodPressure,
			String bodyTemperature, String checkInTime) {
		super();
		this.patientId = patientId;
		this.nurseId = nurseId;
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.bodyTemperature = bodyTemperature;
		this.checkInTime = checkInTime;
	}

	public int getCheckInRecordId() {
		return checkInRecordId;
	}

	public void setCheckInRecordId(int checkInRecordId) {
		this.checkInRecordId = checkInRecordId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(String bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public String getCheckInTime() {
		return checkInTime;
	}

	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}

}
