import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextArea;

public class Supplier implements Serializable { // allows data saving
	private String name;
	private String compName;
	private String phone;
	private String fax;
	private String code;
	protected static boolean addDebtor = true; // for debtor subclass use
	private static Map<String, Supplier> suppliers = new HashMap<String, Supplier>(); 

	public Supplier(String name, String compName, String phone, String fax, String code) {
		super();
		this.name = name;
		this.compName = compName;
		this.phone = phone;
		this.fax = fax;
		this.code = code;
		suppliers.put(getCode(), this); // adds supplier object to hashmap everytime a supplier is created
	}

	public Supplier(String name, String compName, String phone, String fax, String code, boolean addDebtor) {
		super(); // super constructor designed for debtor class, so that the debtor object is not
					// added to the supplier hashmap when created
		this.name = name;
		this.compName = compName;
		this.phone = phone;
		this.fax = fax;
		this.code = code;
		this.addDebtor = addDebtor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static Map<String, Supplier> getSuppliers() {
		return suppliers;
	}

	public static void setSuppliers(Map<String, Supplier> suppliers) {
		Supplier.suppliers = suppliers;
	}

	public static String add(String name, String compName, String phone, String fax, String code) {
		// invoked to create a supplier objects, which is automatically added to hashmap
		if (suppliers.containsKey(code)) {
			return "A supplier with item code " + code + " already exists."; // handles error to make sure Supplier of
																				// same item code not created
		} else {
			// Creates a new supplier object and adds to the hashMap
			// no error handling: all parameters are Strings
			Supplier supplier = new Supplier(name, compName, phone, fax, code); // supplier created
			return "Supplier created successfully!";
		}
	}

	public static String remove(String code) {
		if (suppliers.containsKey(code)) {
			suppliers.remove(code);
			return "Supplier with code " + code + " removed successfully."; 
		}
		return "A supplier with item code " + code + " does not exist."; // handles error if supplier not in hashMap
	}

	@Override
	public String toString() {
		String[] headers = { "Supplier Name", "Company Name", "Phone", "Fax", "Code" };
		StringBuilder sb = new StringBuilder();
		for (String header : headers) {
			sb.append(String.format("%-30s", header));
		} 
		sb.append("\n");
		sb.append(String.format("%-30s%-30s%-30s%-30s%-30s\n", getName(), getCompName(), getPhone(), getFax(),
				getCode()));
		return sb.toString();
	}

	public static void printSupplier(JTextArea textArea) {
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
		for (Supplier supplier : Supplier.suppliers.values()) {
			lineBuilder.setLength(0);
			lineBuilder.append(supplier.getName()).append("\t");
			lineBuilder.append(supplier.getCompName()).append("\t");
			lineBuilder.append(supplier.getPhone()).append("\t");
			lineBuilder.append(supplier.getFax()).append("\t");
			lineBuilder.append(supplier.getCode()).append("\t");
			lineBuilder.append("\n");
			textArea.append(lineBuilder.toString());
		}
		textArea.append("\n");
	}

	public static void clearSuppliers() {
		suppliers.clear();
	}
}
