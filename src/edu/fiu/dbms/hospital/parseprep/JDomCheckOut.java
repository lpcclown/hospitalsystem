package edu.fiu.dbms.hospital.parseprep;

import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import edu.fiu.dbms.hospital.basicentity.Bill;
import edu.fiu.dbms.hospital.basicentity.Creditcard;
import edu.fiu.dbms.hospital.basicentity.Doctor;
import edu.fiu.dbms.hospital.basicentity.Inquiry;
import edu.fiu.dbms.hospital.basicentity.Labortory;
import edu.fiu.dbms.hospital.basicentity.Medicine;
import edu.fiu.dbms.hospital.basicentity.MedicinePurchase;
import edu.fiu.dbms.hospital.basicentity.Nurse;
import edu.fiu.dbms.hospital.basicentity.Patient;
import edu.fiu.dbms.hospital.basicentity.PatientInsurance;
import edu.fiu.dbms.hospital.basicentity.Radiology;
import edu.fiu.dbms.hospital.basicentity.Room;
import edu.fiu.dbms.hospital.util.IXmlDocument;

public class JDomCheckOut implements IXmlDocument {
	public Patient patientOut = new Patient();
	public Creditcard creditcardsOut = new Creditcard();
	public Nurse nurseOut = new Nurse();
	public Doctor doctorOut = new Doctor();
	public Room roomOut = new Room();
	public Labortory labortoryOut = new Labortory();
	public Radiology radiologyOut = new Radiology();
	public Medicine medicineOut = new Medicine();
	public MedicinePurchase medicinePurchaseOut = new MedicinePurchase();
	public PatientInsurance patientInsuranceOut = new PatientInsurance();
	public Bill billOut = new Bill();
	public Inquiry inquiryOut = new Inquiry();

