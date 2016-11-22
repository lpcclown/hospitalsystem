package edu.fiu.dbms.hospital.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import edu.fiu.dbms.hospital.parseentity.ParseCheckOutEntity;
import edu.fiu.dbms.hospital.parseprep.JDomCheckOut;
import edu.fiu.dbms.hospital.util.InteractDB;
import edu.fiu.dbms.hospital.util.JDBCConnection;

public class NewPatientCheckOut {

	public static void main(String[] args) {
		// set up DB connection
		JDBCConnection jdbcConnection = new JDBCConnection();
		try {
			jdbcConnection.initParam(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// initialize objects, fill in objects info from XML input
		JDomCheckOut jDomDemo = new JDomCheckOut();
		ParseCheckOutEntity parseCheckOutEntity = new ParseCheckOutEntity(jDomDemo, args[1]);

		// insert or update information into DB
		insertPatient(jDomDemo.patientOut, jdbcConnection);
		int maxPatientID = getMaxID(jdbcConnection, "\"PATIENTS\"", "\"PATIENTID\"");
		insertCreditcard(jDomDemo.creditcardsOut, maxPatientID, jdbcConnection);
		updateNurseCost(jDomDemo.nurseOut, jdbcConnection);
		updateDoctorCost(jDomDemo.doctorOut, jdbcConnection);
		updateRoomCost(jDomDemo.roomOut, jdbcConnection);
		updateMedicine(jDomDemo.medicineOut, jdbcConnection);
		insertMedicinePurchase(jDomDemo.medicinePurchaseOut, maxPatientID, jdbcConnection);
		insertPatientInsurance(jDomDemo.patientInsuranceOut, maxPatientID, jdbcConnection);
		insertBill(jDomDemo.billOut, jDomDemo.doctorOut, jDomDemo.nurseOut, jDomDemo.roomOut, jDomDemo.labortoryOut,
				jDomDemo.radiologyOut, jDomDemo.creditcardsOut, jDomDemo.medicinePurchaseOut, maxPatientID,
				jdbcConnection);
		int maxBillID = getMaxID(jdbcConnection, "\"BILLS\"", "\"BILLID\"");
		float finalcost = calculateOwnedMoney(maxBillID, jDomDemo.medicinePurchaseOut, jDomDemo.patientInsuranceOut,
				jdbcConnection);
		insertInquiry(jDomDemo.inquiryOut, maxPatientID, maxBillID, finalcost, jdbcConnection);
		int maxInquiryID = getMaxID(jdbcConnection, "\"INQUIRIES\"", "\"ID\"");

		// show information from DB
		System.out.println("================BILL INFO===============================");
		showBill(maxBillID, jdbcConnection);
		System.out.println("================INQUIRY RECORD INFO=====================");
		showInquiryRecord(maxInquiryID, jdbcConnection);
		System.out.println("================BILL REMINDER INFO======================");
		showBillReminder(maxPatientID, jdbcConnection);
		System.out.println("================MEDICINE RESTORK REMINDER INFO==========");
		showMedicineInfo(jDomDemo.medicineOut, jdbcConnection);

	}

	private static void showBill(int maxBillID, JDBCConnection jdbcConnection) {
		String selectBillInfo = "select * from hospitalsystem.\"BILLS\" where \"BILLID\" = '" + maxBillID + "'";
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection, selectBillInfo);
		InteractDB.showResultOnConsole(resultSet, true);
	}

	private static void showInquiryRecord(int maxInquiryID, JDBCConnection jdbcConnection) {
		String selectBillInfo = "select \"ID\", \"PATIENTID\",\"INQUIRYDATE\",\"BILLID\" from hospitalsystem.\"INQUIRIES\" where \"ID\" = '"
				+ maxInquiryID + "'";
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection, selectBillInfo);
		InteractDB.showResultOnConsole(resultSet, true);
	}

