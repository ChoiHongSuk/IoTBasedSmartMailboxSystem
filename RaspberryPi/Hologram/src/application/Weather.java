package application;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Weather {
	 
//1:Clear,(2:Partly Cloudy,3:Mostly Cloudy,4:Cloudy),(5:Rain,6:Snow/Rain),7:Snow
	 String wfEn;
	 String wfKor;
 
	 public Weather() {
	 
	  try {
	   DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
	   DocumentBuilder parser = f.newDocumentBuilder();
	 
	   Document xmlDoc = null;
	   String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1121571000";
	   xmlDoc = parser.parse(url);
	 
	   Element root = xmlDoc.getDocumentElement();
	   // System.out.println(root.getTagName());
	       
	    Node xmlNode1 = root.getElementsByTagName("data").item(0);
	    Node xmlNode21 = ((Element) xmlNode1).getElementsByTagName("wfKor").item(0); //한국어
	    Node xmlNode22 = ((Element) xmlNode1).getElementsByTagName("wfEn").item(0); //영어코드
   
	    wfKor= xmlNode21.getTextContent();
	    wfEn = xmlNode22.getTextContent();   
	 
	  } catch (Exception e) {
	   System.out.println(e.getMessage());
	   System.out.println(e.toString());
	  }
	 }	 
	 public String getEn() {
		 return wfEn;
	 }
	 public String getKor() {
		 return wfKor;
	 }
}