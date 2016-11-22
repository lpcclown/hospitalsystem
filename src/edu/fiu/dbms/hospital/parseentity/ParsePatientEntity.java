package edu.fiu.dbms.hospital.parseentity;

import edu.fiu.dbms.hospital.parseprep.JDomPatient;

public class ParsePatientEntity {

	// JDomPatient jDomDemo = new JDomPatient();

	public ParsePatientEntity(JDomPatient jDomDemo, String inputXML) {
		super();
		// this.jDomDemo = jDomDemo;
		// jDomDemo.parserXml("inputxml/patient.xml");
		jDomDemo.parserXml(inputXML);
	}
}
