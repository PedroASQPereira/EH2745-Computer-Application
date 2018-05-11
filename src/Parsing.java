import java.io.File;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class Parsing {


	public Parsing(GridData sys,File_Specifications file_specs) {
		
		//GridData sys = new GridData();
		//File_Specifications file_specs = new File_Specifications();
		
		try {
			//open the document
			File EQ_XmlFile = new File("Assignment_EQ_reduced.xml");
			File SSH_XmlFile = new File("Assignment_SSH_reduced.xml");
			//Advanced file
			//File EQ_XmlFile = new File("MicroGridTestConfiguration_T1_BE_EQ_V2.xml");
			//File SSH_XmlFile = new File("MicroGridTestConfiguration_T1_BE_SSH_V2.xml");
			
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(EQ_XmlFile);
			Document doc_SSH = dBuilder.parse(SSH_XmlFile);
			

			// normalize CIM XML file
			doc.getDocumentElement().normalize();
			doc_SSH.getDocumentElement().normalize();
			
			//SEARCH EQ FILE FOR A CERTAIN ELEMENT
			for(String buffer_EQ : file_specs.ListSpecs_EQ){
				NodeList subList = doc.getElementsByTagName(buffer_EQ);
				NodeList subList_SSH = null;
				
				//IN CASE THERE IS ALSO THE SAME ELEMENT IN THE SSH
				for(String buffer_SSH :file_specs.ListSpecs_SSH) {
					if(Objects.equals(buffer_EQ, buffer_SSH)) {
						subList_SSH = doc_SSH.getElementsByTagName(buffer_SSH);
					}
				}
				
				new Extract_Node(subList,subList_SSH,file_specs,sys,buffer_EQ);
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
	
}
