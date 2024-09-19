import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Taper extends Diamond implements Serializable {
	private int pieces;
	private static List<Taper> tapers = new ArrayList<Taper>(); // arrayList of Taper objects

	public Taper(double price, char colour, String clarity, String cut, double weight, int itemCode, boolean sold,
			Supplier supplier, LocalDate date, int pieces) { // need to edit code to handle input
																// exceptions
		super(price, colour, clarity, cut, weight, itemCode, sold, supplier, date);
		this.pieces = pieces;
		tapers.add(this); // object added to arraylist everytime it is created
	}

	public int getPieces() {
		return pieces;
	}

	public void setPieces(int pieces) {
		this.pieces = pieces;
	}

	public static List<Taper> getTapers() {
		return tapers;
	}

	public static void setTapers(List<Taper> tapers) {
		Taper.tapers = tapers;
	}

	public static Taper add(double price, char colour, String clarity, String cut, double weight, int itemCode,
			String supCode, String dateStr, int pieces, JTextArea textArea) {
		Round foundR = Round.find(itemCode);
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
			if (supCode.isEmpty() || Supplier.getSuppliers().get(supCode) == null) {
				throw new IllegalArgumentException("Supplier code must be a non-empty string and a valid supplier.");
			}

			LocalDate date = Inventory.dateParse(dateStr);
			if (date == null) {
				textArea.append("Invalid date format. Please enter the date in the format dd/MM/yyyy. \n");
				return null;
			}
			Supplier chosen = Supplier.getSuppliers().get(supCode);
			if (foundR == null && foundT == null && foundB == null) {
				Taper taper = new Taper(price, colour, clarity, cut, weight, itemCode, false, chosen, date, pieces);
				textArea.append("Taper added: \n" + taper);
				return taper;
			} else {
				throw new IllegalArgumentException(
						"Stock with item code " + itemCode + " already exists, try a new item code!");
			}

		} catch (IllegalArgumentException e) {
			textArea.append(e.getMessage() + "\n");
		}
		return null;
	}

	public static String remove(int itemCode) {
		Taper removing = find(itemCode);
		if (removing == null) {
			return "Taper with item code " + itemCode + " does not exist";
		} else {
			tapers.remove(removing);
			return "Taper with item code " + itemCode + " removed successfully.";
		}
	}

	public static void average(String c, JTextField textField) {
		double totalPrice = 0.0;
		double totalWeight = 0.0;
		for (Taper taper : tapers) {
			if ((taper.getClarity().equals(c) || taper.getCut().equals(c)) && taper.getSold() == false) { // checks both
				totalPrice = totalPrice + taper.getPrice();
				totalWeight = totalWeight + taper.getWeight();
			}
		}
		if (totalWeight == 0.0) {
			textField.setText("Invalid Input"); // or you can return any other default value
		} else {
			textField.setText(Double.toString(Inventory.round(totalPrice / totalWeight)));
		}
	}

	public static void average(char colour, JTextField textField) {
		double totalPrice = 0.0;
		double totalWeight = 0.0;
		char upper = Character.toUpperCase(colour);
		for (Taper taper : tapers) {
			if (taper.getColour() == upper && taper.getSold() == false) {
				totalPrice = totalPrice + taper.getPrice();
				totalWeight = totalWeight + taper.getWeight();
			}
		}
		if (totalWeight == 0.0) {
			textField.setText("Invalid Input");
		} else {
			textField.setText(Double.toString(Inventory.round(totalPrice / totalWeight)));
		}
	}

	public static void sold(int itemCode, double unpaid, String codeDebt, String date, int pieces, JTextArea textArea) {
		// allows a small number of tapers to be sold from a larger batch
		double piecePrice = 0;
		double pieceWeight = 0;
		double profit = 0;
		// Check if the diamond is already sold
		Taper sold = Taper.find(itemCode);
		if (sold == null) {
			textArea.append("Diamond does not exist");
			return; // exits method early
		}
		if (sold.getSold()) {
			textArea.append("Diamond is already sold");
			return; // exits method early
		}
		if ((sold.getPieces() - pieces) < 0) { 
			textArea.append("Stock does not have these many pieces"); // handles error if pieces entered are more than
																		// existing
			return; // exits method early
		}

		// Look up the client by code
		Debtor client = Debtor.getClients().get(codeDebt); // seaches clients list to make a debtor (client must be
															// added first (Debtor.add))
		if (client == null) {
			textArea.append("Client with code " + codeDebt + " does not exist.");
			return;
		}

		// Parse the date and create a new debtor object
		LocalDate dateObj = Inventory.dateParse(date);
		if (dateObj == null) {
			textArea.append("Invalid date format. Please enter the date in the format dd/MM/yyyy. \n");
			return;
		}
		Debtor debtor = new Debtor(client.getName(), client.getCompName(), client.getPhone(), client.getFax(), codeDebt,
				itemCode, unpaid, dateObj, Debtor.getIncrement());
		
		piecePrice = sold.getPrice() / sold.getPieces(); // calculates average price per piece for profit
		pieceWeight = sold.getWeight() / sold.getPieces(); // calculates average weight per piece for profit

		// updates current inventory - assumes all individual tapers weigh the same in a big batch
		sold.setPrice((sold.getPrice() - (piecePrice * pieces))); // removes the price amount of pieces sold from the
																	// total pieces
		sold.setWeight((sold.getWeight() - (pieceWeight * pieces))); // removes the weight amount of pieces sold from
																		// the total pieces
		sold.setPieces((sold.getPieces() - pieces)); // removes sold piece amount from original stock
		
		if (sold.getPieces() == 0) { // if all pieces sold
			sold.setSold(true);
		}

		// Calculate the profit and update total profit and sold list
		profit = unpaid - (piecePrice * pieces);
		Inventory.totalProfit = Inventory.totalProfit + profit;
		
		textArea.append("Debtor created: \n");
		textArea.append(debtor.toString()); // debtor printed in textArea using toString() method
	}

	public static void sort(String property) {
		if (tapers == null || tapers.size() <= 1) {
			return;
		}
		mergeSort(property, 0, tapers.size() - 1);
	}

	public static void mergeSort(String property, int left, int right) {
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
		Taper[] leftArr = new Taper[leftSize]; // Create a temporary array to hold the left half of the list
		Taper[] rightArr = new Taper[rightSize]; // Create a temporary array to hold the right half of the list
		for (int i = 0; i < leftSize; i++) { // populate the arrays
			leftArr[i] = tapers.get(left + i);
		}
		for (int i = 0; i < rightSize; i++) {
			rightArr[i] = tapers.get(mid + 1 + i);
		}
		int i = 0; // index for the left half of the list
		int j = 0; // index for the right half of the list
		int k = left; // index for the merged list
		while (i < leftSize && j < rightSize) {
			switch (property.toLowerCase()) { // handles capitals
			case "price":
				if (leftArr[i].getPrice() <= rightArr[j].getPrice()) {
					tapers.set(k++, leftArr[i++]); // mergedlist index++ is set to the left list's index++
				} else {
					tapers.set(k++, rightArr[j++]); // mergedlist index++ is set to the right list's index++
				}
				break;
			case "weight":
				if (leftArr[i].getWeight() <= rightArr[j].getWeight()) {
					tapers.set(k++, leftArr[i++]); // rounds.set(index, element(object))
				} else {
					tapers.set(k++, rightArr[j++]);
				}
				break;
			case "colour":
				if (leftArr[i].getColour() <= rightArr[j].getColour()) {
					tapers.set(k++, leftArr[i++]);
				} else {
					tapers.set(k++, rightArr[j++]);
				}
				break;
			case "cut":
				if (leftArr[i].getCut().compareTo(rightArr[j].getCut()) <= 0) {
					tapers.set(k++, leftArr[i++]);
				} else {
					tapers.set(k++, rightArr[j++]);
				}
				break;
			case "clarity":
				if (leftArr[i].getClarity().compareTo(rightArr[j].getClarity()) <= 0) {
					tapers.set(k++, leftArr[i++]);
				} else {
					tapers.set(k++, rightArr[j++]);
				}
				break;
			case "itemcode":
				if (leftArr[i].getItemCode() <= rightArr[j].getItemCode()) {
					tapers.set(k++, leftArr[i++]);
				} else {
					tapers.set(k++, rightArr[j++]);
				}
				break;
			default:
				System.out.println("Invalid sort property: " + property);
				break;
			}
		}
		while (i < leftSize) {
			tapers.set(k++, leftArr[i++]); // add the remaining elements onto sorted array
		}
		while (j < rightSize) {
			tapers.set(k++, rightArr[j++]);
		}
	}

	public static Taper find(int itemCode) {
		Taper.sort("itemcode");
		int low = 0;
		int high = tapers.size() - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			Taper midTaper = tapers.get(mid);

			if (midTaper.getItemCode() == itemCode) {
				return midTaper;
			} else if (midTaper.getItemCode() < itemCode) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return null;
	}

	public static void totalWeight() {
		for (int i = 0; i < tapers.size(); i++) {
			if (tapers.get(i).getSold() == false) {
				Inventory.totalWeight = Inventory.totalWeight + tapers.get(i).getWeight();
			}
		}
	}

	public static void totalPrice() {
		for (int i = 0; i < tapers.size(); i++) {
			if (tapers.get(i).getSold() == false) {
				Inventory.totalPrice = Inventory.totalPrice + tapers.get(i).getPrice();
			}
		}
	}

	@Override
	public String toString() {
		String[] headers = { "Type", "Price($usd)", "Colour", "Clarity", "Cut", "Weight(ct)", "Item Code",
				"Date Bought", "Supplier", "Supplier code", "Pieces left", "Sold" };
		StringBuilder sb = new StringBuilder();
		for (String header : headers) {
			sb.append(String.format("%-30s", header));
		}
		sb.append("\n");
		sb.append(String.format("%-30s%-30.2f%-30s%-30s%-30s%-30.2f%-30d%-30s%-30s%-30s%-30s%s\n", "Tapers", getPrice(),
				getColour(), getClarity(), getCut(), getWeight(), getItemCode(), getDate(), getSupplier().getName(),
				getSupplier().getCode(), getPieces(), getSold()));
		return sb.toString();
	}

	public static void printTaper(JTextArea textArea) {
		String[] headers = { "Type", "Price($usd)", "Colour", "Clarity", "Cut", "Weight(ct)", "Item Code",
				"Date Bought", "Supplier", "Supplier code", "Pieces left" };

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

		// Print the taper objects with aligned columns
		StringBuilder lineBuilder = new StringBuilder();
		for (Taper taper : Taper.tapers) {
			if (!taper.getSold()) {
				lineBuilder.setLength(0);
				lineBuilder.append("Tapers").append("\t");
				lineBuilder.append(formatPrice(taper.getPrice())).append("\t");
				lineBuilder.append(taper.getColour()).append("\t");
				lineBuilder.append(taper.getClarity()).append("\t");
				lineBuilder.append(taper.getCut()).append("\t");
				lineBuilder.append(formatWeight(taper.getWeight())).append("\t");
				lineBuilder.append(taper.getItemCode()).append("\t");
				lineBuilder.append(taper.getDate()).append("\t");
				lineBuilder.append(taper.getSupplier().getName()).append("\t");
				lineBuilder.append(taper.getSupplier().getCode()).append("\t");
				lineBuilder.append(taper.getPieces()).append("\t");
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

	public static void clearTapers() {
		tapers.clear();
	}

}