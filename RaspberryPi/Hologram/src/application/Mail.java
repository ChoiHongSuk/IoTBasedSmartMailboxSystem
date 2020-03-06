package application;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Mail {
	String mail;
	public String getMail() {
		try {
			Document doc = Jsoup.connect("http://192.168.43.242.7:8080/stackserver/index.jsp").get();
			Element table = doc.select("table").get(0);
			Elements rows = table.select("tr");
			
			Element row = rows.get(1);
		    Elements cols = row.select("td");
		    
		    mail=cols.get(1).text();
			}catch(IOException e) {
				System.out.println(e.getMessage());
				System.out.println(e.toString());
			}
		return mail;
	}
}