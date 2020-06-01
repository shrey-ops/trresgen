package utilis;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;


public class Email {
	
	
	//reportFileName = TestExecutionResultFileName
	/*	@Test(description="report")
		public void first() throws Exception {
			execute("TestReportNeuro Genesis-Automation Testing-Report.html");
		}*/
		
		
		
		
		public static void execute(String reportFileName) throws Exception

		{
			
		String path="C:\\Users\\Shrey\\eclipse-workspace1\\Tresgen\\TestReport\\TresgenReporting.html";

		String[] to={"shrey.parashar@metacube.com"};
		String[] cc={};
		String[] bcc={};

		Email.email("shrey.parashar@metacube.com",
		"vioedktqfdynyslx",
		"smtp.gmail.com",
		"465",
		"true",
		"true",
		true,
		"javax.net.ssl.SSLSocketFactory",
		"false",
		to,
		cc,
		bcc,
		"Automation Report",
		"<Contents if any>",
		path,
		reportFileName);
		}

		public static boolean email(String userName,String passWord,String host,
		String port,
		String starttls,
		String auth,
		boolean debug,
		String socketFactoryClass,
		String fallback,
		String[] to,
		String[] cc,
		String[] bcc,
		String subject,
		String text,
		String attachmentPath,
		String attachmentName){

		//Object Instantiation of a properties file.
		Properties props = new Properties();

		props.put("mail.smtp.user", userName);

		props.put("mail.smtp.host", host);

		if(!"".equals(port)){
		props.put("mail.smtp.port", port);
		}

		if(!"".equals(starttls)){
		props.put("mail.smtp.starttls.enable",starttls);
		props.put("mail.smtp.auth", auth);
		}

		if(debug){

		props.put("mail.smtp.debug", "true");

		}else{

		props.put("mail.smtp.debug", "false");

		}

		if(!"".equals(port)){
		props.put("mail.smtp.socketFactory.port", port);
		}
		if(!"".equals(socketFactoryClass)){
		props.put("mail.smtp.socketFactory.class",socketFactoryClass);
		}
		if(!"".equals(fallback)){
		props.put("mail.smtp.socketFactory.fallback", fallback);
		}

		try{

		Session session = Session.getDefaultInstance(props, null);

		session.setDebug(debug);

		MimeMessage msg = new MimeMessage(session);

		msg.setText(text);

		msg.setSubject(subject);

		Multipart multipart = new MimeMultipart();
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(attachmentPath);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(attachmentName);
		multipart.addBodyPart(messageBodyPart);

		msg.setContent(multipart);
		msg.setFrom(new InternetAddress(userName));

		for(int i=0;i<to.length;i++){
		msg.addRecipient(Message.RecipientType.TO, new
		InternetAddress(to[i]));
		}

		for(int i=0;i<cc.length;i++){
		msg.addRecipient(Message.RecipientType.CC, new
		InternetAddress(cc[i]));
		}

		for(int i=0;i<bcc.length;i++){
		msg.addRecipient(Message.RecipientType.BCC, new
		InternetAddress(bcc[i]));
		}

		msg.saveChanges();

		Transport transport = session.getTransport("smtp");

		transport.connect(host, userName, passWord);

		transport.sendMessage(msg, msg.getAllRecipients());

		transport.close();

		return true;

		} catch (Exception mex){
		mex.printStackTrace();
		return false;
		}
		}
		}
	
	


