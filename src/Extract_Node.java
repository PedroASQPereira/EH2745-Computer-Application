import java.util.ArrayList;
import java.util.Objects;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Extract_Node {

	
	public Extract_Node(NodeList subList, NodeList subList_SSH, File_Specifications file_specs, GridData sys, String buffer_EQ) {

		// Loop for all equipments/containers/voltage/substation
		//for (String buffer_EQ : file_specs.ListSpecs_EQ) {

			switch (buffer_EQ) {
			case "cim:BaseVoltage":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_BV(subList.item(i), sys.ListBaseVoltage);
				}
				break;
			case "cim:Substation":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_Substation(subList.item(i), sys.ListSubstation);
				}
				break;
			case "cim:VoltageLevel":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_VL(subList.item(i), sys.ListVoltageLevel);
				}
				break;
			case "cim:GeneratingUnit":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_GU(subList.item(i), sys.ListGeneratingUnit);
				}
				break;
			case "cim:SynchronousMachine":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_SM(subList.item(i), subList_SSH, sys.ListSynchronous, sys.ListVoltageLevel);
				}
				break;
			case "cim:RegulatingControl":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_RC(subList.item(i),subList_SSH, sys.ListRegControl);
				}
				break;
			case "cim:PowerTransformer":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_PT(subList.item(i), sys.ListPowerTransformer);
				}
				break;
			case "cim:EnergyConsumer":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_EC(subList.item(i),subList_SSH,sys.ListLoad,sys.ListVoltageLevel);
				}
				break;
			case "cim:PowerTransformerEnd":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_PTE(subList.item(i),sys.ListPowerTransformerEnd);
				}
				break;
			case "cim:Breaker":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_BR(subList.item(i), sys.ListBreaker, sys.ListVoltageLevel);
				}
				break;
			case "cim:RatioTapChanger":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_RTC(subList.item(i),subList_SSH,sys.ListRatioTapChanger);
				}
				break;
			case "cim:Terminal":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_T(subList.item(i),sys.ListTerminal);
				}
				break;
			case "cim:BusbarSection":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_BB(subList.item(i),sys.ListBusBar,sys.ListTerminal);
				}
				break;
			case "cim:ConnectivityNode":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_CN(subList.item(i), sys.ListConNode,sys.ListTerminal);
				}
				break;
			case "cim:ACLineSegment":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_ACL( subList.item(i), sys.ListACLine);
				}
				break;
			case "cim:LinearShuntCompensator":
				for (int i = 0; i < subList.getLength(); i++) {
					extractNode_LS( subList.item(i), sys.ListShunt);
				}
				break;
			}

		//}

	}

	public void extractNode_BV (Node node , ArrayList<BaseVoltage> ListBaseVoltage){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;

		//can be used to read specific attribute of XML node.
		BaseVoltage BV = new BaseVoltage(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:BaseVoltage.nominalVoltage").item(0).getTextContent());
		ListBaseVoltage.add(0,BV);
	}

	public void extractNode_Substation (Node node, ArrayList<Substation> ListSubstation){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:Substation.Region").item(0);
		
		//can be used to read specific attribute of XML node.
		Substation S = new Substation(element.getAttribute("rdf:ID"), element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),element1.getAttribute("rdf:resource"));
		ListSubstation.add(0,S);
	}
	
	public void extractNode_VL (Node node, ArrayList<VoltageLevel> ListVoltageLevel){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:VoltageLevel.Substation").item(0);
		Element element2 = (Element) element.getElementsByTagName("cim:VoltageLevel.BaseVoltage").item(0);
		

		//can be used to read specific attribute of XML node.
		VoltageLevel VL = new VoltageLevel(element.getAttribute("rdf:ID"),Double.parseDouble( element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent()), element1.getAttribute("rdf:resource"), element2.getAttribute("rdf:resource"));
		ListVoltageLevel.add(0,VL);
		
	}
	
	public void extractNode_GU (Node node, ArrayList<GeneratingUnit> ListGeneratingUnit){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0);

		GeneratingUnit GU = new GeneratingUnit(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),element.getElementsByTagName("cim:GeneratingUnit.maxOperatingP").item(0).getTextContent(),element.getElementsByTagName("cim:GeneratingUnit.minOperatingP").item(0).getTextContent(),element1.getAttribute("rdf:resource"));
		ListGeneratingUnit.add(0,GU);
	}
	
	public void extractNode_SM (Node node, NodeList node_SSH, ArrayList<synch> ListSynch,  ArrayList<VoltageLevel> ListVoltageLevel){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		//Element element_SSH = (Element) node_SSH ;
		
		Element element1 = (Element) element.getElementsByTagName("cim:RotatingMachine.GeneratingUnit").item(0);
		Element element2 = (Element) element.getElementsByTagName("cim:RegulatingCondEq.RegulatingControl").item(0);
		Element element3 = (Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0);
		
		double p=0;
		double q=0;
		
		for(int m=0; m < node_SSH.getLength(); m++) {
			Element element_SSH = (Element) node_SSH.item(m);
			if( Objects.equals(element.getAttribute("rdf:ID"), element_SSH.getAttribute("rdf:about").substring(1))) {
				p = Double.parseDouble(element_SSH.getElementsByTagName("cim:RotatingMachine.p").item(0).getTextContent());
				q = Double.parseDouble(element_SSH.getElementsByTagName("cim:RotatingMachine.q").item(0).getTextContent());
				break;
			}
			
		}
		
		
		//can be used to read specific attribute of XML node.
		synch SM = new synch(element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(), element.getAttribute("rdf:ID"),element1.getAttribute("rdf:resource"), element2.getAttribute("rdf:resource") ,element3.getAttribute("rdf:resource"),element.getElementsByTagName("cim:RotatingMachine.ratedS").item(0).getTextContent(),p, q);
		SM.findBaseVoltage(ListVoltageLevel);
		ListSynch.add(0,SM);
	}
	
	public void extractNode_RC (Node node, NodeList node_SSH, ArrayList<regControl> ListRegControl){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		//Element element_SSH = (Element) node_SSH ;
		//can be used to read specific attribute of XML node.
		
		double targV =0;
		
		for(int m=0; m < node_SSH.getLength(); m++) {
			Element element_SSH = (Element) node_SSH.item(m);
			if( Objects.equals(element.getAttribute("rdf:ID"), element_SSH.getAttribute("rdf:about").substring(1))) {
				targV = Double.parseDouble(element_SSH.getElementsByTagName("cim:RegulatingControl.targetValue").item(0).getTextContent());
				break;
			}
		}
		
		
		regControl reg = new regControl(element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),element.getAttribute("rdf:ID"),targV);
		ListRegControl.add(reg);
	}
	
	public void extractNode_PT (Node node, ArrayList<PowerTransformer> ListPowerTransformer){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0);

		//can be used to read specific attribute of XML node.
		PowerTransformer PT = new PowerTransformer(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),element1.getAttribute("rdf:resource"));
		ListPowerTransformer.add(PT);
	}
	
	public void extractNode_EC (Node node, NodeList node_SSH, ArrayList<EnergyConsumer> ListLoad, ArrayList<VoltageLevel> ListVoltageLevel){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		//Element element_SSH = (Element) node_SSH;
		Element element1 = (Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0);
		double p=0;
		double q=0;
		
		for(int m=0; m < node_SSH.getLength(); m++) {
			Element element_SSH = (Element) node_SSH.item(m);
			if( Objects.equals(element.getAttribute("rdf:ID"), element_SSH.getAttribute("rdf:about").substring(1))) {
				p = Double.parseDouble(element_SSH.getElementsByTagName("cim:EnergyConsumer.p").item(0).getTextContent());
				q = Double.parseDouble(element_SSH.getElementsByTagName("cim:EnergyConsumer.q").item(0).getTextContent());
				break;
			}
			
		}
		
		//MISSING VOLTAGE LEVEL
		//can be used to read specific attribute of XML node.
		EnergyConsumer EC = new EnergyConsumer(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),p,q,element1.getAttribute("rdf:resource"));
		EC.findBaseVoltage(ListVoltageLevel);
		ListLoad.add(EC);
	}
	
	public void extractNode_PTE (Node node, ArrayList<PowerTransformerEnd> ListPowerTransformerEnd){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:PowerTransformerEnd.PowerTransformer").item(0);
		Element element2 = (Element) element.getElementsByTagName("cim:TransformerEnd.BaseVoltage").item(0);
		
		PowerTransformerEnd PTE = new PowerTransformerEnd(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.r").item(0).getTextContent()), Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.x").item(0).getTextContent())
				,Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.g").item(0).getTextContent()),Double.parseDouble(element.getElementsByTagName("cim:PowerTransformerEnd.b").item(0).getTextContent()),element1.getAttribute("rdf:resource"), element2.getAttribute("rdf:resource") );
		ListPowerTransformerEnd.add(PTE);
		
	}
	
	public void extractNode_BR (Node node, ArrayList<Breaker> ListBreaker, ArrayList<VoltageLevel> ListVoltageLevel){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0);
	
		Breaker BR = new Breaker(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),element.getElementsByTagName("cim:Switch.normalOpen").item(0).getTextContent(),element1.getAttribute("rdf:resource"));
		BR.findBaseVoltage(ListVoltageLevel);
		ListBreaker.add(BR);
		
	}
	
	public void extractNode_RTC (Node node,NodeList RTC,ArrayList<RatioTapChanger> ListRatioTapChanger){
		// … remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;		
		double step=0;

		for(int m=0; m < RTC.getLength(); m++) {
			Element element_SSH = (Element) RTC.item(m);
			if( Objects.equals(element.getAttribute("rdf:ID"), element_SSH.getAttribute("rdf:about").substring(1))) {
				step = Double.parseDouble(element_SSH.getElementsByTagName("cim:TapChanger.step").item(0).getTextContent());
				break;
			}
			
		}
		
		RatioTapChanger RTC_ = new RatioTapChanger(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),step);
		ListRatioTapChanger.add(RTC_);		
	}
	
	public void extractNode_BB (Node node , ArrayList<BusBar> ListBusBar, ArrayList<Terminal> ListTerminal ){
		
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0);
		
		Terminal terminal=null;
		for(Terminal t : ListTerminal){
			if(Objects.equals(t.conductingEquipment.substring(1), element.getAttribute("rdf:ID"))) {
				terminal = t;
				break;
			}
		}
		
		BusBar BB = new BusBar(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),element1.getAttribute("rdf:resource"),terminal);
		
		ListBusBar.add(BB);
	}
	
	public void extractNode_T (Node node , ArrayList<Terminal> ListTerminal){
		
		Element element = (Element) node;
		Element element1 = (Element) element.getElementsByTagName("cim:Terminal.ConnectivityNode").item(0);
		Element element2 = (Element) element.getElementsByTagName("cim:Terminal.ConductingEquipment").item(0);
		
		
		Terminal T = new Terminal(element.getAttribute("rdf:ID"),element1.getAttribute("rdf:resource"),element2.getAttribute("rdf:resource"));
		ListTerminal.add(T);
	}
	
	public void extractNode_CN (Node node , ArrayList<ConnectivityNode> ListConNode, ArrayList<Terminal> ListTerminal ){
		//  remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element ele1 = (Element) element.getElementsByTagName("cim:ConnectivityNode.ConnectivityNodeContainer").item(0);

		//can be used to read specific attribute of XML node.
		ConnectivityNode BV = new ConnectivityNode(element.getAttribute("rdf:ID"),ele1.getAttribute("rdf:resource"));
		for(Terminal t : ListTerminal){
			if(Objects.equals(t.connectivityNode.substring(1), BV.rdf_ID)) {
				BV.addTerminal(t);
			}
		}
		ListConNode.add(BV);
		
	}
	
	public void extractNode_ACL (Node node , ArrayList<ACLine> ListACLine ){
		//  remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		
		double r = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.r").item(0).getTextContent());
		double x = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.x").item(0).getTextContent());
		double b = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.bch").item(0).getTextContent());
		double g = Double.parseDouble(element.getElementsByTagName("cim:ACLineSegment.gch").item(0).getTextContent());
		//can be used to read specific attribute of XML node.
		ACLine ACL = new ACLine(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),r,x,b,g);
		ListACLine.add(ACL);
		
	}
	
	public void extractNode_LS (Node node , ArrayList<LinearShunt> ListShunt ){
		//  remember to convert node to element in order to search for the data inside it.
		Element element = (Element) node;
		Element ele1 = (Element) element.getElementsByTagName("cim:Equipment.EquipmentContainer").item(0);
		
		double n_sec = Double.parseDouble(element.getElementsByTagName("cim:ShuntCompensator.normalSections").item(0).getTextContent());
		double b = Double.parseDouble(element.getElementsByTagName("cim:LinearShuntCompensator.bPerSection").item(0).getTextContent());
		double g = Double.parseDouble(element.getElementsByTagName("cim:LinearShuntCompensator.gPerSection").item(0).getTextContent());
		//can be used to read specific attribute of XML node.
		
		LinearShunt LS = new LinearShunt(element.getAttribute("rdf:ID"),element.getElementsByTagName("cim:IdentifiedObject.name").item(0).getTextContent(),n_sec,b,g,ele1.getAttribute("rdf:resource"));
		ListShunt.add(LS);
		
	}
	
	
	
}
