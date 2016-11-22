package edu.fiu.dbms.hospital.basicentity;

public class Bill {
	@Override
	public String toString() {
		return "Bill [billDate=" + billDate + ", ifPaid=" + ifPaid + "]";
	}

	private String billDate;
	private String ifPaid;

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bill(String billDate, String ifPaid) {
		super();
		this.billDate = billDate;
		this.ifPaid = ifPaid;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getIfPaid() {
		return ifPaid;
	}

	public void setIfPaid(String ifPaid) {
		this.ifPaid = ifPaid;
	}
}
