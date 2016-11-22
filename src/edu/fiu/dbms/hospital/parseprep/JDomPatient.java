package edu.fiu.dbms.hospital.parseprep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import edu.fiu.dbms.hospital.basicentity.CheckInRecord;
import edu.fiu.dbms.hospital.basicentity.Creditcard;
import edu.fiu.dbms.hospital.basicentity.Patient;
import edu.fiu.dbms.hospital.util.IXmlDocument;

/**
 * This is the basic class which is used to parse XML doc
 * 
 * @author PINCHAO LIU 3619260
 * 
 */
public class JDomPatient implements IXmlDocument {
	public Patient patientOut = new Patient();
	public List<Creditcard> creditcardsOutList = new ArrayList<Creditcard>();
	public CheckInRecord checkInRecordOut = new CheckInRecord();

	public void parserXml(String fileName) {
		SAXBuilder builder = new SAXBuilder(false);
		try {
			Document document = builder.build(fileName);
			Element patient = document.getRootElement();
			List patientInfo = patient.getChildren();
			Element creditcards = patient.getChild("creditcards");
			Element checkinRecord = patient.getChild("checkinrecord");
			for (int i = 0; i < patientInfo.size(); i++) {
				if (((Element) patientInfo.get(i)).getName() == "creditcards") {
					List creditcardList = creditcards.getChildren("creditcard");
					for (int k = 0; k < creditcardList.size(); k++) {
						Element creditcard = (Element) creditcardList.get(k);
						List creditcardInfo = creditcard.getChildren();
						Creditcard creditcardTemp = new Creditcard();
						for (int j = 0; j < creditcardInfo.size(); j++) {
							// System.out.println(((Element)
							// creditcardInfo.get(j)).getName() + ":"
							// + ((Element) creditcardInfo.get(j)).getValue());
							String value = ((Element) creditcardInfo.get(j)).getValue().toString();
							switch (((Element) creditcardInfo.get(j)).getName()) {
							case "CREDITCARDNUMBER":
								creditcardTemp.setCreditcardNumber(value);
								break;
							case "BILLADDRESS":
								creditcardTemp.setBillAddress(value);
								break;
							case "IFPREFEREDTOUSE":
								if (value.toString().equals("Yes")) {
									creditcardTemp.setIfPreferedToUse(true);
								} else {
									creditcardTemp.setIfPreferedToUse(false);
								}
								break;
							case "IFMAILPAPERBILL":
								if (value.toString().equals("Yes")) {
									creditcardTemp.setIfMailPaperBill(true);
								} else {
									creditcardTemp.setIfMailPaperBill(false);
								}
								break;
							}

						}
						creditcardsOutList.add(creditcardTemp);
					}
				} else if (((Element) patientInfo.get(i)).getName() == "checkinrecord") {
					List checkinRecordInfo = checkinRecord.getChildren();
					for (int j = 0; j < checkinRecordInfo.size(); j++) {
						// System.out.println(((Element)
						// checkinRecordInfo.get(j)).getName() + ":"
						// + ((Element) checkinRecordInfo.get(j)).getValue());
						String value = ((Element) checkinRecordInfo.get(j)).getValue().toString();
						switch (((Element) checkinRecordInfo.get(j)).getName()) {
						case "nurseid":
							checkInRecordOut.setNurseId(Integer.parseInt(value));
							break;
						case "HEIGHT":
							checkInRecordOut.setHeight(Float.parseFloat(value));
							break;
						case "WEIGHT":
							checkInRecordOut.setWeight(Float.parseFloat(value));
							break;
						case "BLOODPRESSURE":
							checkInRecordOut.setBloodPressure(value);
							break;
						case "BODYTEMPERATURE":
							checkInRecordOut.setBodyTemperature(value);
							break;
						case "CHECKINTIME":
							checkInRecordOut.setCheckInTime(value);
							break;
						}
					}
				} else {
					// System.out.println(
					// ((Element) patientInfo.get(i)).getName() + ":" +
					// ((Element) patientInfo.get(i)).getValue());
					String value = ((Element) patientInfo.get(i)).getValue().toString();
					switch (((Element) patientInfo.get(i)).getName()) {
					case "SSN":
						patientOut.setSsn(value);
						break;
					case "FIRSTNAME":
						patientOut.setFirstname(value);
						break;
					case "MIDDLENAME":
						patientOut.setMiddleInitial(value);
						break;
					case "LASTNAME":
						patientOut.setLastname(value);
						break;
					case "EMAIL":
						patientOut.setEmail(value);
						break;
					case "PHONENUMBER":
						patientOut.setPhoneNumber(value);
						break;
					case "PASSWORD":
						patientOut.setPassword(value);
						break;
					case "BALANCEAMOUNT":
						patientOut.setBalanceAmount(value);
						break;
					case "INSURANCEPOLICYNO":
						patientOut.setInsurancePolicyNumber(value);
						break;
					case "GENDER":
						patientOut.setGender(value);
						if (value.toString().equals("Male")) {
							patientOut.setGender("M");
						} else {
							patientOut.setGender("F");
						}
						break;
					case "DATEOFBIRTH":
						patientOut.setBirthdate(value);
						break;
					}

				}

			}

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}