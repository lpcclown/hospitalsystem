package edu.fiu.dbms.hospital.basicentity;

/**
 * @author LIU
 *
 */
public class Inquiry {
	@Override
	public String toString() {
		return "Inquiry [inquiryDate=" + inquiryDate + "]";
	}

	private String inquiryDate;

	public Inquiry(String inquiryDate) {
		super();
		this.inquiryDate = inquiryDate;
	}

	public String getInquiryDate() {
		return inquiryDate;
	}

	public void setInquiryDate(String inquiryDate) {
		this.inquiryDate = inquiryDate;
	}

	public Inquiry() {
		super();
		// TODO Auto-generated constructor stub
	}

}
