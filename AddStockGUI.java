import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddStockGUI extends javax.swing.JFrame {

	/**
	 * Creates new form AddStockGUI
	 */
	public AddStockGUI() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jComboBox1 = new javax.swing.JComboBox<>();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox<>();
		jLabel4 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox<>();
		jLabel5 = new javax.swing.JLabel();
		jComboBox4 = new javax.swing.JComboBox<>();
		jLabel6 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		jTextField5 = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		jTextField6 = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jComboBox5 = new javax.swing.JComboBox<>();
		jLabel12 = new javax.swing.JLabel();
		jTextField7 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("Choose Type");

		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Round", "Taper", "Baguette" }));
		jComboBox1.setSelectedIndex(1);
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});

		jLabel2.setText("Enter Price");

		jLabel3.setText("Choose Colour");

		jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));

		jLabel4.setText("Choose Clarity");

		jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "FL", "IF", "VVS1", "VVS2", "VS1", "VS2", "SI1", "SI2", "I1", "I2", "I3"}));

		jLabel5.setText("Choose Cut");

		jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(
				new String[] { "Excellent", "Very Good", "Good", "Fair", "Poor"  }));

		jLabel6.setText("Enter Weight");

		jLabel7.setText("Enter Item Code");

		jLabel8.setText("Enter Supplier Code");

		jLabel9.setText("Enter Date");

		jTextField5.setText("dd/mm/yyyy");

		jLabel10.setText("Enter Number");

		jLabel11.setText("Facets");

		jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single Cut", "Full Cut" }));

		jLabel12.setText("No. of Pieces");

		jButton1.setText("Add");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton3.setText("Reset");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jTextArea1.setEditable(false);
		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(90, 90, 90)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(jButton1).addGap(83, 83, 83)
										.addComponent(jButton3))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel3)
												.addComponent(jLabel4).addComponent(jLabel5).addComponent(jLabel6)
												.addComponent(jLabel7).addComponent(jLabel8).addComponent(jLabel9)
												.addComponent(jLabel10).addComponent(jLabel11).addComponent(jLabel12))
										.addGap(36, 36, 36)
										.addGroup(layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jTextField1)
												.addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jComboBox3, 0, 100, Short.MAX_VALUE)
												.addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jTextField2).addComponent(jTextField5)
												.addComponent(jTextField4).addComponent(jTextField3)
												.addComponent(jTextField6)
												.addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jTextField7))))
						.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(60, 60, 60)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
						.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel2))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3)
						.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4)
						.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5)
						.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel6)
						.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7)
						.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jLabel8)
						.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel9)
						.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel10)
						.addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel11)
						.addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(15, 15, 15)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel12)
						.addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jButton3)
						.addComponent(jButton1))
				.addGap(18, 18, 18)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { // Add jButton 
		jTextArea1.setText("");
		String selectedType = jComboBox1.getSelectedItem().toString(); // Object --> String
		if (selectedType.equals("Round")) {
			boolean isSingle = false;
			if (jComboBox5.getSelectedItem().toString().equals("Single Cut")) {
				isSingle = true;
			}
			JTextField[] textFields = { jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6 };
			boolean flag = Inventory.checkInputs(textFields);
			if (flag == false) {
				jTextArea1.append("Please enter all fields.");
				return;
			}
			// Check that jTextField1, jTextField2, and jTextField3 contain valid numeric values
			try {
				int itemCode = Integer.parseInt(jTextField3.getText());
				double weight = Double.parseDouble(jTextField2.getText());
				double price = Double.parseDouble(jTextField1.getText());
				Round.add(price, jComboBox2.getSelectedItem().toString().charAt(0),
						jComboBox3.getSelectedItem().toString(), jComboBox4.getSelectedItem().toString(),
						jTextField6.getText(), weight, itemCode, jTextField4.getText(), jTextField5.getText(), isSingle,
						jTextArea1);
			} catch (NumberFormatException e) {
				jTextArea1.append(
						"Please enter valid numeric values for Price and Weight, and a valid integer value for Item Code.");
				return;
			}
		} else if (selectedType.equals("Taper")) {
			JTextField[] textFields = { jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField7 };
			boolean flag = Inventory.checkInputs(textFields);
			if (flag == false) {
				jTextArea1.append("Please enter all fields.");
				return;
			}
			// Check that jTextField1, jTextField2, and jTextField3 contain valid numeric values
			try {
				int itemCode = Integer.parseInt(jTextField3.getText());
				double weight = Double.parseDouble(jTextField2.getText());
				double price = Double.parseDouble(jTextField1.getText());
				Taper.add(price, jComboBox2.getSelectedItem().toString().charAt(0),
						jComboBox3.getSelectedItem().toString(), jComboBox4.getSelectedItem().toString(), weight,
						itemCode, jTextField4.getText(), jTextField5.getText(), Integer.parseInt(jTextField7.getText()),
						jTextArea1);
			} catch (NumberFormatException e) {
				jTextArea1.append(
						"Please enter valid numeric values for Price and Weight, and a valid integer value for Item Code.");
				return;
			}
		} else if (selectedType.equals("Baguette")) {
			JTextField[] textFields = { jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField7 };
			boolean flag = Inventory.checkInputs(textFields);
			if (flag == false) {
				jTextArea1.append("Please enter all fields.");
				return;
			}
			// Check that jTextField1, jTextField2, and jTextField3 contain valid numeric values
			try {
				int itemCode = Integer.parseInt(jTextField3.getText());
				double weight = Double.parseDouble(jTextField2.getText());
				double price = Double.parseDouble(jTextField1.getText());
				Baguette.add(price, jComboBox2.getSelectedItem().toString().charAt(0),
						jComboBox3.getSelectedItem().toString(), jComboBox4.getSelectedItem().toString(), weight,
						itemCode, jTextField4.getText(), jTextField5.getText(), Integer.parseInt(jTextField7.getText()),
						jTextArea1);
			} catch (NumberFormatException e) {
				jTextArea1.append(
						"Please enter valid numeric values for Price and Weight, and a valid integer value for Item Code.");
				return;
			}
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) { // Clear jButton
		jComboBox1.setSelectedIndex(0);
		jComboBox2.setSelectedIndex(0);
		jComboBox3.setSelectedIndex(0);
		jComboBox4.setSelectedIndex(0);
		jTextField1.setText("");
		jTextField2.setText("");
		jTextField3.setText(""); // clears all text fields to have no text
		jTextField4.setText("");
		jTextField5.setText("dd/mm/yyyy");
		jTextField6.setText("");
		jTextField7.setText("");
	}

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) { // Type jComboBox selector
		String selectedType = jComboBox1.getSelectedItem().toString(); // Object --> String for comparison
		switch (selectedType) { // switch statement used, more efficient
		case "Round":
			jTextField6.setVisible(true);
			jComboBox5.setVisible(true); // single cut/full cut jComboBox
			jTextField7.setVisible(false); // pieces jTextfield
			jLabel10.setVisible(true);
			jLabel11.setVisible(true);
			jLabel12.setVisible(false); // pieces jLabel
			boolean isSingle = false;
			if (jComboBox5.getSelectedItem().toString().equals("Single Cut")) {
				isSingle = true;
			}
			break;
		case "Taper":
			jTextField6.setVisible(false);
			jComboBox5.setVisible(false);
			jTextField7.setVisible(true);
			jLabel10.setVisible(false);
			jLabel11.setVisible(false);
			jLabel12.setVisible(true);
			break;
		case "Baguette":
			jTextField6.setVisible(false);
			jComboBox5.setVisible(false);
			jTextField7.setVisible(true);
			jLabel10.setVisible(false);
			jLabel11.setVisible(false);
			jLabel12.setVisible(true);
			break;
		default:
			break;
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(AddStockGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AddStockGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AddStockGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AddStockGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new AddStockGUI().setVisible(true);
			}
		});
	}

	// Variables declaration -
	private javax.swing.JButton jButton1; // add button 
	private javax.swing.JButton jButton3; // clear button
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JComboBox<String> jComboBox2;
	private javax.swing.JComboBox<String> jComboBox3;
	private javax.swing.JComboBox<String> jComboBox4;
	private javax.swing.JComboBox<String> jComboBox5;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	private javax.swing.JTextField jTextField7;
}
