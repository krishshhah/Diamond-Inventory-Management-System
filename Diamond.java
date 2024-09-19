import java.io.Serializable;
import java.time.LocalDate;

public abstract class Diamond implements Serializable { 
	private double price;
	private char colour;
	private String clarity;
	private String cut;
	private double weight;
	private int itemCode;
	private boolean sold;
	private Supplier supplier; // Aggregation: links to a supplier object
	private LocalDate date;

	public Diamond(double price, char colour, String clarity, String cut, double weight, int itemCode, boolean sold,
			Supplier supplier, LocalDate date) {
		super();
		this.price = price;
		this.colour = colour;
		this.clarity = clarity;
		this.cut = cut;
		this.weight = weight;
		this.itemCode = itemCode;
		this.sold = false;
		this.supplier = supplier;
		this.date = date;
	}

	// Encapsulation: setters and getters.

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public char getColour() {
		return colour;
	}

	public void setColour(char colour) {
		this.colour = colour;
	}

	public String getClarity() {
		return clarity;
	}

	public void setClarity(String clarity) {
		this.clarity = clarity;
	}

	public String getCut() {
		return cut;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public boolean getSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