	private static void showBillReminder(int maxPatientID, JDBCConnection jdbcConnection) {

		String selectBillReminderInfo = "select * from hospitalsystem.\"BILLREMINDERS\" where \"PATIENTID\"='"
				+ maxPatientID + "'";
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection, selectBillReminderInfo);
		InteractDB.showResultOnConsole(resultSet, true);
	}

	private static void showMedicineInfo(Medicine medicine, JDBCConnection jdbcConnection) {

		String selectMedicineInfo = "select * from hospitalsystem.\"MEDICINES\" WHERE \"MEDICINEID\"= '"
				+ medicine.getMedicineID() + "'";
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection, selectMedicineInfo);
		InteractDB.showResultOnConsole(resultSet, true);
	}

	private static Float calculateOwnedMoney(int maxBillID, MedicinePurchase medicinePurchase,
			PatientInsurance patientInsurance, JDBCConnection jdbcConnection) {
		String selectBillReminderInfo = "select * from hospitalsystem.\"BILLS\" where \"BILLID\"='" + maxBillID + "'";
		Float doctorCost = 0f;
		Float nurseCost = 0f;
		Float roomCost = 0f;
		Float labCost = 0f;
		Float radiologyCost = 0f;
		Float discountAmount = 0f;
		Float finalCost = 0f;
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection, selectBillReminderInfo);
		try {
			if (resultSet.next()) {
				doctorCost = Float.parseFloat(resultSet.getString("DOCTORCOST"));
				nurseCost = Float.parseFloat(resultSet.getString("NURSECOST"));
				roomCost = Float.parseFloat(resultSet.getString("ROOMCOST"));
				labCost = Float.parseFloat(resultSet.getString("LABCOST"));
				radiologyCost = Float.parseFloat(resultSet.getString("RADIOLOGYCOST"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int medicineCost = Integer.parseInt(medicinePurchase.getMedicineAmount())
				* Integer.parseInt(medicinePurchase.getMedicineUnitPrice());
		Float totalCost = doctorCost + nurseCost + roomCost + labCost + radiologyCost + medicineCost;

		discountAmount = Float.parseFloat(patientInsurance.getCoveragePercent()) * 0.01f * totalCost;
		if (discountAmount > Float.parseFloat(patientInsurance.getCoverageAmount())) {
			finalCost = totalCost - Float.parseFloat(patientInsurance.getCoverageAmount());
		} else {
			finalCost = totalCost * (1 - Float.parseFloat(patientInsurance.getCoveragePercent()) * 0.01f);
		}
		return finalCost;
	}

	private static int getMaxID(JDBCConnection jdbcConnection, String tableName, String columnName) {
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection,
				"select max(" + columnName + ") from hospitalsystem." + tableName + "");
		String maxID = "";
		try {
			maxID = resultSet.next() ? resultSet.getString(1) : resultSet.getString(1);
			return Integer.parseInt(maxID);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private static void insertPatient(Patient patient, JDBCConnection jdbcConnection) {
		// using embedded SQL to insert new patient info
		String insertPatientInfo = "INSERT INTO hospitalsystem.\"PATIENTS\"(\"SSN\", \"FIRSTNAME\", \"MIDDLENAME\", \"LASTNAME\", \"EMAIL\", \"PHONENUMBER\", \"PASSWORD\", \"BALANCEAMOUNT\", \"INSURANCEPOLICYNO\", \"GENDER\", \"BIRTHDATE\") "
				+ "VALUES (" + "'" + patient.getSsn() + "'," + "'" + patient.getFirstname() + "'," + "'"
				+ patient.getMiddleInitial() + "'," + "'" + patient.getLastname() + "'," + "'" + patient.getEmail()
				+ "'," + "'" + patient.getPhoneNumber() + "', md5('" + patient.getPassword() + "')," + "'"
				+ patient.getBalanceAmount() + "'," + "'" + patient.getInsurancePolicyNumber() + "'," + "'"
				+ patient.getGender() + "'," + "'" + patient.getBirthdate() + "'" + ")";
		InteractDB.insertInfoToDB(jdbcConnection, insertPatientInfo);

	}

	private static void insertCreditcard(Creditcard creditcard, int maxPatientID, JDBCConnection jdbcConnection) {
		// using store procedure to insert new credit card info
		String callCreatCreditCardFunc = "select hospitalsystem.create_creditcard('" + creditcard.getCreditcardNumber()
				+ "', '" + creditcard.getBillAddress() + "', '" + (creditcard.isIfPreferedToUse() ? "Y" : "N") + "', '"
				+ (creditcard.isIfMailPaperBill() ? "Y" : "N") + "'," + "'" + maxPatientID + "')";
		InteractDB.callFunctionFromDB(jdbcConnection, callCreatCreditCardFunc);
	}

	private static void updateNurseCost(Nurse nurse, JDBCConnection jdbcConnection) {
		String updateNurseCost = "UPDATE hospitalsystem.\"NURSES\" SET \"NURSECOST\" = '" + nurse.getNursecost()
				+ "' where \"NURSEID\"= '" + nurse.getId() + "'";
		InteractDB.insertInfoToDB(jdbcConnection, updateNurseCost);
	}

	private static void updateDoctorCost(Doctor doctor, JDBCConnection jdbcConnection) {
		String updateDoctorCost = "UPDATE hospitalsystem.\"DOCTORS\" SET \"DOCTORCOST\" = '" + doctor.getDoctorCost()
				+ "' where \"DOCTORID\"= '" + doctor.getDoctorID() + "'";
		InteractDB.insertInfoToDB(jdbcConnection, updateDoctorCost);
	}

	private static void updateRoomCost(Room room, JDBCConnection jdbcConnection) {
		String updateRoomCost = "UPDATE hospitalsystem.\"ROOMS\" SET \"ROOMCOST\" = '" + room.getRoomCostPerDay()
				+ "' where \"ROOMID\"= '" + room.getRoomID() + "'";
		InteractDB.insertInfoToDB(jdbcConnection, updateRoomCost);
	}

	private static void updateMedicine(Medicine medicine, JDBCConnection jdbcConnection) {
		String reminder = medicine.getRestoreReminder().equals("Yes") ? "Y" : "N";
		String updateMedicine = "UPDATE hospitalsystem.\"MEDICINES\" SET \"LOWINVENTORYTHRESHOLD\" = '"
				+ medicine.getThresholdInventory() + "'" + ", \"AMOUNT\" = '" + medicine.getCurrentAmount()
				+ "' , \"RESTOCKREMINDER\"='" + reminder + "' where \"MEDICINEID\"= '" + medicine.getMedicineID() + "'";
		InteractDB.insertInfoToDB(jdbcConnection, updateMedicine);

	}

	private static void insertMedicinePurchase(MedicinePurchase medicinePurchase, int maxPatientID,
			JDBCConnection jdbcConnection) {
		String insertMedicinePurchaseInfo = "INSERT INTO hospitalsystem.\"MEDICINEPURCHASERECORDS\"(\"PATIENTID\", \"MEDICINEID\", \"MEDICINEPRICE\", \"MEDICINEPRICEDATE\", \"MEDICINEAMOUNT\") "
				+ "VALUES ('" + maxPatientID + "'," + "'" + medicinePurchase.getMedicineID() + "'," + "'"
				+ medicinePurchase.getMedicineUnitPrice() + "'," + "'" + medicinePurchase.getMedicinePriceDate() + "',"
				+ "'" + medicinePurchase.getMedicineAmount() + "')";
		InteractDB.insertInfoToDB(jdbcConnection, insertMedicinePurchaseInfo);

	}

	private static void insertPatientInsurance(PatientInsurance patientInsurance, int maxPatientID,
			JDBCConnection jdbcConnection) {
		String status = patientInsurance.getInsuranceStatus().equals("Active") ? "1" : "0";
		String insertPatientInsuranceInfo = "INSERT INTO hospitalsystem.\"PATIENTXINSURANCE\"(\"PATIENTID\", \"INSURANCEPOLICYNO\", \"INSURANCESTATUS\", \"COVERAGEPERCENT\", \"COVERAGEAMOUNT\") "
				+ "VALUES ('" + maxPatientID + "'," + "'" + patientInsurance.getInsurancePolicyNo() + "','" + status
				+ "','" + patientInsurance.getCoveragePercent() + "'," + "'" + patientInsurance.getCoverageAmount()
				+ "')";
		InteractDB.insertInfoToDB(jdbcConnection, insertPatientInsuranceInfo);

	}

	private static void insertBill(Bill bill, Doctor doctor, Nurse nurse, Room room, Labortory labortory,
			Radiology radiology, Creditcard creditcard, MedicinePurchase medicinePurchase, int maxPatientID,
			JDBCConnection jdbcConnection) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String firstDueDate = "";
		try {
			Date date = sdf.parse(bill.getBillDate());
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			rightNow.add(Calendar.MONTH, 3);
			date = rightNow.getTime();
			firstDueDate = sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int rommCost = 0;
		try {
			rommCost = Integer.parseInt(room.getRoomCostPerDay())
					* daysGap(room.getCheckInDate(), room.getDischargeDate());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String insertBillInfo = "INSERT INTO hospitalsystem.\"BILLS\"(\"BILLDATE\", \"PATIENTID\", \"DOCTORCOST\", \"NURSECOST\", \"ROOMCOST\",\"LABCOST\",\"RADIOLOGYCOST\",\"CREDITCARDNUMBER\", \"FIRSTDUEDATE\",\"BILLADDRESS\",\"MEDICINEPURCHASERECORDID\", \"IFPAID\") "
				+ "VALUES ('" + bill.getBillDate() + "','" + maxPatientID + "','" + doctor.getDoctorCost() + "','"
				+ nurse.getNursecost() + "','" + rommCost + "', '" + labortory.getLabortoryCost() + "', '"
				+ radiology.getRadiologyCost() + "', '" + creditcard.getCreditcardNumber() + "','" + firstDueDate
				+ "', '" + creditcard.getBillAddress() + "','1','" + bill.getIfPaid() + "')";
		InteractDB.insertInfoToDB(jdbcConnection, insertBillInfo);

	}

	private static void insertInquiry(Inquiry inquiry, int maxPatientID, int maxBillID, float finalcost,
			JDBCConnection jdbcConnection) {
		String reminderMessage = "According to insurance policy and bill ID " + maxBillID + ", the patient ID "
				+ maxPatientID + " owns money " + finalcost + " dollar.";
		String insertInquiryInfo = "INSERT INTO hospitalsystem.\"INQUIRIES\"(\"INQUIRYDATE\",\"BILLID\",\"PATIENTID\",\"REMINDERMESSAGE\") "
				+ "VALUES ('" + inquiry.getInquiryDate() + "','" + maxBillID + "','" + maxPatientID + "','"
				+ reminderMessage + "')";
		InteractDB.insertInfoToDB(jdbcConnection, insertInquiryInfo);
	}

	private static int daysGap(String startDate, String endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cl = Calendar.getInstance();
		cl.setTime(sdf.parse(startDate));
		long begin = cl.getTimeInMillis();
		cl.setTime(sdf.parse(endDate));
		long end = cl.getTimeInMillis();
		int days = (int) ((end - begin) / (1000 * 3600 * 24));
		return days;
	}

}
