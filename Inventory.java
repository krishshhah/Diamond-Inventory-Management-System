import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class Inventory implements Serializable {
	private static Queue<Double> lastMonthQP = new LinkedList<Double>();
	private static Queue<Double> lastMonthQW = new LinkedList<Double>();
	private static Stack<Double> lastMonthSP = new Stack<Double>();
	private static Stack<Double> lastMonthSW = new Stack<Double>();
	private static TreeMap<LocalDate, Double> priceData = new TreeMap<>();
	private static TreeMap<LocalDate, Double> weightData = new TreeMap<>();

	private final static int qsize = 30;
	protected static double totalPrice = 0;
	protected static double totalWeight = 0;
	protected static double totalProfit = 0;
	protected static double priceChange = 0;
	protected static double weightChange = 0;
	protected static LocalTime currentTime = LocalTime.now();
	protected static final LocalTime startTime = LocalTime.of(7, 0); // 7am
	protected static final LocalTime endTime = LocalTime.of(23, 0); // 7pm

	private static boolean isTaskExecuted = false; // for calculations
	private static LocalDate lastExecutionDate = null; // for calculations
	private static boolean isPriceExecuted = false; // for plotting price data
	private static LocalDate lastPriceExecutionDate = null; // for plotting price data
	private static boolean isWeightExecuted = false; // for plotting price data
	private static LocalDate lastWeightExecutionDate = null; // for plotting price data
	private static boolean isInitialised = false;

	public static Queue<Double> getLastMonthQP() {
		return lastMonthQP;
	}

	public static void setLastMonthQP(Queue<Double> lastMonthQP) {
		Inventory.lastMonthQP = lastMonthQP;
	}

	public static Queue<Double> getLastMonthQW() {
		return lastMonthQW;
	}

	public static void setLastMonthQW(Queue<Double> lastMonthQW) {
		Inventory.lastMonthQW = lastMonthQW;
	}

	public static int getQsize() {
		return qsize;
	}

	public static Stack<Double> getLastMonthSP() {
		return lastMonthSP;
	}

	public static void setLastMonthSP(Stack<Double> lastMonthSP) {
		Inventory.lastMonthSP = lastMonthSP;
	}

	public static Stack<Double> getLastMonthSW() {
		return lastMonthSW;
	}

	public static void setLastMonthSW(Stack<Double> lastMonthSW) {
		Inventory.lastMonthSW = lastMonthSW;
	}

	public static double getTotalProfit() {
		return totalProfit;
	}

	public static void setTotalProfit(double totalProfit) {
		Inventory.totalProfit = totalProfit;
	}

	public static double getTotalPrice() {
		return totalPrice;
	}

	public static void setTotalPrice(double totalPrice) {
		Inventory.totalPrice = totalPrice;
	}

	public static double getTotalWeight() {
		return totalWeight;
	}

	public static void setTotalWeight(double totalWeight) {
		Inventory.totalWeight = totalWeight;
	}

	public static double getPriceChange() {
		return priceChange;
	}

	public static void setPriceChange(double priceChange) {
		Inventory.priceChange = priceChange;
	}

	public static double getWeightChange() {
		return weightChange;
	}

	public static void setWeightChange(double weightChange) {
		Inventory.weightChange = weightChange;
	}

	public static boolean isTaskExecuted() {
		return isTaskExecuted;
	}

	public static void setTaskExecuted(boolean isTaskExecuted) {
		Inventory.isTaskExecuted = isTaskExecuted;
	}

	public static LocalDate getLastExecutionDate() {
		return lastExecutionDate;
	}

	public static void setLastExecutionDate(LocalDate lastExecutionDate) {
		Inventory.lastExecutionDate = lastExecutionDate;
	}

	public static boolean isPriceExecuted() {
		return isPriceExecuted;
	}

	public static void setPriceExecuted(boolean isPriceExecuted) {
		Inventory.isPriceExecuted = isPriceExecuted;
	}

	public static LocalDate getLastPriceExecutionDate() {
		return lastPriceExecutionDate;
	}

	public static void setLastPriceExecutionDate(LocalDate lastPriceExecutionDate) {
		Inventory.lastPriceExecutionDate = lastPriceExecutionDate;
	}

	public static boolean isWeightExecuted() {
		return isWeightExecuted;
	}

	public static void setWeightExecuted(boolean isWeightExecuted) {
		Inventory.isWeightExecuted = isWeightExecuted;
	}

	public static LocalDate getLastWeightExecutionDate() {
		return lastWeightExecutionDate;
	}

	public static void setLastWeightExecutionDate(LocalDate lastWeightExecutionDate) {
		Inventory.lastWeightExecutionDate = lastWeightExecutionDate;
	}

	public static boolean isInitialised() {
		return isInitialised;
	}

	public static void setInitialised(boolean isInitialised) {
		Inventory.isInitialised = isInitialised;
	}

	public static TreeMap<LocalDate, Double> getPriceData() {
		return priceData;
	}

	public static void setPriceData(TreeMap<LocalDate, Double> priceData) {
		Inventory.priceData = priceData;
	}

	public static TreeMap<LocalDate, Double> getWeightData() {
		return weightData;
	}

	public static void setWeightData(TreeMap<LocalDate, Double> weightData) {
		Inventory.weightData = weightData;
	}

	static { // allows program to initially create stacks and queues with values
		if (!isInitialised) {
			while (Inventory.getLastMonthQP().size() < getQsize()) {
				for (double i = 10000; i < 20000; i = i + 20) {
					Inventory.getLastMonthQP().add(i);
					Inventory.getLastMonthSP().push(i);
					if (Inventory.getLastMonthQP().size() == getQsize())
						break;
				}
			}
			while (Inventory.getLastMonthQW().size() < getQsize()) {
				Inventory.getLastMonthQW().add(8.0);
				Inventory.getLastMonthSW().push(8.0);
				if (Inventory.getLastMonthQW().size() == getQsize())
					break;
			}
			isInitialised = true;
		}

	}

	// Helper method to build the format string with variable width specifiers based
	// on the headers
	public static String buildFormatString(String[] headers) {
		StringBuilder formatBuilder = new StringBuilder();
		for (String header : headers) {
			formatBuilder.append("%s\t");
		}
		formatBuilder.append("\n");
		return formatBuilder.toString();
	}

	public static boolean checkInputs(JTextField[] textFields) { // passes a string of textfields
		boolean allFieldsFilled = true;
		for (JTextField textField : textFields) { // checks all textFields in GUI
			if (textField.getText().isEmpty()) { // if any textfield is empty
				allFieldsFilled = false; // variable set to false
				break; // loops exited
			}
		}
		if (!allFieldsFilled) {
			JOptionPane.showMessageDialog(null, "Please fill in all the fields."); // displays a pop-up requesting all
																					// fields to be filled
		}
		return allFieldsFilled;
	}

	public static double round(double number) { // rounds a double value to 2dp to display
		DecimalFormat df = new DecimalFormat("#.##");
		double roundedNum = Double.parseDouble(df.format(number));
		return roundedNum; 
	}

	public static LocalDate dateParse(String date) { // converts a date string into a Local Date object by parsing
		try {
			LocalDate Date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return Date;
		} catch (DateTimeParseException e) {
			return null; // error caught
		}
	}

	public static void displayAll(JTextArea textArea) { // prints all stock of all classes
		Round.printRound(textArea);
		Taper.printTaper(textArea);
		Baguette.printBaguette(textArea);
	}

	public static void calculate() {
		// Check if task has already been executed for the day
		LocalDate currentDate = LocalDate.now();
		if (lastExecutionDate != null && currentDate.equals(lastExecutionDate) && isTaskExecuted) {
			return; // Exit the method if already executed for the day
		}

		// Reset variables
		totalPrice = 0;
		totalWeight = 0;

		// Run the necessary calculations
		calcPrice();
		calcWeight();
		updateQP();
		updateQW();
		updateSP();
		updateSW();
		changedPrice();
		changedWeight();

		// Update flags and date
		lastExecutionDate = currentDate;
		isTaskExecuted = true;
	}

	public static void resetProfit() {
		totalProfit = 0;
	} // resets profit counter

	// Other methods used in calculate()

	public static double calcPrice() {
		Round.totalPrice(); // total price calculated by all classes and added to the static variable
		Taper.totalPrice();
		Baguette.totalPrice();
		return round(Inventory.totalPrice);

	}

	public static double calcWeight() {
		Round.totalWeight(); // total weight calculated by all classes and added to the static variable
		Taper.totalWeight();
		Baguette.totalWeight();
		return round(Inventory.totalWeight);
	}

	public static void updateQP() {
		lastMonthQP.add(Inventory.totalPrice);
		while (lastMonthQP.size() > getQsize()) { // keeps size constant to 30 days
			lastMonthQP.remove(); // dequeues/removes object from front of queue until it is the right size
		}
	}

	public static void updateQW() {
		lastMonthQW.add(Inventory.totalWeight);
		while (lastMonthQW.size() > getQsize()) {
			lastMonthQW.remove();
		}
	}

	public static void updateSP() { // assuming totalPrice already calculated.
		if (!lastMonthSP.isEmpty()) { // if stack is not empty
			lastMonthSP.push(Inventory.totalPrice); // current days total price added
		}
	}

	public static void updateSW() { // assuming totalWeight already calculated.
		if (!lastMonthSW.isEmpty()) {
			lastMonthSW.push(Inventory.totalWeight);
		}
	}

	public static double changedPrice() {
		if (lastMonthSP.size() >= 2) {
			double x = lastMonthSP.pop(); // pops off the last element in stack
			double y = lastMonthSP.peek(); // looks at the last element in stack
			lastMonthSP.push(x); // push back the removed element to keep the stack intact
			Inventory.priceChange = round(x - y);
			return Inventory.priceChange; // finds difference
		} else {
			return 0.0;
		}
	}

	public static double changedWeight() {
		if (lastMonthSW.size() >= 2) {
			double x = lastMonthSW.pop(); // pops off the last element in stack
			double y = lastMonthSW.peek(); // looks at the last element in stack
			lastMonthSW.push(x); // push back the removed element to keep the stack intact
			Inventory.weightChange = round(x - y);
			return Inventory.weightChange; // finds difference
		} else {
			return 0.0;
		}
	}


	public static void createPriceData(Queue<Double> queue, String type) {
		// Check if task has already been executed for the day
		LocalDate currentDate = LocalDate.now();
		if (lastPriceExecutionDate != null && currentDate.equals(lastPriceExecutionDate) && isPriceExecuted) {
			return; // Exit the method if already executed for the day
		}
		priceData.clear(); // removes all data from treemap, to prevent doubling values. Prepared to read from queue. 
		double difference = 0;
		double total = 0;
		double count = 0;
		for (int i = 29; i >= 0; i--) {
			Double element = queue.peek();
			if (element != null) {
				priceData.put(currentDate.plusDays(-i), element); // updates data hashmap with last 30 days
			}
			queue.offer(queue.poll()); // Move the first element to the end of the queue
		}
		for (int i = 29; i > 0; i--) { // loops back through the hashmap 
			difference = priceData.get(currentDate.plusDays(-(i - 1))) - priceData.get(currentDate.plusDays(-i)); 
			// finds the difference between 2 adjacent price values 
			total = total + difference; // adding the difference to a variable 
			count++; // incrementing the count
		}
		double average = total / count; // finds the average difference between each day
		priceData.put(currentDate.plusDays(1), (priceData.get(currentDate) + (average))); 
		// average difference is added to the current date's price value to predict the next day's price
		lastPriceExecutionDate = currentDate;
		isPriceExecuted = true;
	}

	public static void createWeightData(Queue<Double> queue, String type) {
		// Check if task has already been executed for the day
		LocalDate currentDate = LocalDate.now();
		if (lastWeightExecutionDate != null && currentDate.equals(lastWeightExecutionDate) && isWeightExecuted) {
			return; // Exit the method if already executed for the day
		}
		weightData.clear(); // removes all data from treemap, to prevent doubling values. Prepared to read from queue. 
		double difference = 0;
		double total = 0;
		double count = 0;
		for (int i = 29; i >= 0; i--) {
			Double element = queue.peek(); // dequeues first element in queue
			if (element != null) {
				weightData.put(currentDate.plusDays(-i), element); // updates data hashmap with last 30 days
			}
			queue.offer(queue.poll()); // Move the first element to the end of the queue
		}
		for (int i = 29; i > 0; i--) { // loops back through the hashmap 
			difference = weightData.get(currentDate.plusDays(-(i - 1))) - weightData.get(currentDate.plusDays(-i)); 
			// finds the difference between 2 adjacent price values 
			total = total + difference; // adding the difference to a variable 
			count++; // incrementing the count
		}
		double average = total / count; // finds the average difference between each day
		weightData.put(currentDate.plusDays(1), (weightData.get(currentDate) + (average))); 
		// average difference is added to the current date's price value to predict the next day's price
		lastWeightExecutionDate = currentDate; 
		isWeightExecuted = true;
	}

	public static void plotData(TreeMap<LocalDate, Double> map, String type) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		map.forEach((date, price) -> dataset.addValue(price, "Price", date)); // iterates over key-value pairs in
																				// treemap.
		// lambda function
		// (data value(bar height), bar label, x-axis label)

		JFreeChart barChart = ChartFactory.createBarChart("Inventory " + type + " over the past month", "Date", type,
				dataset); // dataset (Title, 'x-axis', 'y-axis', data) used

		CategoryPlot plot = barChart.getCategoryPlot();
		CategoryAxis xAxis = plot.getDomainAxis();
		xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // sets position of x-axis labels to be at 45
																		// angle to the x-axis.

		JFrame frame = new JFrame("Stats"); // creates a new jFrame to display
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new ChartPanel(barChart));
		frame.pack();
		frame.setVisible(true);
	}

	public static void clearLastMonthQP() {
		lastMonthQP.clear();
	}

	public static void clearLastMonthSP() {
		lastMonthSP.clear();
	}

	public static void clearLastMonthQW() {
		lastMonthQW.clear();
	}

	public static void clearLastMonthSW() {
		lastMonthSW.clear();
	}

}