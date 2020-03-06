package application;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;

public class Controller implements Initializable {
		@FXML private Label watch;
		@FXML private Label weeken;
		@FXML private Label weathering;
		@FXML private Label mail;
		@FXML private StackPane background;
		private int s1;
		private int s2;
		private Weather ft=new Weather();
		private Mail mt=new Mail();
		private String wfEn = ft.getEn();
		
		//File one=new File("/home/pi/Desktop/image/Clearly.gif");
		//File two=new File("/home/pi/Desktop/image/cloudy.gif");
		//File three=new File("/home/pi/Desktop/image/partlycloudy.gif");
		//File four=new File("/home/pi/Desktop/image/rain.gif");
		//File five=new File("/home/pi/Desktop/image/snow.gif");
		File one=new File("C:\\Users/User51/Desktop/image/Clearly.gif");
		File two=new File("C:\\Users/User51/Desktop/image/cloudy.gif");
		File three=new File("C:\\Users/User51/Desktop/image/partlycloudy.gif");
		File four=new File("C:\\Users/User51/Desktop/image/rain.gif");
		File five=new File("C:\\Users/User51/Desktop/image/snow.gif");
		
		private Image rain=new Image(four.toURI().toString());
		private Image clear=new Image(one.toURI().toString());
		private Image cloudy=new Image(two.toURI().toString());
		private Image partlycloudy=new Image(three.toURI().toString());
		private Image snow=new Image(five.toURI().toString());
		//private Image rain=new Image(ResourceLoader.load("image/rain.gif"));
		//private Image clear=new Image(ResourceLoader.load("image/Clear.gif"));
		//private Image cloudy=new Image(ResourceLoader.load("image/cloudy.gif"));
		//private Image partlycloudy=new Image(ResourceLoader.load("image/partlycloudy.gif"));
		//private Image snow=new Image(ResourceLoader.load("image/snow.gif"));
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {

			Thread thread = new Thread() {
				@Override
				public void run() {
					Calendar test = Calendar.getInstance();
					String s3=null; //요일
					
					int week = test.get(Calendar.DAY_OF_WEEK);
					switch(week) { 
					case Calendar.MONDAY:
						s3="Mon"; break;
					case Calendar.TUESDAY:
						s3="Tue"; break;
					case Calendar.WEDNESDAY:
						s3="Wed"; break;
					case Calendar.THURSDAY:
						s3="Thu"; break;
					case Calendar.FRIDAY:
						s3="Fri"; break;
					case Calendar.SATURDAY:
						s3="Sat"; break;
					default:
						s3="Sun";
				}
							
					SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
					while(true) {
						String strTime=sdf.format(new Date());
						String we = String.format("%s", s3);
						String wfKor = ft.getKor();
						String wfmail = mt.getMail();
						Platform.runLater(()->{
							switch(wfEn)//weather패키지의 파싱데이터를 물어와 gif이미지로 바꿔준다
							{
								case "Clear":
									BackgroundImage a=new BackgroundImage(clear,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
									background.setBackground(new Background(a));
									break;
								case "Partly Cloudy":case "Mostly Cloudy": 
									BackgroundImage b=new BackgroundImage(partlycloudy,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
									background.setBackground(new Background(b));
									break;
								case "Cloudy":
									BackgroundImage c=new BackgroundImage(cloudy,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
									background.setBackground(new Background(c));
									break;
								case "Rain":case "Snow/Rain":
									BackgroundImage d=new BackgroundImage(rain,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
									background.setBackground(new Background(d));
									break;
								default:
									BackgroundImage e=new BackgroundImage(snow,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
									background.setBackground(new Background(e));
									break;
							}
							watch.setText(strTime);
							weeken.setText(we);
							weathering.setText(wfKor);
							mail.setText(wfmail);
						});
						try {Thread.sleep(1000);}catch(InterruptedException e) {}
					}
				}
			};
			thread.setDaemon(true);
			thread.start();
		};
}