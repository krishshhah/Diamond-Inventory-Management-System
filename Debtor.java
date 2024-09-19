import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Debtor extends Supplier implements Serializable {
	private int soldCode; // itemCode of stock --> debtor soldCode
	private double unpaid;
	private LocalDate date;
	private static int increment = 1; 
	private int number;
	private static Map<String, Debtor> clients = new HashMap<String, Debtor>(); // clients do not require sorting, only
																				// searching
	private static List<Debtor> debtors = new ArrayList<Debtor>(); // ArrayList used for debtor objects: requires
																	// sorting

	public Debtor(String name, String compName, String phone, String fax, String code, boolean isDebtor) { 
		super(name, compName, phone, fax, code, Supplier.addDebtor); // constructor overloading
		// accesses 2nd constructor in Supplier class to prevent debtor object from being added to suppliers HashMap
		clients.put(getCode(), this); // adds directly to clients HashMap
	}

	public Debtor(String name, String compName, String phone, String fax, String code, int soldCode, double unpaid,
			LocalDate date, int number) { 
		/* constructor invoked when a stock (round, taper, baguette) is sold to create a debtor object to manage payment. 
		used in the sold method in other classes.*/
		super(name, compName, phone, fax, code, Supplier.addDebtor);
		this.soldCode = soldCode;
		this.unpaid = unpaid;
		this.date = date;
		this.number = number;
		increment++; // incremented every time object created --> set as the number attribute
		debtors.add(this); // directly added to the debtors ArrayList
	}

	public int getSoldCode() {
		return soldCode;
	} 

	public void setSoldCode(int soldCode) {
		this.soldCode = soldCode;
	}

	public double getUnpaid() {
		return unpaid;
	}

	public void setUnpaid(double unpaid) {
		this.unpaid = unpaid;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getNumber() { // need to fix
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public static List<Debtor> getDebtors() {
		return debtors;
	}

	public static void setDebtors(List<Debtor> debtors) {
		Debtor.debtors = debtors;
	}

	public static void setClients(Map<String, Debtor> clients) {
		Debtor.clients = clients;
	}

	public static int getIncrement() {
		return increment;
	}

	public static void setIncrement(int increment) {
		Debtor.increment = increment;
	}

	public static Map<String, Debtor> getClients() {
		return clients;
	}

	public static String add(String name, String compName, String phone, String fax, String code) {
		if (clients.containsKey(code)) {
			return "A client with item code " + code + " already exists."; // handles error to make sure client of
																			// same item code not created
		} else {
			// Create new debtor object and add to list
			// does not require error handling: all inputs are strings
			Debtor debtor = new Debtor(name, compName, phone, fax, code, true);
			 // debtor object created and added to clients HashMap - not added to suppliers HashMap
			return "Client created successfully!";
		}
	} 

	public static String remove(String code) {
		if (clients.containsKey(code)) { 
			clients.remove(code);
			return "Client with code " + code + " removed successfully.";
		}
		return "A client with item code " + code + " does not exist."; // handles error if debtor not in hashMap
	}

	public static void mergeSort(List<Debtor> arr) {
		// Base case: if the input array has only one element, it is already sorted
		if (arr.size() <= 1) {
			return;
		}

		// Find the middle index of the array
		int mid = arr.size() / 2;
		// Split the array into left and right halves
		ArrayList<Debtor> left = new ArrayList<>(arr.subList(0, mid));
		ArrayList<Debtor> right = new ArrayList<>(arr.subList(mid, arr.size()));

		// Recursively call mergeSort on the left and right halves
		mergeSort(left);
		mergeSort(right);

		// Merge the sorted left and right halves back together into the original array
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < left.size() && j < right.size()) {
			// Compare the elements at the ith index of the left array and the jth index of
			// the right array
			// If the element in the left array is smaller, set it at the kth index of the
			// original array
			if (left.get(i).getNumber() < right.get(j).getNumber()) {
				arr.set(k, left.get(i));
				i++;
				// Otherwise, set the element in the right array at the kth index of the
				// original array
			} else {
				arr.set(k, right.get(j));
				j++;
			}
			// Move to the next index of the original array
			k++;
		}

		// If there are any remaining elements in the left or right arrays, add them to
		// the end of the original array
		while (i < left.size()) {
			arr.set(k, left.get(i));
			i++;
			k++;
		}

		while (j < right.size()) {
			arr.set(k, right.get(j));
			j++;
			k++;
		}
	}

	public static void sort() {
		// Call mergeSort on the debtors ArrayList to sort it
		mergeSort(debtors);
	}

	public static Debtor find(int number) { // uses iterative binary search to find a particular debtor
		sort(); // ensures the arrayList is perfectly sorted before it is found
		int low = 0;
		int high = debtors.size() - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			Debtor midDebtor = debtors.get(mid);
			if (midDebtor.getNumber() == number) {
				return midDebtor;
			} else if (midDebtor.getNumber() < number) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return null;
	}
	
	public static String updateUnpaid(int number, double amount) { // Strings returned to allow for easy printing in GUI
	    Debtor found = Debtor.find(number);
	    double update = 0.0;
	    if (found == null) {
	        return "Debtor with number " + number + " does not exist"; // handles error if incorrect sold code is
			// inputted
	    }
	    if (amount <= found.getUnpaid()) {
	        update = found.getUnpaid() - amount;
	        update = Inventory.round(update);
	        found.setUnpaid(update); // sets the new value of the outstanding balance difference left

	        if (found.getUnpaid() == 0.0) {
	            debtors.remove(found); // removes the debtor from arrayList once payment is complete
	        }
	    } else if (amount > found.getUnpaid()) { // handle error: if amount paid > balance remaining
	        return "Cannot receive a payment more than outstanding balance";
	    }
	    return "Debtor updated: \n" + found; // toString() activated
	}

	public static String updateDays(int number, int days) { // Strings returned to allow for easy printing in GUI
		Debtor found = Debtor.find(number);
		if (found == null) {
			return "Debtor with number " + number + " does not exist"; // handles error if incorrect sold code is
																		// inputted
		} else {
			LocalDate update = found.getDate().plusDays(days); // adds days to credit date
			found.setDate(update); // sets the date to the updated new date
		}
		return "Debtor updated: \n" + found; // toString() activated
	}

	public static void reminder() { // gives reminder for payments that will be due soon, pops up once login correct
		for (Debtor debtor : debtors) {
			long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), debtor.getDate()); // calculates the days between
																						// current date and due date
			if (daysLeft == 10) {
				JOptionPane.showMessageDialog(null, "Payment of $" + (debtor.getUnpaid()) + " from "
						+ (debtor.getName()) + " of item " + (debtor.getSoldCode()) + " is due in 10 days!\n"); // creates
																												// a pop
																												// up
			} else if (daysLeft == 5) {
				JOptionPane.showMessageDialog(null, "Payment of $" + (debtor.getUnpaid()) + " from "
						+ (debtor.getName()) + " of item " + (debtor.getSoldCode()) + " is due in 5 days!\n");
			} else if (daysLeft == 0) {
				JOptionPane.showMessageDialog(null, "Payment of $" + (debtor.getUnpaid()) + " from "
						+ (debtor.getName()) + " of item " + (debtor.getSoldCode()) + " is due today!\n");
			}
		}
	}

	@Override
	public String toString() { // used when finding debtors with sold codes
		String[] headers;
		String format;
		if (Debtor.getDebtors().contains(this)) {
			headers = new String[] { "Debtor Name", "Company Name", "Phone", "Fax", "Code", "Sold Code", "Unpaid",
					"Date", "Number" };
			format = "%-30s%-30s%-30s%-30s%-30s%-30d%-30.2f%-30s%-30d\n"; // prints in a table format with attributes
																			// underneath: better readability of stock
		} else {
			headers = new String[] { "Debtor Name", "Company Name", "Phone", "Fax", "Code" };
			format = "      %-30s%-30s%-30s%-30s%-30s\n";
		}
		StringBuilder sb = new StringBuilder();
		for (String header : headers) {
			sb.append(String.format("%-30s", header));
		}
		sb.append("\n");
		sb.append(String.format(format, getName(), getCompName(), getPhone(), getFax(), getCode(), soldCode, unpaid,
				date, number));
		return sb.toString();
	}

	public static void printDebtor(JTextArea textArea) {
		String[] headers = { "Debtor Name", "Company", "Phone", "Fax", "Code", "Sold Code", "Unpaid", "Payback Date",
				"Days left", "Number" };

		// Build the format string with variable width specifiers based on the maximum
		// width of each column
		String format = Inventory.buildFormatString(headers);

		// Print the headers
		StringBuilder headerBuilder = new StringBuilder();
		for (String header : headers) {
			headerBuilder.append(header).append("\t");
		}
		headerBuilder.append("\n");
		textArea.append(headerBuilder.toString());

		// Print the round objects with aligned columns
		StringBuilder lineBuilder = new StringBuilder();
		for (Debtor debtor : Debtor.debtors) {
			long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), debtor.getDate());
			lineBuilder.setLength(0);
			lineBuilder.append(debtor.getName()).append("\t");
			lineBuilder.append(debtor.getCompName()).append("\t");
			lineBuilder.append(debtor.getPhone()).append("\t");
			lineBuilder.append(debtor.getFax()).append("\t");
			lineBuilder.append(debtor.getCode()).append("\t");
			lineBuilder.append(debtor.getSoldCode()).append("\t");
			lineBuilder.append(formatUnpaid(debtor.getUnpaid())).append("\t");
			lineBuilder.append(debtor.getDate()).append("\t");
			lineBuilder.append(daysLeft).append("\t");
			lineBuilder.append(debtor.getNumber()).append("\t");
			lineBuilder.append("\n");
			textArea.append(lineBuilder.toString());
		}
		textArea.append("\n");
	}

	private static String formatUnpaid(double unpaid) {
		return String.format("%.2f", unpaid);
	}

	public static void printClient(JTextArea textArea) {
		String[] headers = { "Client Name", "Company", "Phone", "Fax", "Code" };

		// Build the format string with variable width specifiers based on the maximum
		// width of each column
		String format = Inventory.buildFormatString(headers);

		// Print the headers
		StringBuilder headerBuilder = new StringBuilder();
		for (String header : headers) {
			headerBuilder.append(header).append("\t");
		}
		headerBuilder.append("\n");
		textArea.append(headerBuilder.toString());

		// Print the round objects with aligned columns
		StringBuilder lineBuilder = new StringBuilder();
		for (Debtor client : Debtor.clients.values()) {
			lineBuilder.setLength(0);
			lineBuilder.append(client.getName()).append("\t");
			lineBuilder.append(client.getCompName()).append("\t");
			lineBuilder.append(client.getPhone()).append("\t");
			lineBuilder.append(client.getFax()).append("\t");
			lineBuilder.append(client.getCode()).append("\t");
			lineBuilder.append("\n");
			textArea.append(lineBuilder.toString());
		}
		textArea.append("\n");
	}

	public static void clearDebtors() {
		debtors.clear();
	}

	public static void clearClients() {
		clients.clear();
	}

}
