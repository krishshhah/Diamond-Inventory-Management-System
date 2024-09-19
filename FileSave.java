import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class FileSave {

	public static void saveDataToFile() { // saves to data.ser
		try { // to handle exceptions
			FileOutputStream fileOut = new FileOutputStream("data.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Supplier.getSuppliers());
			out.writeObject(Debtor.getDebtors());
			out.writeObject(Debtor.getClients()); // dynamic data structures
			out.writeObject(Round.getRounds());
			out.writeObject(Taper.getTapers());
			out.writeObject(Baguette.getBaguettes());
			out.writeObject(Inventory.getLastMonthQP());
			out.writeObject(Inventory.getLastMonthSP()); 
			out.writeObject(Inventory.getLastMonthQW());
			out.writeObject(Inventory.getLastMonthSW());
			out.writeObject(Inventory.getPriceData());
			out.writeObject(Inventory.getWeightData());
			out.writeInt(Debtor.getIncrement()); // debtor number
			out.writeDouble(Inventory.getTotalPrice());
			out.writeDouble(Inventory.getTotalWeight());
			out.writeDouble(Inventory.getTotalProfit());
			out.writeDouble(Inventory.getPriceChange());
			out.writeDouble(Inventory.getWeightChange());
			out.writeBoolean(Inventory.isTaskExecuted());
			out.writeObject(Inventory.getLastExecutionDate());
			out.writeBoolean(Inventory.isPriceExecuted());
			out.writeObject(Inventory.getLastPriceExecutionDate());
			out.writeBoolean(Inventory.isWeightExecuted()); // booleans for calculations
			out.writeObject(Inventory.getLastWeightExecutionDate());
			out.close();
			fileOut.close();
			System.out.println("Data saved to file.");
		} catch (IOException e) { 
			System.out.println("Error saving data to file: " + e.getMessage());
		}
	}

	public static void loadDataFromFile() { // loads from data.ser
		try { // to handle exceptions 
			FileInputStream fileIn = new FileInputStream("data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Supplier.setSuppliers((Map<String, Supplier>) in.readObject());
			Debtor.setDebtors((ArrayList<Debtor>) in.readObject());
			Debtor.setClients((Map<String, Debtor>) in.readObject());
			Round.setRounds((ArrayList<Round>) in.readObject());
			Taper.setTapers((ArrayList<Taper>) in.readObject());
			Baguette.setBaguettes((ArrayList<Baguette>) in.readObject());
			Inventory.setLastMonthQP((Queue<Double>) in.readObject());
			Inventory.setLastMonthSP((Stack<Double>) in.readObject());
			Inventory.setLastMonthQW((Queue<Double>) in.readObject());
			Inventory.setLastMonthSW((Stack<Double>) in.readObject());
			Inventory.setPriceData((TreeMap<LocalDate, Double>) in.readObject());
			Inventory.setWeightData((TreeMap<LocalDate, Double>) in.readObject());
			Debtor.setIncrement(in.readInt());
			Inventory.setTotalPrice(in.readDouble());
			Inventory.setTotalWeight(in.readDouble());
			Inventory.setTotalProfit(in.readDouble());
			Inventory.setPriceChange(in.readDouble());
			Inventory.setWeightChange(in.readDouble());
			Inventory.setTaskExecuted(in.readBoolean());
			Inventory.setLastExecutionDate((LocalDate) in.readObject());
			Inventory.setPriceExecuted(in.readBoolean());
			Inventory.setLastPriceExecutionDate((LocalDate) in.readObject());
			Inventory.setWeightExecuted(in.readBoolean());
			Inventory.setLastWeightExecutionDate((LocalDate) in.readObject());
			in.close();
			fileIn.close();
			System.out.println("Data loaded from file.");
		} catch (IOException e) {
			System.out.println("Error loading data from file: " + e.getMessage());
		} catch (ClassNotFoundException e) { // different exceptions
			System.out.println("Error loading data from file: " + e.getMessage());
		}
	}

	public static void clearData() {
		Supplier.clearSuppliers();
		Debtor.clearDebtors();
		Debtor.clearClients();
		Round.clearRounds();
		Taper.clearTapers();
		Baguette.clearBaguettes();
		Inventory.clearLastMonthQP();
		Inventory.clearLastMonthSP();
		Inventory.clearLastMonthQW();
		Inventory.clearLastMonthSW();
	}

}
