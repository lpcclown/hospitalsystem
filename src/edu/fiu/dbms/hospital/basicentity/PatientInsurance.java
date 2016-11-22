package edu.fiu.dbms.hospital.basicentity;

public class PatientInsurance {

	@Override
	public String toString() {
		return "PatientInsurance [insurancePolicyNo=" + insurancePolicyNo + ", insuranceStatus=" + insuranceStatus
				+ ", coveragePercent=" + coveragePercent + ", coverageAmount=" + coverageAmount + "]";
	}

	private String insurancePolicyNo;
	private String insuranceStatus;
	private String coveragePercent;
	private String coverageAmount;

	public PatientInsurance(String insurancePolicyNo, String insuranceStatus, String coveragePercent,
			String coverageAmount) {
		super();
		this.insurancePolicyNo = insurancePolicyNo;
		this.insuranceStatus = insuranceStatus;
		this.coveragePercent = coveragePercent;
		this.coverageAmount = coverageAmount;
	}

	public String getInsurancePolicyNo() {
		return insurancePolicyNo;
	}

	public void setInsurancePolicyNo(String insurancePolicyNo) {
		this.insurancePolicyNo = insurancePolicyNo;
	}

	public String getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(String insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public String getCoveragePercent() {
		return coveragePercent;
	}

	public void setCoveragePercent(String coveragePercent) {
		this.coveragePercent = coveragePercent;
	}

	public String getCoverageAmount() {
		return coverageAmount;
	}

	public void setCoverageAmount(String coverageAmount) {
		this.coverageAmount = coverageAmount;
	}

	public PatientInsurance() {
		super();
		// TODO Auto-generated constructor stub
	}

}
