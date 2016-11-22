package edu.fiu.dbms.hospital.activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.fiu.dbms.hospital.basicentity.CheckInRecord;
import edu.fiu.dbms.hospital.basicentity.Creditcard;
import edu.fiu.dbms.hospital.basicentity.Patient;
import edu.fiu.dbms.hospital.parseentity.ParsePatientEntity;
import edu.fiu.dbms.hospital.parseprep.JDomPatient;
import edu.fiu.dbms.hospital.util.InteractDB;
import edu.fiu.dbms.hospital.util.JDBCConnection;

/**
 * This is the main function for a new patient check in.
 * 
 * @author PINCHAO LIU
 * @version 0.1
 *
 */
public class NewPatientCheckIn {

	public static void main(String[] args) {
		// set up DB connection
		JDBCConnection jdbcConnection = new JDBCConnection();
		try {
			jdbcConnection.initParam(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// initialize objects, fill in objects info from xml input
		JDomPatient jDomDemo = new JDomPatient();
		ParsePatientEntity parsePatientEntity = new ParsePatientEntity(jDomDemo, args[1]);
		Patient patient = jDomDemo.patientOut;
		List<Creditcard> creditcards = jDomDemo.creditcardsOutList;
		CheckInRecord checkInRecord = jDomDemo.checkInRecordOut;

		// using embedded sql to insert new patient info
		String insertPatientInfo = "INSERT INTO hospitalsystem.\"PATIENTS\"(\"SSN\", \"FIRSTNAME\", \"MIDDLENAME\", \"LASTNAME\", \"EMAIL\", \"PHONENUMBER\", \"PASSWORD\", \"BALANCEAMOUNT\", \"INSURANCEPOLICYNO\", \"GENDER\", \"BIRTHDATE\") "
				+ "VALUES (" + "'" + patient.getSsn() + "'," + "'" + patient.getFirstname() + "'," + "'"
				+ patient.getMiddleInitial() + "'," + "'" + patient.getLastname() + "'," + "'" + patient.getEmail()
				+ "'," + "'" + patient.getPhoneNumber() + "', md5('" + patient.getPassword() + "')," + "'"
				+ patient.getBalanceAmount() + "'," + "'" + patient.getInsurancePolicyNumber() + "'," + "'"
				+ patient.getGender() + "'," + "'" + patient.getBirthdate() + "'" + ")";
		InteractDB.insertInfoToDB(jdbcConnection, insertPatientInfo);

		int maxPatientID = getMaxPatientID(jdbcConnection);

		// using store procedure to insert new credit card info
		for (Creditcard creditcard : creditcards) {
			String callCreatCreditCardFunc = "select hospitalsystem.create_creditcard('"
					+ creditcard.getCreditcardNumber() + "', '" + creditcard.getBillAddress() + "', '"
					+ (creditcard.isIfPreferedToUse() ? "Y" : "N") + "', '"
					+ (creditcard.isIfMailPaperBill() ? "Y" : "N") + "'," + "'" + maxPatientID + "')";
			InteractDB.callFunctionFromDB(jdbcConnection, callCreatCreditCardFunc);

		}

		// using store procedure to insert check in record
		String callCreatCheckInRecordFunc = "select hospitalsystem.create_checkinrecord('" + maxPatientID + "' , '"
				+ checkInRecord.getNurseId() + "' , '" + checkInRecord.getHeight() + "', '" + checkInRecord.getWeight()
				+ "', '" + checkInRecord.getBloodPressure() + "' , '" + checkInRecord.getBodyTemperature() + "', '"
				+ checkInRecord.getCheckInTime() + "')";

		InteractDB.callFunctionFromDB(jdbcConnection, callCreatCheckInRecordFunc);

		String newPatientCheckInRecord = "select * from hospitalsystem.v_newpatientcheckinrecord";
		ResultSet resultSet1 = InteractDB.showInfoFromDB(jdbcConnection, newPatientCheckInRecord);
		InteractDB.showResultOnConsole(resultSet1, true);
	}

	private static int getMaxPatientID(JDBCConnection jdbcConnection) {
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection,
				"select max(\"PATIENTID\") from hospitalsystem.\"PATIENTS\"");
		String maxPatientID = "";
		try {
			maxPatientID = resultSet.next() ? resultSet.getString(1) : resultSet.getString(1);
			return Integer.parseInt(maxPatientID);
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
}