	public void parserXml(String fileName) {
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document document = builder.build(fileName);
			Element checkOutRecord = document.getRootElement();
			List<Element> checkOutRecordInfo = checkOutRecord.getChildren();
			Element patient = checkOutRecord.getChild("Patient");
			Element creditcard = patient.getChild("creditcards");
			Element nurse = checkOutRecord.getChild("Nurse");
			Element doctor = checkOutRecord.getChild("Doctor");
			Element room = checkOutRecord.getChild("Room");
			Element labortory = checkOutRecord.getChild("Labortory");
			Element radiology = checkOutRecord.getChild("Radiology");
			Element medicine = checkOutRecord.getChild("Medicine");
			Element medicinePurchase = checkOutRecord.getChild("MedicinePurchase");
			Element patientInsurance = checkOutRecord.getChild("PatientInsurance");
			Element bill = checkOutRecord.getChild("Bill");
			Element inquiry = checkOutRecord.getChild("Inquiry");
			fillInPatient(patient);
			fillInCreditcard(creditcard);
			fillInNurseInfo(nurse);
			fillInDoctorInfo(doctor);
			fillInRoomInfo(room);
			fillInLabortory(labortory);
			fillInRadiology(radiology);
			fillInMedicine(medicine);
			fillInMedicinePurchase(medicinePurchase);
			fillInPatientInsurance(patientInsurance);
			fillInBill(bill);
			fillInInquiry(inquiry);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fillInPatient(Element patient) {
		patientOut = new Patient(patient.getChild("SSN").getValue(), patient.getChild("FIRSTNAME").getValue(),
				patient.getChild("MIDDLENAME").getValue(), patient.getChild("LASTNAME").getValue(),
				patient.getChild("EMAIL").getValue(), patient.getChild("PHONENUMBER").getValue(),
				patient.getChild("PASSWORD").getValue(), patient.getChild("BALANCEAMOUNT").getValue(),
				patient.getChild("INSURANCEPOLICYNO").getValue(), patient.getChild("GENDER").getValue(),
				patient.getChild("DATEOFBIRTH").getValue());

	}

	private void fillInCreditcard(Element creditcard) {
		creditcardsOut = new Creditcard(creditcard.getChild("CREDITCARDNUMBER").getValue(),
				creditcard.getChild("BILLADDRESS").getValue(), creditcard.getChild("IFPREFEREDTOUSE").getValue(),
				creditcard.getChild("IFMAILPAPERBILL").getValue());
	}

	private void fillInNurseInfo(Element nurse) {
		nurseOut.setId(nurse.getChild("NurseID").getValue());
		// nurseOut.setCheckInDate(nurse.getChild("CheckInTime").getValue());
		nurseOut.setNursecost(nurse.getChild("NurseCost").getValue());
	}

	private void fillInDoctorInfo(Element doctor) {
		doctorOut.setDoctorID(doctor.getChild("DoctorID").getValue());
		// doctorOut.setServiceTime(doctor.getChild("ServiceTime").getValue());
		doctorOut.setDoctorCost(doctor.getChild("DoctorCost").getValue());
	}

	private void fillInRoomInfo(Element room) {
		roomOut.setRoomID(room.getChild("RoomID").getValue());
		roomOut.setRoomCostPerDay(room.getChild("RoomCostPerDay").getValue());
		roomOut.setCheckInDate(room.getChild("CheckInDate").getValue());
		roomOut.setDischargeDate(room.getChild("DischargeDate").getValue());
	}

	private void fillInLabortory(Element labortory) {
		labortoryOut.setLabortoryID(labortory.getChild("LabortoryID").getValue());
		labortoryOut.setTakeLabDate(labortory.getChild("TakeLabDate").getValue());
		labortoryOut.setLabortoryCost(labortory.getChild("LabortoryCost").getValue());
	}

	private void fillInRadiology(Element radiology) {
		radiologyOut.setRadiologyID(radiology.getChild("RadiologyID").getValue());
		radiologyOut.setTakeRadioDate(radiology.getChild("TakeradioDate").getValue());
		radiologyOut.setRadiologyCost(radiology.getChild("RadiologyCost").getValue());
	}

	private void fillInMedicine(Element medicine) {
		medicineOut.setMedicineID(medicine.getChild("MedicineID").getValue());
		medicineOut.setThresholdInventory(medicine.getChild("ThresholdInventory").getValue());
		medicineOut.setCurrentAmount(medicine.getChild("CurrentAmount").getValue());
		medicineOut.setRestoreReminder(medicine.getChild("RestoreReminder").getValue());
	}

	private void fillInMedicinePurchase(Element medicinePurchase) {
		medicinePurchaseOut.setMedicineID(medicinePurchase.getChild("MedicineID").getValue());
		medicinePurchaseOut.setMedicineUnitPrice(medicinePurchase.getChild("MedicineUnitPrice").getValue());
		medicinePurchaseOut.setMedicinePriceDate(medicinePurchase.getChild("MedicinePriceDate").getValue());
		medicinePurchaseOut.setMedicineAmount(medicinePurchase.getChild("MedicineAmount").getValue());
	}

	private void fillInPatientInsurance(Element patientInsurance) {
		patientInsuranceOut.setInsurancePolicyNo(patientInsurance.getChild("InsurancePolicyNo").getValue());
		patientInsuranceOut.setInsuranceStatus(patientInsurance.getChild("InsuranceStatus").getValue());
		patientInsuranceOut.setCoveragePercent(patientInsurance.getChild("CoveragePercent").getValue());
		patientInsuranceOut.setCoverageAmount(patientInsurance.getChild("CoverageAmount").getValue());
	}

	private void fillInBill(Element bill) {
		billOut.setBillDate(bill.getChild("BillDate").getValue());
		billOut.setIfPaid(bill.getChild("IfPaid").getValue());
	}

	private void fillInInquiry(Element inquiry) {
		inquiryOut.setInquiryDate(inquiry.getChild("InquiryDate").getValue());
	}

}