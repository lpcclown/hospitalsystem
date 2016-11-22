package edu.fiu.dbms.hospital.parseentity;

import edu.fiu.dbms.hospital.parseprep.JDomCheckOut;

public class ParseCheckOutEntity {
	public ParseCheckOutEntity(JDomCheckOut jDomDemo, String inputXML) {
		super();
		jDomDemo.parserXml(inputXML);
	}
}