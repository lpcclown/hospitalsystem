package edu.fiu.dbms.hospital.basicentity;

public class Creditcard {

	@Override
	public String toString() {
		return "Creditcard [creditcardId=" + creditcardId + ", creditcardNumber=" + creditcardNumber + ", billAddress="
				+ billAddress + ", ifPreferedToUse=" + ifPreferedToUse + ", ifMailPaperBill=" + ifMailPaperBill
				+ ", patientid=" + patientid + "]";
	}

	private int creditcardId;
	private String creditcardNumber;
	private String billAddress;
	private boolean ifPreferedToUse;
	private boolean ifMailPaperBill;
	private int patientid;

	public Creditcard(int creditcardId, String creditcardNumber, String billAddress, boolean ifPreferedToUse,
			boolean ifMailPaperBill) {
		super();
		this.creditcardId = creditcardId;
		this.creditcardNumber = creditcardNumber;
		this.billAddress = billAddress;
		this.ifPreferedToUse = ifPreferedToUse;
		this.ifMailPaperBill = ifMailPaperBill;
	}

	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}

	public Creditcard() {
	}

	public Creditcard(String creditcardNumber, String billAddress, String ifPreferedToUse, String ifMailPaperBill) {
		super();
		this.creditcardNumber = creditcardNumber;
		this.billAddress = billAddress;
		this.ifPreferedToUse = ifPreferedToUse.equals("Yes") ? true : false;
		this.ifMailPaperBill = ifMailPaperBill.equals("Yes") ? true : false;
	}

	public int getCreditcardId() {
		return creditcardId;
	}

	public Creditcard(int creditcardId, String creditcardNumber, String billAddress, boolean ifPreferedToUse,
			boolean ifMailPaperBill, int patientid) {
		super();
		this.creditcardId = creditcardId;
		this.creditcardNumber = creditcardNumber;
		this.billAddress = billAddress;
		this.ifPreferedToUse = ifPreferedToUse;
		this.ifMailPaperBill = ifMailPaperBill;
		this.patientid = patientid;
	}

	public void setCreditcardId(int creditcardId) {
		this.creditcardId = creditcardId;
	}

	public String getCreditcardNumber() {
		return creditcardNumber;
	}

	public void setCreditcardNumber(String creditcardNumber) {
		this.creditcardNumber = creditcardNumber;
	}

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public boolean isIfPreferedToUse() {
		return ifPreferedToUse;
	}

	public void setIfPreferedToUse(boolean ifPreferedToUse) {
		this.ifPreferedToUse = ifPreferedToUse;
	}

	public boolean isIfMailPaperBill() {
		return ifMailPaperBill;
	}

	public void setIfMailPaperBill(boolean ifMailPaperBill) {
		this.ifMailPaperBill = ifMailPaperBill;
	}

}
