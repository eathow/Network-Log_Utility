import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
	
private String sequence;
private String packetSize;
private String dateTime;
private String sourceIP;
private String destinationIP;
private String protocol;
private String comment;

/** LogEntry, Non‐default constructor, initializes the class’ fields after validating the information. If any of the values do not conform to
the restrictions shown in Figure 1, the constructor throws an exception of type 𝐼𝑛𝑠𝑡𝑎𝑛𝑡𝑖𝑎𝑡𝑖𝑜𝑛𝐸𝑥𝑐𝑒𝑝𝑡𝑖𝑜𝑛 with the message "𝑂𝑛𝑒 𝑜𝑟 𝑚𝑜𝑟𝑒 𝑣𝑎𝑙𝑢𝑒𝑠 𝑎𝑟𝑒 𝑖𝑛𝑣𝑎𝑙𝑖𝑑" 
Hint:
 A 𝑆𝑡𝑟𝑖𝑛𝑔𝑠 maybe converted to an 𝑖𝑛𝑡 using the method 𝐼𝑛𝑡𝑒𝑔𝑒𝑟. 𝑝𝑎𝑟𝑠𝑒𝐼𝑛𝑡􏰩 􏰪. e.g. 𝑖𝑛𝑡 𝑖 􏰫 𝐼𝑛𝑡𝑒𝑔𝑒𝑟. 𝑝𝑎𝑟𝑠𝑒𝐼𝑛𝑡􏰩"55"􏰪;
 * @throws InstantiationException */
public LogEntry(String getSequence, String getDateTime, String getSourceIP, String getDestinationIP, String getProtocol, String getPacketSize, String getComment) throws InstantiationException {
	 /** This is a way to solve the first exception, done in class. We use parsing to find an int in a string */
	 try {
		 int seqNum = Integer.parseInt(getSequence);
		 int packetS = Integer.parseInt(getPacketSize);
		//sequence must be between 1 & MAX_VALUE
		 if(seqNum < 1 || seqNum > Integer.MAX_VALUE) {
			 throw new InstantiationException("One or more values are invalid");
		 }
		//packetSize Between 1 & 1,500
		 if(packetS < 1 || packetS > 1500) {
				throw new InstantiationException("One or more values are invalid");
		 }
		//dateTime Exactly 19 characters
		 if(getDateTime.length() != 19) {
			 throw new InstantiationException("One or more values are invalid");
		 }
		//sourceIP Cannot be empty or null
		 if(getSourceIP == null || getSourceIP.equals("")) {
			 throw new InstantiationException("One or more values are invalid");
		 }
		//destinationIP Cannot be empty or null
		 if(getDestinationIP == null || getDestinationIP.equals("")) {
			 throw new InstantiationException("One or more values are invalid");
		 }
		//protocol Either TCP or UDP
		 if(!getProtocol.equals("TCP") && !getProtocol.equals("UDP")) {
			 throw new InstantiationException("One or more values are invalid");
		 }
		//comment No restrictions 
	 }
	 catch(NumberFormatException ex) {
		 throw new InstantiationException("One or more values are invalid");
	 }
	 sequence = getSequence;
	 dateTime = getDateTime;
	 sourceIP = getSourceIP;
	 destinationIP = getDestinationIP;
	 protocol = getProtocol;
	 packetSize = getPacketSize;
	 comment = getComment;
}

/** */
public String getSequence() {
	return sequence;
}

/** */
public String getDateTime() {
	return dateTime;
}

/** 𝑔𝑒𝑡𝐷𝑎𝑡𝑒𝑇𝑖𝑚𝑒𝐴𝑠𝐷𝑎𝑡𝑒, returns the value of 𝑑𝑎𝑡𝑒𝑇𝑖𝑚𝑒 field as a 𝐷𝑎𝑡𝑒 object. Use 𝑆𝑖𝑚𝑝𝑙𝑒𝐷𝑎𝑡𝑒𝐹𝑜𝑟𝑚𝑎𝑡 with
pattern "𝑀𝑀/𝑑𝑑/𝑦𝑦𝑦 𝐻𝐻: 𝑚𝑚: 𝑠𝑠". Note that Java has multiple 𝐷𝑎𝑡𝑒 classes, make sure to use 𝑗𝑎𝑣𝑎. 𝑢𝑡𝑖𝑙. 𝐷𝑎𝑡𝑒*/
String dateTimeAsDate = null;
public Date getDateTimeAsDate() throws ParseException {
	String timeAsDate = "𝑀𝑀/𝑑𝑑/𝑦𝑦𝑦 𝐻𝐻: 𝑚𝑚: 𝑠𝑠";
	SimpleDateFormat dateTaD = new SimpleDateFormat(timeAsDate);
	Date dateTimeAsDate = dateTaD.parse(timeAsDate);
	return dateTimeAsDate;
}

/** */
public String getSourceIP() {
	return sourceIP;
}

/** */
public String getDestinationIP() {
	return destinationIP;
}

/** */
public String getProtocol() {
	return protocol;
}

public String getPacketSize() {
	return packetSize;
}

/** */
public String getComment() {
	return comment;
}

/** 𝑡𝑜𝑆𝑡𝑟𝑖𝑛𝑔: returns a comma delimited String consisting of the class’ 7 fields.
 ≪ 𝑣𝑎𝑙𝑢𝑒 𝑜𝑓 𝑠𝑒𝑞𝑢𝑒𝑛𝑐𝑒 ≫ , ≪ 𝑣𝑎𝑙𝑢𝑒 𝑜𝑓 𝑑𝑎𝑡𝑒𝑇𝑖𝑚𝑒 ≫ , ...*/
public String toString() {
    String tostring = getSequence() + ", " + getDateTime() + ", " + getSourceIP() + ", " + getDestinationIP() + ", " + getProtocol() + ", " + getPacketSize() + ", "+ getComment();
	return tostring;
}
}
