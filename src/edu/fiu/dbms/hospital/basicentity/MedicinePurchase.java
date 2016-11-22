package edu.fiu.dbms.hospital.basicentity;

public class MedicinePurchase {

	@Override
	public String toString() {
		return "MedicinePurchase [medicineID=" + medicineID + ", medicineUnitPrice=" + medicineUnitPrice
				+ ", medicinePriceDate=" + medicinePriceDate + ", medicineAmount=" + medicineAmount + "]";
	}

	private String medicineID;
	private String medicineUnitPrice;
	private String medicinePriceDate;
	private String medicineAmount;

	public MedicinePurchase(String medicineID, String medicineUnitPrice, String medicinePriceDate,
			String medicineAmount) {
		super();
		this.medicineID = medicineID;
		this.medicineUnitPrice = medicineUnitPrice;
		this.medicinePriceDate = medicinePriceDate;
		this.medicineAmount = medicineAmount;
	}

	public String getMedicineID() {
		return medicineID;
	}

	public void setMedicineID(String medicineID) {
		this.medicineID = medicineID;
	}

	public String getMedicineUnitPrice() {
		return medicineUnitPrice;
	}

	public void setMedicineUnitPrice(String medicineUnitPrice) {
		this.medicineUnitPrice = medicineUnitPrice;
	}

	public String getMedicinePriceDate() {
		return medicinePriceDate;
	}

	public void setMedicinePriceDate(String medicinePriceDate) {
		this.medicinePriceDate = medicinePriceDate;
	}

	public String getMedicineAmount() {
		return medicineAmount;
	}

	public void setMedicineAmount(String medicineAmount) {
		this.medicineAmount = medicineAmount;
	}

	public MedicinePurchase() {
		super();
		// TODO Auto-generated constructor stub
	}

}
