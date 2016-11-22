package edu.fiu.dbms.hospital.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.fiu.dbms.hospital.util.InteractDB;
import edu.fiu.dbms.hospital.util.JDBCConnection;

public class BasicFunctions {

	public static void main(String[] args) throws IOException, SQLException {

		// set up DB connection
		JDBCConnection jdbcConnection = new JDBCConnection();
		try {
			jdbcConnection.initParam(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Welcome to hospital system demo functions!");
		showAvailableFunctions();
		checkFunctionToCall(strin, jdbcConnection);

	}

	static void showAvailableFunctions() {
		System.out.println();
		System.out.println("Please choose which function you want to test:");
		System.out.println("1 -- To check user login.");
		System.out.println("2 -- To update user password.");
		System.out.println("3 -- To check patient information.");
		System.out.println("4 -- To set preferred credit card.");
		System.out.println("5 -- To get medicine availability.");
		System.out.println("6 -- To get patient charge information.");
		System.out.println("0 -- Exit the system.");
	}

	static void checkFunctionToCall(BufferedReader strin, JDBCConnection jdbcConnection)
			throws IOException, SQLException {
		String functionNumber = strin.readLine();
		switch (functionNumber) {
		case "1":
			userLoginCheck(strin, jdbcConnection);
			break;
		case "2":
			updatePassword(strin, jdbcConnection);
			break;
		case "3":
			checkPatientInfo(strin, jdbcConnection);
			break;
		case "4":
			setPreferCard(strin, jdbcConnection);
			break;
		case "5":
			getMedicineAvailability(strin, jdbcConnection);
			break;
		case "6":
			chargePatientInfo(strin, jdbcConnection);
			break;
		case "0":
			System.exit(0);
		default:
			showAvailableFunctions();
			checkFunctionToCall(strin, jdbcConnection);
			break;
		}

	}

	static void userLoginCheck(BufferedReader strin, JDBCConnection jdbcConnection) throws IOException, SQLException {

		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("--------------Check User Login-------------------");
		System.out.println("-------------------------------------------------");

		System.out.println("Please Key In Username (Patient Email Address):");
		String userName = strin.readLine();

		System.out.println("Please Key In Password:");
		String password = strin.readLine();

		String callLoginCheckFunc = "select hospitalsystem.fn_logincheck ('" + userName + "', '" + password + "')";

		ResultSet resultSet = (InteractDB.callFunctionFromDB(jdbcConnection, callLoginCheckFunc));
		InteractDB.showResultOnConsole(resultSet, false);
		showAvailableFunctions();
		checkFunctionToCall(strin, jdbcConnection);

	}

	static void updatePassword(BufferedReader strin, JDBCConnection jdbcConnection) throws IOException, SQLException {

		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("--------------Update Password--------------------");
		System.out.println("-------------------------------------------------");

		System.out.println("Please Key In Username (Patient Email Address):");
		String userName = strin.readLine();

		System.out.println("Please Key In Original Password:");
		String originalPassword = strin.readLine();

		System.out.println("Please Key In New Password:");
		String newPassword = strin.readLine();

		String callUpdatePasswordFunc = "select hospitalsystem.fn_updatepassword ('" + userName + "', '"
				+ originalPassword + "', '" + newPassword + "')";
		ResultSet resultSet = InteractDB.callFunctionFromDB(jdbcConnection, callUpdatePasswordFunc);
		InteractDB.showResultOnConsole(resultSet, false);
		showAvailableFunctions();
		checkFunctionToCall(strin, jdbcConnection);

	};

	static void checkPatientInfo(BufferedReader strin, JDBCConnection jdbcConnection) throws IOException, SQLException {
		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("--------------Check Patient Info-----------------");
		System.out.println("-------------------------------------------------");

		System.out.println("Please Key In Patient ID:");
		String patientID = strin.readLine();
		String selectPatientInfo = "select * from hospitalsystem.\"PATIENTS\" where \"PATIENTID\" = '" + patientID
				+ "'";
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection, selectPatientInfo);
		InteractDB.showResultOnConsole(resultSet, true);
		showAvailableFunctions();
		checkFunctionToCall(strin, jdbcConnection);

	}

	static void setPreferCard(BufferedReader strin, JDBCConnection jdbcConnection) throws IOException, SQLException {

		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("--------------Setup Prefer Credit Card-----------");
		System.out.println("-------------------------------------------------");

		System.out.println("Please Key In Patient ID:");
		String patientID = strin.readLine();

		System.out.println("Please Key In CreditCard Number:");
		String creditCardNumber = strin.readLine();

		String callSetPreferCardFunc = "select hospitalsystem.fn_setprefercard ('" + patientID + "', '"
				+ creditCardNumber + "')";

		ResultSet resultSet = (InteractDB.callFunctionFromDB(jdbcConnection, callSetPreferCardFunc));
		ResultSet orignalResult = (InteractDB.callFunctionFromDB(jdbcConnection, callSetPreferCardFunc));
		if (resultSet.next()) {
			System.out.println(
					"The Prefer Credit Card Setup is Successfully Done in DB, the Card Number and Bill Address are As Below:");
			InteractDB.showResultOnConsole(orignalResult, false);
		} else {
			System.out.println("The Association of Patient ID and Credit Card is Wrong, Please Double Check!");
		}
		showAvailableFunctions();
		checkFunctionToCall(strin, jdbcConnection);
	}

	static void getMedicineAvailability(BufferedReader strin, JDBCConnection jdbcConnection)
			throws IOException, SQLException {

		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("--------------Get Medicine Availability----------");
		System.out.println("-------------------------------------------------");

		System.out.println("Please Key In Medicine ID:");
		String medicineID = strin.readLine();

		System.out.println("Please Key In Date:");
		String priceDate = strin.readLine();

		String callSetPreferCardFunc = "select hospitalsystem.fn_getmedicineavailability ('" + medicineID + "', '"
				+ priceDate + "')";

		ResultSet resultSet = (InteractDB.callFunctionFromDB(jdbcConnection, callSetPreferCardFunc));
		ResultSet orignalResult = (InteractDB.callFunctionFromDB(jdbcConnection, callSetPreferCardFunc));
		if (resultSet.next()) {
			System.out.print("The available amount of this medicine is: ");
			InteractDB.showResultOnConsole(orignalResult, false);
		} else {
			System.out.println("The Requested Record Does Not Exist In DB, Please Double Check!");
		}
		showAvailableFunctions();
		checkFunctionToCall(strin, jdbcConnection);
	}

	static void chargePatientInfo(BufferedReader strin, JDBCConnection jdbcConnection)
			throws IOException, SQLException {
		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("--------------Charge Patient Info----------------");
		System.out.println("-------------------------------------------------");

		System.out.println("Please Key In Patient ID:");
		String patientID = strin.readLine();
		System.out.println("Please Key In Bill Date:");
		String billDate = strin.readLine();
		String callChargePatientFunction = "select  hospitalsystem.fn_chargepatientinfo(" + patientID + " , '"
				+ billDate + "')";
		ResultSet resultSet = InteractDB.showInfoFromDB(jdbcConnection, callChargePatientFunction);
		ResultSet orignalResult = (InteractDB.callFunctionFromDB(jdbcConnection, callChargePatientFunction));
		if (resultSet.next()) {
			System.out.println(
					"('BILLID', 'BILLDATE', 'PATIENTID','DOCTORCOST','NURSECOST','ROOMCOST', 'LABCOST', 'RADIOLOGYCOST', 'MEDICINECOST')");
			InteractDB.showResultOnConsole(orignalResult, false);
		} else {
			System.out.println("The Requested Record Does Not Exist In DB, Please Double Check!");
		}
		showAvailableFunctions();
		checkFunctionToCall(strin, jdbcConnection);
	}
}
