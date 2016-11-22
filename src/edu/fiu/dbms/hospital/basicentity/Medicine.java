package edu.fiu.dbms.hospital.basicentity;

public class Medicine {

	@Override
	public String toString() {
		return "Medicine [medicineID=" + medicineID + ", thresholdInventory=" + thresholdInventory + ", currentAmount="
				+ currentAmount + ", restoreReminder=" + restoreReminder + "]";
	}

	private String medicineID;
	private String thresholdInventory;
	private String currentAmount;
	private String restoreReminder;

	public String getMedicineID() {
		return medicineID;
	}

	public void setMedicineID(String medicineID) {
		this.medicineID = medicineID;
	}

	public String getThresholdInventory() {
		return thresholdInventory;
	}

	public void setThresholdInventory(String thresholdInventory) {
		this.thresholdInventory = thresholdInventory;
	}

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getRestoreReminder() {
		return restoreReminder;
	}

	public void setRestoreReminder(String restoreReminder) {
		this.restoreReminder = restoreReminder;
	}

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicine(String medicineID, String thresholdInventory, String currentAmount, String restoreReminder) {
		super();
		this.medicineID = medicineID;
		this.thresholdInventory = thresholdInventory;
		this.currentAmount = currentAmount;
		this.restoreReminder = restoreReminder;
	}
}
