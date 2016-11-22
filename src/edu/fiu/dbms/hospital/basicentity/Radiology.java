package edu.fiu.dbms.hospital.basicentity;

public class Radiology {

	@Override
	public String toString() {
		return "Radiology [radiologyID=" + radiologyID + ", takeRadioDate=" + takeRadioDate + ", radiologyCost="
				+ radiologyCost + "]";
	}

	private String radiologyID;
	private String takeRadioDate;
	private String radiologyCost;

	public Radiology(String radiologyID, String takeRadioDate, String radiologyCost) {
		super();
		this.radiologyID = radiologyID;
		this.takeRadioDate = takeRadioDate;
		this.radiologyCost = radiologyCost;
	}

	public String getRadiologyID() {
		return radiologyID;
	}

	public void setRadiologyID(String radiologyID) {
		this.radiologyID = radiologyID;
	}

	public String getTakeRadioDate() {
		return takeRadioDate;
	}

	public void setTakeRadioDate(String takeRadioDate) {
		this.takeRadioDate = takeRadioDate;
	}

	public String getRadiologyCost() {
		return radiologyCost;
	}

	public void setRadiologyCost(String radiologyCost) {
		this.radiologyCost = radiologyCost;
	}

	public Radiology() {
		super();
		// TODO Auto-generated constructor stub
	}

}
