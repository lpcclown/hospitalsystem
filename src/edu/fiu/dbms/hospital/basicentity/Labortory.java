package edu.fiu.dbms.hospital.basicentity;

public class Labortory {

	@Override
	public String toString() {
		return "Labortory [labortoryID=" + labortoryID + ", takeLabDate=" + takeLabDate + ", labortoryCost="
				+ labortoryCost + "]";
	}

	private String labortoryID;
	private String takeLabDate;
	private String labortoryCost;

	public Labortory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getLabortoryID() {
		return labortoryID;
	}

	public void setLabortoryID(String labortoryID) {
		this.labortoryID = labortoryID;
	}

	public String getTakeLabDate() {
		return takeLabDate;
	}

	public void setTakeLabDate(String takeLabDate) {
		this.takeLabDate = takeLabDate;
	}

	public String getLabortoryCost() {
		return labortoryCost;
	}

	public void setLabortoryCost(String labortoryCost) {
		this.labortoryCost = labortoryCost;
	}

	public Labortory(String labortoryID, String takeLabDate, String labortoryCost) {
		super();
		this.labortoryID = labortoryID;
		this.takeLabDate = takeLabDate;
		this.labortoryCost = labortoryCost;
	}
}
