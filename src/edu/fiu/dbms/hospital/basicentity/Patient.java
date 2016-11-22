package edu.fiu.dbms.hospital.basicentity;

public class Patient {

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", ssn=" + ssn + ", firstname=" + firstname + ", middleInitial="
				+ middleInitial + ", lastname=" + lastname + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", balanceAmount=" + balanceAmount + ", insurancePolicyNumber="
				+ insurancePolicyNumber + ", gender=" + gender + ", birthdate=" + birthdate + "]";
	}

	public Patient(String ssn, String firstname, String middleInitial, String lastname, String email,
			String phoneNumber, String password, String balanceAmount, String insurancePolicyNumber, String gender,
			String birthdate) {
		super();
		this.ssn = ssn;
		this.firstname = firstname;
		this.middleInitial = middleInitial;
		this.lastname = lastname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.balanceAmount = balanceAmount;
		this.insurancePolicyNumber = insurancePolicyNumber;
		this.gender = gender;
		this.birthdate = birthdate;
	}

	public Patient() {
	}

	private int patientId;
	private String ssn;
	private String firstname;
	private String middleInitial;
	private String lastname;
	private String email;
	private String phoneNumber;
	private String password;
	private String balanceAmount;
	private String insurancePolicyNumber;
	private String gender;
	private String birthdate;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String value) {
		this.balanceAmount = value;
	}

	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}

	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
}