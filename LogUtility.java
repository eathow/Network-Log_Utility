import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class LogUtility {
	
private ArrayList<LogEntry> listLogEntries;

/** 𝑑𝑒𝑓𝑎𝑢𝑙𝑡 𝑐𝑜𝑛𝑠𝑡𝑟𝑢𝑐𝑡𝑜𝑟: initializes the 𝑓𝑖𝑛𝑎𝑙 field 𝑙𝑖𝑠𝑡𝐿𝑜𝑔𝐸𝑛𝑡𝑟𝑦. */
public LogUtility() {
	listLogEntries = new ArrayList<LogEntry>();
}

/** 𝑝𝑎𝑟𝑠𝑒𝐹𝑖𝑙𝑒: reads all lines from the provided text file 􏰩𝑁𝑒𝑡𝑤𝑜𝑟𝑘. 𝑙𝑜𝑔􏰪. The method extracts a line from the text file, parses the line by the comma delimiter, and creates a 𝐿𝑜𝑔𝐸𝑛𝑡𝑟𝑦 instance. If the instance is created successfully, the instance is added to the 𝑙𝑖𝑠𝑡𝐿𝑜𝑔𝐸𝑛𝑡𝑟𝑦 field. If an exception is raised from the 𝐿𝑜𝑔𝐸𝑛𝑡𝑟𝑦 constructor, print the message: 𝑆𝑘𝑖𝑝𝑝𝑖𝑛𝑔 𝑙𝑖𝑛𝑒: ≪ 𝑡h𝑒 𝑙𝑖𝑛𝑒 𝑏𝑒𝑖𝑛𝑔 𝑠𝑘𝑖𝑝𝑝𝑒𝑑 ≫. See Figure 3
Hints:
	1. The 𝐹𝑖𝑙𝑒𝑁𝑜𝑡𝐹𝑜𝑢𝑛𝑑𝐸𝑥𝑐𝑒𝑝𝑡𝑖𝑜𝑛 should not be handled within this method.
	2. Use 𝑆𝑐𝑎𝑛𝑛𝑒𝑟 class to read the input file’s lines. This is similar to reading from the standard command line.
	3. Use the String class’ split method to parse each line. The split method returns an array: 𝑆𝑡𝑟𝑖𝑛𝑔 𝑙𝑖𝑛𝑒 􏰫 "𝑎, 𝑏, 𝑐, 𝑑"; 𝑆𝑡𝑟𝑖𝑛𝑔 𝑎𝑟𝑟􏰬 􏰭 􏰫 𝑙𝑖𝑛𝑒. 𝑠𝑝𝑙𝑖𝑡􏰩", "􏰪;
 * @throws FileNotFoundException */
public boolean parseFile(String fileName) throws FileNotFoundException {
	File text = new File(fileName);
	Scanner snr = new Scanner(text);
	String line = "";
	String array[];	
	while(snr.hasNextLine()) {
		try {
			line = snr.nextLine();
			array = line.split(",");
			LogEntry instance = new LogEntry(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
			listLogEntries.add(instance);
		}
		catch(InstantiationException ex) {
			System.err.println("Skipping Line: " + line);
		}
	}
	return true;
}

/** 𝑔𝑒𝑡𝐵𝑒𝑡𝑤𝑒𝑒𝑛: returns a list of records between the date string parameters (inclusive). The two parameters are the start and end dates respectively. First, convert the parameters from 𝑆𝑡𝑟𝑖𝑛𝑔 to 𝐷𝑎𝑡𝑒 in order to perform the search. 𝑃𝑎𝑟𝑠𝑒𝐸𝑥𝑐𝑒𝑝𝑡𝑖𝑜𝑛 should be handled by this method.
Hint:
	The 𝐷𝑎𝑡𝑒 class provides the 𝑐𝑜𝑚𝑝𝑎𝑟𝑒𝑇𝑜 method for date‐to‐date object comparison. The methods returns:
	1. 0, if the two date objects are equal
	2. A number <􏰮 0, if the 𝑙𝑒𝑓𝑡 object is chronologically smaller
	3. A number >􏰯 0, if the 𝑟𝑖𝑔h𝑡 object is chronologically smaller 
	
	*Date date1 = convert Param1 to java.util.Date
	*Date date2 = convert Param2 to java.util.Date
	*int result = date1.compareTo(date2);
	*if(result == 0) { date1=date2; }
	*if(result < 0) { date1 < date2; }
	*if(result > 0) { date1 > date2; }
 * @throws ParseException 
	*
	*/
public ArrayList<LogEntry> getBetween(String startDate, String endDate) throws ParseException {
	DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	Date date1 = format.parse(startDate);
	Date date2 = format.parse(endDate);
	ArrayList<LogEntry> dateArray = new ArrayList<LogEntry>();
	for(int i=0; i<listLogEntries.size(); i++) {
		Date dateI = format.parse(listLogEntries.get(i).getDateTime());
		int result1 = dateI.compareTo(date1);
		int result2 = dateI.compareTo(date2);
		if((result1 >= 0) && (result2 <= 0)) {
			dateArray.add(listLogEntries.get(i));
			}
	}
	return dateArray;
}

public enum variable {
	 getSequence, getDateTime, getSourceIP, getDestinationIP, getProtocol, getPacketSize, getComment;
}

public LogEntry common(String variable, variable var) {
	switch(var) {
	case getSequence:
		for(int i=0; i < listLogEntries.size(); i++) {
			if(listLogEntries.get(i).getSequence().equals(variable)) {
			return listLogEntries.get(i);
			}
		}
		break;
	case getDateTime:
		for(int i=0; i < listLogEntries.size(); i++) {
			if(listLogEntries.get(i).getDateTime().equals(variable)) {
			return listLogEntries.get(i);
			}
		}
		break;
	case getSourceIP:
		for(int i=0; i < listLogEntries.size(); i++) {
			if(listLogEntries.get(i).getSourceIP().equals(variable)) {
			return listLogEntries.get(i);
			}
		}
		break;
	case getDestinationIP:
		for(int i=0; i < listLogEntries.size(); i++) {
			if(listLogEntries.get(i).getDestinationIP().equals(variable)) {
			return listLogEntries.get(i);
			}
		}
		break;
	case getProtocol:
		for(int i=0; i < listLogEntries.size(); i++) {
			if(listLogEntries.get(i).getProtocol().equals(variable)) {
			return listLogEntries.get(i);
			}
		}
		break;
	case getPacketSize:
		for(int i=0; i < listLogEntries.size(); i++) {
			if(listLogEntries.get(i).getPacketSize().equals(variable)) {
			return listLogEntries.get(i);
			}
		}
		break;
	case getComment:
		for(int i=0; i < listLogEntries.size(); i++) {
			if(listLogEntries.get(i).getComment().equals(variable)) {
			return listLogEntries.get(i);
			}
		}
		break;
	}
	return null;
}
/** The next seven functions return the first instance of each parameter. To complete these functions we need to 
 * create a common method to increase efficiency. This method should select the proper field and perform the search */
/** 𝑓𝑖𝑛𝑑𝐹𝑖𝑟𝑠𝑡*: the remaining 7 required methods allow searching by a specific field. For simplicity, these methods return only the first occurrence that matches the search criteria (i.e. parameter). Please note that it is highly inefficient to implement each method independently, therefore you must create a common method which contains the search subroutine. This method should select the proper field and perform the search.
Hint:
	One way to distinguish between the fields in the common method is to create an enumerator and use a switch structure to select the proper field.*/
public LogEntry findFirstId(String sequence) {
	// The psuedocode for common looked like: common(..) switch { go over the ArrayList, if id == searchID, return 
	// that object, else check the next entry in the array list
	return common(sequence, variable.getSequence);
}

public LogEntry findFirstTimestamp(String dateTime) {
	return common(dateTime, variable.getDateTime);
}

public LogEntry findFirstSourceIP(String sourceIP) {
	return common(sourceIP, variable.getSourceIP);
}

public LogEntry findFirstDestinationIP(String destinationIP) {
	return common(destinationIP, variable.getDestinationIP);
}

public LogEntry findFirstProtocol(String protocol) {
	return common(protocol, variable.getProtocol);
}

public LogEntry findFirstLength(String packetSize) {
	return common(packetSize, variable.getPacketSize);
}

public LogEntry findFirstDescription(String comment) {
	return common(comment, variable.getComment);
}

/** 𝑡𝑜𝑆𝑡𝑟𝑖𝑛𝑔: returns the following String. Replace  with the number of entries in 𝑙𝑖𝑠𝑡𝐿𝑜𝑔𝐸𝑛𝑡𝑟𝑖𝑒𝑠. Figure 3 𝐿𝑜𝑔𝑈𝑡𝑖𝑙𝑖𝑡𝑦: 𝑡h𝑒𝑟𝑒 𝑎𝑟𝑒  𝑟𝑒𝑐𝑜𝑟𝑑𝑠 */
public String toString() {
	String ts = "LogUtility: There are " + listLogEntries.size() + " records";
	return ts;
}
}
