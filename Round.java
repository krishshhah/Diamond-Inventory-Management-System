import java.io.Serializable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Round extends Diamond implements Serializable { // inherits common attributes from diamond class
	private boolean single;
	private String number;
	private static List<Round> rounds = new ArrayList<Round>(); // arrayList used to manage round objects

	public Round(double price, char colour, String clarity, String cut, String number, double weight, int itemCode,
			boolean sold, Supplier supplier, LocalDate date, boolean single) {
		super(price, colour, clarity, cut, weight, itemCode, sold, supplier, date);
		this.single = single;
		this.number = number;
		rounds.add(this); // Round object added to arrayList everytime it is created
	}

	public boolean getSingle() {
		return single;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public static List<Round> getRounds() {
		return rounds; 
	}

	public static void setRounds(List<Round> rounds) {
		Round.rounds = rounds;
	}

	public static Round add(double price, char colour, String clarity, String cut, String number, double weight,
			int itemCode, String supCode, String dateStr, boolean single, JTextArea textArea) {
		// allows the finished object to be printed in GUI

		Round foundR = Round.find(itemCode); // all classes searched for item code
		Taper foundT = Taper.find(itemCode);
		Baguette foundB = Baguette.find(itemCode);
		try {
			// Checks if price and weight are positive numbers
			if (price <= 0 || weight <= 0) {
				throw new IllegalArgumentException("Price and weight must be positive numbers.");
			}

			// Check if colour is a valid colour character
			if (!Character.isLetter(colour) || "ABC".indexOf(colour) != -1) {
				throw new IllegalArgumentException("Colour must be a valid character.");
			}

			// Check if clarity and cut are non-empty strings
			if (clarity.isEmpty() || cut.isEmpty()) {
				throw new IllegalArgumentException("Clarity and cut must be non-empty strings.");
			}

			// Check if itemCode is a positive integer
			if (itemCode <= 0) {
				throw new IllegalArgumentException("Item code must be a positive integer.");
			}

			// Check if supCode is a non-empty string and the supplier exists
			if (supCode.isEmpty() || Supplier.getSuppliers().get(supCode) == null) { // suppliers hashmap searched
				throw new IllegalArgumentException("Supplier code must be a non-empty string and a valid supplier.");
			}

			LocalDate date = Inventory.dateParse(dateStr); // uses method from Inventory class to parse date as string
			if (date == null) {
				textArea.append("Invalid date format. Please enter the date in the format dd/MM/yyyy. \n");
				return null;
			}
			Supplier chosen = Supplier.getSuppliers().get(supCode); // finds supplier from hashmap
			if (foundR == null && foundT == null && foundB == null) { // if no stock with chosen itemCode exists, Round
																		// object created
				Round round = new Round(price, colour, clarity, cut, number, weight, itemCode, false, chosen, date,
						single);
				textArea.append("Round added: \n" + round);
				return round;
			} else {
				throw new IllegalArgumentException(
						"Stock with item code " + itemCode + " already exists, try a new item code!");
			}

		} catch (IllegalArgumentException e) {
			textArea.append(e.getMessage() + "\n");
		}
		return null;
	}

	public static String remove(int itemCode) { // returns string for easy print
		Round removing = find(itemCode);
		if (removing == null) {
			return "Round with item code " + itemCode + " does not exist";
		} else {
			rounds.remove(removing);
			return "Round with item code " + itemCode + " removed successfully.";
		}
	}

	public static void average(String c, JTextField textField) { // runs to check clarity/cut (both strings), prints in
																	// GUI
		double totalPrice = 0.0;
		double totalWeight = 0.0;
		for (Round round : rounds) { // for loop - looping through array list
			if ((round.getClarity().equals(c) || round.getCut().equals(c)) && round.getSold() == false) { // checks both
																											// cut and
																											// clarity
				// Round item cannot be sold
				totalPrice = totalPrice + round.getPrice(); 
				totalWeight = totalWeight + round.getWeight(); // adds to variables
			}
		}
		if (totalWeight == 0.0) {
			textField.setText("Invalid Input"); // means no stock with that attribute exists
		} else {
			textField.setText(Double.toString(Inventory.round(totalPrice / totalWeight))); // converts double to String for
																				// printing
			// outputs average price/carat figure
		}
	}

	public static void average(char colour, JTextField textField) {
		double totalPrice = 0.0;
		double totalWeight = 0.0;
		char upper = Character.toUpperCase(colour); // handles case error
		for (Round round : rounds) {
			if (round.getColour() == upper && round.getSold() == false) {
				totalPrice = totalPrice + round.getPrice();
				totalWeight = totalWeight + round.getWeight(); // adds to variables
			}
		}
		if (totalWeight == 0.0) {
			textField.setText("Invalid Input");
		} else {
			textField.setText(Double.toString(Inventory.round(totalPrice / totalWeight))); // converts double to String for
																				// printing
		}
	}

	public static void sold(int itemCode, double unpaid, String codeDebt, String date, JTextArea textArea) { // unpaid = selling price
		double profit = 0;
		Round sold = Round.find(itemCode); // handles error: round searched
		if (sold == null) {
			textArea.append("Diamond does not exist");
			return; // exits method early
		}
		if (sold.getSold()) {
			textArea.append("Diamond is already sold");
			return; // exits method early
		}
		
		// Look up the client by code
		Debtor client = Debtor.getClients().get(codeDebt); // seaches clients list to make a debtor (client must be
															// added first (Debtor.add))
		if (client == null) {
			textArea.append("Client with code " + codeDebt + " does not exist."); // handles error if not found
			return;
		}

		// Parse the date and create a new debtor object
		LocalDate dateObj = Inventory.dateParse(date);
		if (dateObj == null) {
			textArea.append("Invalid date format. Please enter the date in the format dd/MM/yyyy. \n");
			return;
		}
		sold.setSold(true); // boolean sold is set to true

		Debtor debtor = new Debtor(client.getName(), client.getCompName(), client.getPhone(), client.getFax(), codeDebt,
				itemCode, unpaid, dateObj, Debtor.getIncrement()); // debtor created using the client object's
																	// attributes
		
		// Calculate the profit and update total profit and sold list
		profit = unpaid - sold.getPrice(); // profit calculated
		Inventory.totalProfit = Inventory.totalProfit + profit; // profit added profit tally
		
		textArea.append("Debtor created: \n");
		textArea.append(debtor.toString()); // debtor printed in textArea using toString() method
	}

	public static void sort(String property) {
		if (rounds == null || rounds.size() <= 1) {
			return;
		}
		mergeSort(property, 0, rounds.size() - 1);
	}

	public static void mergeSort(String property, int left, int right) { // efficient search algorithm: time complexity
																			// - O(n log n)
		if (left >= right) { // base case
			return;
		}
		int mid = (left + right) / 2; // calculate middle
		mergeSort(property, left, mid); // recursively sort left half of list
		mergeSort(property, mid + 1, right); // recursively sort right half of list
		merge(property, left, mid, right); // merge 2 sorted halves
	}

	public static void merge(String property, int left, int mid, int right) {
		int leftSize = mid - left + 1; // size of left list
		int rightSize = right - mid; // size of right list
		Round[] leftArr = new Round[leftSize]; // Create a temporary array to hold the left half of the list
		Round[] rightArr = new Round[rightSize]; // Create a temporary array to hold the right half of the list
		for (int i = 0; i < leftSize; i++) { // populate the array
			leftArr[i] = rounds.get(left + i);
		}
		for (int i = 0; i < rightSize; i++) {
			rightArr[i] = rounds.get(mid + 1 + i);
		}
		int i = 0; // index for the left half of the list
		int j = 0; // index for the right half of the list
		int k = left; // index for the merged list
		while (i < leftSize && j < rightSize) {
			switch (property.toLowerCase()) { // handles capitals
			case "price":
				if (leftArr[i].getPrice() <= rightArr[j].getPrice()) {
					rounds.set(k++, leftArr[i++]); // mergedlist index++ is set to the left list's index++
				} else {
					rounds.set(k++, rightArr[j++]); // mergedlist index++ is set to the right list's index++
				}
				break;
			case "weight":
				if (leftArr[i].getWeight() <= rightArr[j].getWeight()) {
					rounds.set(k++, leftArr[i++]); // rounds.set(index, element(object))
				} else {
					rounds.set(k++, rightArr[j++]);
				}
				break;
			case "colour":
				if (leftArr[i].getColour() <= rightArr[j].getColour()) {
					rounds.set(k++, leftArr[i++]);
				} else {
					rounds.set(k++, rightArr[j++]);
				}
				break;
			case "cut":
				if (leftArr[i].getCut().compareTo(rightArr[j].getCut()) <= 0) {
					rounds.set(k++, leftArr[i++]);
				} else {
					rounds.set(k++, rightArr[j++]);
				}
				break;
			case "clarity":
				if (leftArr[i].getClarity().compareTo(rightArr[j].getClarity()) <= 0) {
					rounds.set(k++, leftArr[i++]);
				} else {
					rounds.set(k++, rightArr[j++]);
				}
				break;
			case "itemcode":
				if (leftArr[i].getItemCode() <= rightArr[j].getItemCode()) {
					rounds.set(k++, leftArr[i++]);
				} else {
					rounds.set(k++, rightArr[j++]);
				}
				break;
			default:
				System.out.println("Invalid sort property: " + property);
				break;
			}
		}
		while (i < leftSize) {
			rounds.set(k++, leftArr[i++]); // add the remaining elements onto sorted array
		}
		while (j < rightSize) {
			rounds.set(k++, rightArr[j++]);
		}
	}

	public static Round find(int itemCode) { // iterative binary search method
		Round.sort("itemcode"); // sorted first by merge sort based upon itemCode
		int low = 0;
		int high = rounds.size() - 1; // to get last index

		while (low <= high) {
			int mid = (low + high) / 2;
			Round midRound = rounds.get(mid);

			if (midRound.getItemCode() == itemCode) {
				return midRound;
			} else if (midRound.getItemCode() < itemCode) {
				low = mid + 1; // updates mid index
			} else {
				high = mid - 1; // updates mid index
			}
		}
		return null;
	}

	public static void totalWeight() {
		for (int i = 0; i < rounds.size(); i++) {
			if (rounds.get(i).getSold() == false) { // iterates through entire arrayList
				Inventory.totalWeight = Inventory.totalWeight + rounds.get(i).getWeight(); // all weights added to
																							// variable
			}
		}
	}

	public static void totalPrice() {
		for (int i = 0; i < rounds.size(); i++) {
			if (rounds.get(i).getSold() == false) { // iterates through entire arrayList
				Inventory.totalPrice = Inventory.totalPrice + rounds.get(i).getPrice(); // all prices added to variable
			}
		}
	}

	@Override
	public String toString() {
		String[] headers = { "Type", "Price($usd)", "Colour", "Clarity", "Cut", "Number", "Weight(ct)", "Item Code",
				"Date Bought", "Supplier", "Supplier code", "Single Cut", "Sold" };
		StringBuilder sb = new StringBuilder();
		for (String header : headers) {
			sb.append(String.format("%-30s", header)); // prints in table format
		}
		sb.append("\n");
		sb.append(String.format("%-30s%-30.2f%-30s%-30s%-30s%-30s%-30.2f%-30d%-30s%-30s%-30s%-30s%-30s\n", "Round",
				getPrice(), getColour(), getClarity(), getCut(), getNumber(), getWeight(), getItemCode(), getDate(),
				getSupplier().getName(), getSupplier().getCode(), getSingle(), getSold()));
		return sb.toString(); // returns String
	}

	public static void printRound(JTextArea textArea) {
		String[] headers = { "Type", "Price($usd)", "Colour", "Clarity", "Cut", "Number", "Weight(ct)", "Item Code",
				"Date Bought", "Supplier", "Supplier code", "Single Cut" };

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
		for (Round round : Round.rounds) {
			if (!round.getSold()) {
				lineBuilder.setLength(0);
				lineBuilder.append("Round").append("\t");
				lineBuilder.append(formatPrice(round.getPrice())).append("\t");
				lineBuilder.append(round.getColour()).append("\t");
				lineBuilder.append(round.getClarity()).append("\t");
				lineBuilder.append(round.getCut()).append("\t");
				lineBuilder.append(round.getNumber()).append("\t");
				lineBuilder.append(formatWeight(round.getWeight())).append("\t");
				lineBuilder.append(round.getItemCode()).append("\t");
				lineBuilder.append(round.getDate()).append("\t");
				lineBuilder.append(round.getSupplier().getName()).append("\t");
				lineBuilder.append(round.getSupplier().getCode()).append("\t");
				lineBuilder.append(round.getSingle()).append("\t");
				lineBuilder.append("\n");
				textArea.append(lineBuilder.toString());
			}
		}
		textArea.append("\n");
	}

	// Helper method to format the price attribute
	private static String formatPrice(double price) {
		return String.format("%.2f", price);
	}

	// Helper method to format the weight attribute
	private static String formatWeight(double weight) {
		return String.format("%.2f", weight);
	}


	public static void clearRounds() { // removes all round objects from rounds arrayList
		rounds.clear();
	}

}