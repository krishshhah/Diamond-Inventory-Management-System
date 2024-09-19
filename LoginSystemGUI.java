import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class LoginSystemGUI extends javax.swing.JFrame {

	
	public LoginSystemGUI() {
		initComponents();
	}
	
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jPasswordField1 = new javax.swing.JPasswordField();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("Username");

		jLabel2.setText("Password");

		jButton1.setText("Submit");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(94, 94, 94)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel1).addComponent(jLabel2))
								.addGap(67, 67, 67)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 112,
												Short.MAX_VALUE)
										.addComponent(jTextField1)))
						.addGroup(layout.createSequentialGroup().addGap(153, 153, 153).addComponent(jButton1)))
				.addContainerGap(68, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(92, 92, 92)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(28, 28, 28)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addGap(43, 43, 43).addComponent(jButton1).addContainerGap(96, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) { // submit Jbutton
		String username = jTextField1.getText();
		char[] passwordChars = jPasswordField1.getPassword(); 
		try {
			File logins = new File("logins.txt"); // reads data from text file
			boolean access = false;

			try (Scanner myReader = new Scanner(logins)) { // error here
				while (myReader.hasNextLine() && !access) {
					String username1 = myReader.nextLine(); // text file holds usernames and passwords consecutively on different lines
					char[] password1Chars = myReader.nextLine().toCharArray();

					if (username.equals(username1) && Arrays.equals(passwordChars, password1Chars)) {
						JOptionPane.showMessageDialog(this, "Login is successful!");
						access = true; // access allowed
						new HomePageGUI().setVisible(true); // homepage can be viewed and used
						FileSave.loadDataFromFile(); // data is loaded from file
						Debtor.reminder(); // reminder method accessed - pops up if any payments are due 
						this.dispose(); // closes the current Jframe 
					}
				}
			}

			if (!access) {
				JOptionPane.showMessageDialog(this, "Login failed, try again!"); // pop up
			}

		} catch (IOException e) {
			System.out.println("An error has occurred"); // if text file not read from correctly
			e.printStackTrace(); 
		} finally {
			Arrays.fill(passwordChars, ' '); // clear the password array from memory
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LoginSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LoginSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LoginSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginSystemGUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginSystemGUI().setVisible(true);
			}
		});
	}

	// Variables declaration 
	private javax.swing.JButton jButton1; // submit button
	private javax.swing.JLabel jLabel1; // username
	private javax.swing.JLabel jLabel2; // password
	private javax.swing.JPasswordField jPasswordField1; // makes it protected
	private javax.swing.JTextField jTextField1;
}
