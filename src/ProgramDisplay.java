import java.awt.EventQueue;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class ProgramDisplay {

	protected static final nameException comma = null;
	private JFrame frame;
	private JLayeredPane layeredPane;
	private JTextField salary2;
	private JTextField addPay2;
	private JTextField tax2;
	private JTextField salary1;
	private JTextField time1;
	private JTextField addPay1;
	private JTextField tax1;
	private JTextField name2;
	private JTextField id2;
	private JTextField phone2;
	private JTextField name1;
	private JTextField id1;
	private JTextField phone1;
	private PayRoll p = new PayRoll();
	private String name;
	private int empID;
	private double salary, time, tax, addPay;
	private String telNo;
	private JTable table_2;
	private JTextField empIDs;
	private JTextField names;
	private JTextField phones;
	private JTextField pays;
	private JTextField addPays;
	private JTextField salarys;
	private JTextField times;
	private JTextField taxs;
	private JLabel empTypes;
	private JTextField ID;
	private JTextField fileName;
	private JLabel empIDss;


	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramDisplay window = new ProgramDisplay();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	public boolean checkComma(String check)
	{
		String s  = check;
		char a[]  = s.toUpperCase().toCharArray();
		int check1 = 0;
		for(int x= 0; x< a.length; x++)
		{
			if((int)a[x] >= 65 && (int)a[x] <=90)
			{
				check1=0;
			}
			else
			{
				if(a[x] == ' '||a[x] == '/' || a[x] =='.')
				{
					check1 =0;
				}
				else
				{
					check1 = 1;
					break;
				}
			}		
		}

			
		if(check1 !=0)
		{
			return false;
		}
		else
			return true;
	}
	
	public boolean checkTelNo(String s1)
	{
		if(s1.length() != 10 && s1.length() !=11)
		{
			return false;
		}
		else
		{
			char[]ck = s1.toCharArray();
			for(int x=0; x< s1.length();x++)
			{
				if((int)ck[x]<48 || (int)ck[x] > 57 )
				{
					return false;
				}
				
			}
		}
		return true;
	}

	public void updateList() 
	{
		Employee[] emp = p.getList();
		if(emp.length==0)
		{
			JOptionPane.showMessageDialog(null, "No data found");
		}
		Object[] data = new Object[5];
		DefaultTableModel tblmodel = (DefaultTableModel)table_2.getModel();
		tblmodel.setRowCount(0);
		
		for(int x =0; x<emp.length; x++)
		{
			data[0] = emp[x].getEmpID();
			data[1] = emp[x].getName();
			data[2]	= emp[x].getTelNo();
			data[3] = emp[x].computeGrossPay();
			data[4] = emp[x].getType();
			tblmodel.addRow(data);
		}
	}

	/**
	 * Create the application.
	 */
	public ProgramDisplay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		
		frame.setBounds(100, 100, 1000, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(145, 10, 812, 539);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel_mainPanel = new JPanel();
		layeredPane.add(panel_mainPanel, "name_1261352461085200");
		panel_mainPanel.setLayout(null);
		Image backgroundImg = new ImageIcon(this.getClass().getResource("/payroll.png")).getImage();
		
		JLabel lblNewLabel_backgroundImg = new JLabel("");
		lblNewLabel_backgroundImg.setIcon(new ImageIcon(backgroundImg));
		lblNewLabel_backgroundImg.setBounds(168, 10, 596, 499);
		panel_mainPanel.add(lblNewLabel_backgroundImg);
		
		////////////////////////////////////////
		JPanel panel_recordList = new JPanel();
		layeredPane.add(panel_recordList, "name_767437683309400");
		panel_recordList.setLayout(null);
		
		

			
	

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 36, 812, 412);
		panel_recordList.add(scrollPane_2);
		

		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "Employee Name", "Tel No.", "Total pay", "Employee Type"
			}
		));
		table_2.getColumnModel().getColumn(0).setPreferredWidth(123);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(128);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(114);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(119);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(220);


		scrollPane_2.setViewportView(table_2);
		
		JButton btnNewButton_1 = new JButton("Load List");
		btnNewButton_1.setToolTipText("Load employee list from file");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        DefaultTableModel tblmodel = (DefaultTableModel)table_2.getModel();
		        tblmodel.setRowCount(0);
		        updateList();
			}
		});
		btnNewButton_1.setBounds(217, 459, 280, 50);
		panel_recordList.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete Record");
		btnNewButton_2.setToolTipText("Delete selected record");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tblmodel = (DefaultTableModel)table_2.getModel();
				if(table_2.getSelectedRowCount() == 1)
				{
					int deleteEmp =table_2.getSelectedRow();					
					p.delEmp(deleteEmp);
					tblmodel.removeRow(table_2.getSelectedRow());
				}
				else
					if(table_2.getRowCount()==0)
					{
						JOptionPane.showMessageDialog(null, "No data selected to be deleted");
					}
			}
		});
		btnNewButton_2.setBounds(547, 459, 200, 50);
		panel_recordList.add(btnNewButton_2);
		
		JLabel lblNewLabel_24 = new JLabel("EMPLOYEE LIST");
		lblNewLabel_24.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_24.setBounds(10, 0, 225, 37);
		panel_recordList.add(lblNewLabel_24);
		////////////////////////////////////////////////
		JPanel panel_searchRecord = new JPanel();
		layeredPane.add(panel_searchRecord, "name_767450644712400");
		panel_searchRecord.setLayout(null);
		
		empIDs = new JTextField();
		empIDs.setToolTipText("Enter employee ID");
		empIDs.setBounds(103, 77, 200, 50);
		panel_searchRecord.add(empIDs);
		empIDs.setColumns(10);
		
		JLabel searchLabel = new JLabel("Search ID:");
		searchLabel.setBounds(10, 77, 200, 50);
		panel_searchRecord.add(searchLabel);
		
		JLabel label = new JLabel("Name: ");
		label.setBounds(10, 210, 200, 50);
		panel_searchRecord.add(label);
		
		JLabel lblNewLabel_10 = new JLabel("Phone No: ");
		lblNewLabel_10.setBounds(10, 271, 200, 50);
		panel_searchRecord.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("GrossPay: ");
		lblNewLabel_11.setBounds(10, 332, 200, 50);
		panel_searchRecord.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Additional Pay: ");
		lblNewLabel_12.setBounds(10, 393, 200, 50);
		panel_searchRecord.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Salary Per hour: ");
		lblNewLabel_13.setBounds(393, 138, 200, 50);
		panel_searchRecord.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Work Hour: ");
		lblNewLabel_14.setBounds(393, 213, 200, 50);
		panel_searchRecord.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Tax: ");
		lblNewLabel_15.setBounds(393, 284, 200, 50);
		panel_searchRecord.add(lblNewLabel_15);
		
		names = new JTextField();
		names.setToolTipText("Display employee name");
		names.setBounds(103, 210, 200, 50);
		panel_searchRecord.add(names);
		names.setColumns(10);
		
		phones = new JTextField();
		phones.setToolTipText("Display employee phone number");
		phones.setBounds(103, 271, 200, 50);
		panel_searchRecord.add(phones);
		phones.setColumns(10);
		
		pays = new JTextField();
		pays.setToolTipText("Gross pay: salary before any deductions and taxes taken out ");
		pays.setBounds(103, 332, 200, 50);
		panel_searchRecord.add(pays);
		pays.setColumns(10);
		
		addPays = new JTextField();
		addPays.setToolTipText("Addtional pay: any bonus or supplemental pay");
		addPays.setBounds(103, 393, 200, 50);
		panel_searchRecord.add(addPays);
		addPays.setColumns(10);
		
		salarys = new JTextField();
		salarys.setToolTipText("Display salary per hour");
		salarys.setBounds(493, 138, 200, 50);
		panel_searchRecord.add(salarys);
		salarys.setColumns(10);
		
		times = new JTextField();
		times.setToolTipText("Display total worked hours of an employee");
		times.setBounds(493, 213, 200, 50);
		panel_searchRecord.add(times);
		times.setColumns(10);
		
		taxs = new JTextField();
		taxs.setToolTipText("Display total tax rate");
		taxs.setBounds(493, 284, 200, 50);
		panel_searchRecord.add(taxs);
		taxs.setColumns(10);
		
		JButton search = new JButton("Search ID");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
				int empID  = Integer.parseInt(empIDs.getText());
				int s = p.searchEmp(empID);
				if(s != -1)
				{
					Employee [] emp =p.getList();
					if(emp[s] instanceof hourlyPay)
					{
						pays.setText("null");
						times.setText(Double.toString((((hourlyPay)emp[s]).getTime())));
						salarys.setText(String.valueOf(((hourlyPay)emp[s]).getSalaryPerHour()));

					}
					else
						if(emp[s] instanceof fixedPay)
					{
						pays.setText(Double.toString(((fixedPay)emp[s]).getFixedSalary()));
						times.setText("null");
						salarys.setText("null");
					}
					
					empIDss.setText(Integer.toString(emp[s].getEmpID()));
					names.setText(emp[s].getName());
					taxs.setText(Double.toString(emp[s].getTax()));
					phones.setText(emp[s].getTelNo());
					addPays.setText(Double.toString(emp[s].getAddPay()));
					empTypes.setText(emp[s].getType());
					
				}
				else
					JOptionPane.showMessageDialog(null, "ID NOT FOUND");
				}catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Please input valid ID");
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Unknown error detected");
				}
			}
			
		});
		
		
		
		
		search.setBounds(313, 77, 120, 50);
		panel_searchRecord.add(search);
		
		JButton btnNewButton_3 = new JButton("Edit Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int x =0;
				try {
				int empID  = Integer.parseInt(empIDs.getText());
				int s = p.searchEmp(empID);
				if(s != -1)
				{
					
					x=0;
					Employee [] emp =p.getList();
					if(emp[s] instanceof hourlyPay)
					{
						pays.setText("null");
						((hourlyPay)emp[s]).setTime(Double.parseDouble(times.getText()));
						((hourlyPay)emp[s]).setSalaryPerHour(Double.parseDouble(salarys.getText()));
					}
					else
						if(emp[s] instanceof fixedPay)
					{
						((fixedPay)emp[s]).setFixedSalary(Double.parseDouble(pays.getText()));
						times.setText("null");
						salarys.setText("null");
					}
					
					emp[s].setEmpID(Integer.parseInt(empIDss.getText()));

					emp[s].setTax(Double.parseDouble(taxs.getText()));
					
					emp[s].setTelNo((phones.getText()));
					if(checkTelNo(phones.getText()) == false)
						throw new telNoException("");
					
					emp[s].setAddPay(Double.parseDouble(addPays.getText()));
					
					emp[s].setName(names.getText());
					if(checkComma(names.getText())==false)
						throw new nameException("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "ID not found");
						x=1;
					}
					}
					catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(null, "Please input a valid ID");
						x=1;
					}
					catch(nameException ne)
					{
						JOptionPane.showMessageDialog(null, "Invalid naming format");
						x=1;
					}
					catch(NullPointerException npe)
					{
						JOptionPane.showMessageDialog(null, "All filled must be filled up");
						x=1;
					}
					catch(telNoException telex)
					{
						JOptionPane.showMessageDialog(null,"PLEASE ENTER VALID TELEPHONE NO WITH 10 OR 11 INTEGER ONLY");
						x=1;
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Unknown error detected");
						x=1;
					}
					if(x ==0)
					{
						p.editEmp();
						JOptionPane.showMessageDialog(null, "EDIT UPDATE SUCCESSFULLY  ");
						switchPanels(panel_mainPanel);
					}
				
				
				
			}
		});
		
	


		btnNewButton_3.setBounds(443, 77, 120, 50);
		panel_searchRecord.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Employee ID: ");
		lblNewLabel.setBounds(10, 138, 200, 50);
		panel_searchRecord.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Type: ");
		lblNewLabel_1.setBounds(393, 343, 200, 50);
		panel_searchRecord.add(lblNewLabel_1);
		
		empTypes = new JLabel("Employee");
		empTypes.setBounds(493, 343, 200, 50);
		panel_searchRecord.add(empTypes);
		
		JButton btnNewButton_7 = new JButton("Clear Search");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empIDs.setText("");
				empIDss.setText("");
				names.setText("");
				phones.setText("");
				pays.setText("");
				addPays.setText("");
				times.setText("");
				taxs.setText("");
				empTypes.setText("");
				salarys.setText("");
			}
		});
		btnNewButton_7.setBounds(573, 77, 120, 50);
		panel_searchRecord.add(btnNewButton_7);
		
		empIDss = new JLabel("null");
		empIDss.setBounds(103, 138, 200, 50);
		panel_searchRecord.add(empIDss);
		
		JLabel lblNewLabel_25 = new JLabel("SEARCH EMPLOYEEE");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_25.setBounds(10, 11, 233, 55);
		panel_searchRecord.add(lblNewLabel_25);
		
		///////////////////////////////////////////////
		JPanel panel_printPaySlip = new JPanel();
		layeredPane.add(panel_printPaySlip, "name_767835190488300");
		panel_printPaySlip.setLayout(null);
		
		JButton text = new JButton("Generate Text File");
		text.setToolTipText("Generate a .txt pay slip");



		text.setBounds(40, 217, 200, 50);
		panel_printPaySlip.add(text);
		
		JButton word = new JButton("Generate Word File");
		word.setToolTipText("Generate a .doc pay slip");

		
		word.setBounds(40, 294, 200, 50);
		panel_printPaySlip.add(word);
		
	     class chooser implements ActionListener
	     {
	     	  public void actionPerformed(ActionEvent event)
	     	  {


	    		int x=0;
	    		Employee [] emp =p.getList();
	    		try
	    		{
	    			int empID  = Integer.parseInt(ID.getText());
	    			int s = p.searchEmp(empID);
	    			
	    				if(s!=-1)
	    				{
	    		     		PrintWriter outputFile;
	    		     		File file =null;
	    		     		File directory = null;
	    		     		JFileChooser fc = new JFileChooser();
	    		     		
		    				fc.setApproveButtonText("save");
							fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
							int saver = fc.showOpenDialog(null);
							
						    directory = fc.getSelectedFile();


	    					if(fileName.getText().equals("default"))
	    						throw new nameException("");
	    		
	    		     	    if (event.getSource() == text)
	 	    					file= new File(directory, fileName.getText()+".txt");
	    		     	    
	    		     	    if(event.getSource() == word)
		 	    				file= new File(directory, fileName.getText()+".doc");
	    		     	    
	    					if (saver == JFileChooser.APPROVE_OPTION) 
	    					{
	    						outputFile = new PrintWriter(file);
	    						
	    						outputFile.println("=================================================");
	    						outputFile.println("|EMPLOYEEE PAY SLIP				|");
	    						outputFile.println("=================================================");
	    						outputFile.println(emp[s].toString());
	    						outputFile.println("=================================================");
	    						outputFile.close();
	    						x=0;
	    						if(x==0)
	    							JOptionPane.showMessageDialog(null, "File saved");
	    					}
	    					else
	    						if (saver == JFileChooser.CANCEL_OPTION) 
	    							JOptionPane.showMessageDialog(null, "File not saved");

	    				}	
	    				else
	    					throw new empIDException("");
	    			}
	    			catch(empIDException eie)
	    			{
	    				JOptionPane.showMessageDialog(null, "Employee ID Not found");
	    				x=1;

	    			}
	    			catch(NumberFormatException nfe)
	    			{
	    				JOptionPane.showMessageDialog(null, "Please enter valid employee ID");
	    				x=1;
	    			}
	    			catch(nameException ex)
	    			{
	    				JOptionPane.showMessageDialog(null, "File Name cannot be default");
	    				x=1;
	    			}
	    			catch (FileNotFoundException e1) 
	    			{
	    				JOptionPane.showMessageDialog(null, "Please specific you saving path");
	    				x=1;
	    			}
	    			catch(Exception ex)
	    			{
	    				JOptionPane.showMessageDialog(null, "Unknown Error Detected");
	    				x=1;
	    			}
	    			finally
	    			{
	    				fileName.setText("default");
	    				ID.setText("");
	    			}

	     	  }
	     }
	     ActionListener listener = new chooser();
	     text.addActionListener(listener);
	     word.addActionListener(listener);
	     
	     
		JLabel lblNewLabel_3 = new JLabel("Employee ID");
		lblNewLabel_3.setBounds(40, 75, 200, 50);
		panel_printPaySlip.add(lblNewLabel_3);
		
		ID = new JTextField();
		ID.setToolTipText("Enter employee ID");
		ID.setBounds(142, 75, 200, 50);
		panel_printPaySlip.add(ID);
		ID.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				int empID  = Integer.parseInt(ID.getText());
				int s = p.searchEmp(empID);
				if(s != -1)
				{
					JOptionPane.showMessageDialog(null, " Search Found !!!");
				}
				else
					throw new empIDException("");
				}
				catch(empIDException eie)
				{
					JOptionPane.showMessageDialog(null, "Employee ID Not found");
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "Please enter valid employee ID");
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Unknown Error Detected");
				}
			}
		});
		btnNewButton.setBounds(348, 75, 200, 50);
		panel_printPaySlip.add(btnNewButton);
		
		JLabel lblNewLabel_19 = new JLabel("File Name: ");
		lblNewLabel_19.setBounds(40, 131, 193, 50);
		panel_printPaySlip.add(lblNewLabel_19);
		
		fileName = new JTextField();
		fileName.setToolTipText("Compulsory to enter a file name (Cannot be default)");
		fileName.setText("default");
		fileName.setBounds(142, 131, 200, 50);
		panel_printPaySlip.add(fileName);
		fileName.setColumns(10);
		
		JLabel lblNewLabel_26 = new JLabel("PRINT SLIP");
		lblNewLabel_26.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_26.setBounds(10, 11, 173, 40);
		panel_printPaySlip.add(lblNewLabel_26);
		////////////////////////////////////////////////////
		JPanel panel_chooseEmp = new JPanel();
		layeredPane.add(panel_chooseEmp, "name_812467648536400");
		panel_chooseEmp.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Please choose the type of employee");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_9.setBounds(242, 96, 373, 50);
		panel_chooseEmp.add(lblNewLabel_9);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setToolTipText("Compulsory to choose type of employee");
		comboBox.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					if (comboBox.getSelectedItem().toString().equals("Fixed Pay"))
						JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString() + " is selected.");
					
					else if (comboBox.getSelectedItem().toString().equals("Hourly Pay"))
						JOptionPane.showMessageDialog(null, comboBox.getSelectedItem().toString() + " is selected.");
				}
			}
		});
		comboBox.setBounds(263, 173, 333, 50);
		panel_chooseEmp.add(comboBox);
		
		comboBox.addItem("Choose");
		comboBox.addItem("Hourly Pay");
		comboBox.addItem("Fixed Pay");
		///////////////////////////////////////////
		JPanel panel_fixedPay = new JPanel();
		layeredPane.add(panel_fixedPay, "name_816818433887400");
		panel_fixedPay.setLayout(null);
		
		JLabel gross2 = new JLabel("Gross Pay:");
		gross2.setToolTipText("Gross pay: salary before any deductions and taxes taken out ");
		gross2.setBounds(367, 129, 200, 50);
		panel_fixedPay.add(gross2);
		
		salary2 = new JTextField();
		salary2.setBounds(485, 130, 200, 50);
		panel_fixedPay.add(salary2);
		salary2.setColumns(10);
		
		JLabel pay2 = new JLabel("Additional Pay:");
		pay2.setBounds(367, 216, 200, 50);
		panel_fixedPay.add(pay2);
		
		addPay2 = new JTextField();
		addPay2.setToolTipText("Additional pay: any bonus or supplemental pay ");
		addPay2.setBounds(485, 217, 200, 50);
		panel_fixedPay.add(addPay2);
		addPay2.setColumns(10);
		
		JLabel taxLabel = new JLabel("Total Tax(%):");
		taxLabel.setBounds(367, 310, 200, 50);
		panel_fixedPay.add(taxLabel);
		
		tax2 = new JTextField();
		tax2.setToolTipText("Enter total tax rate (normally between 0 to 1)");
		tax2.setBounds(485, 311, 200, 50);
		panel_fixedPay.add(tax2);
		tax2.setColumns(10);
		
		JButton save2 = new JButton("Save");
		save2.setToolTipText("Save employee record");
		save2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int x = 0;
				try
				{
				name = name2.getText();
				if(checkComma(name)==false)
					throw new nameException("");
				
				empID = Integer.parseInt(id2.getText());
				
				if(p.searchEmp(empID) != -1)
					throw new empIDException("");
				
				telNo = phone2.getText();
				salary = Double.parseDouble(salary2.getText());
				addPay = Double.parseDouble(addPay2.getText());
				
				tax = Double.parseDouble(tax2.getText());
				if(checkTelNo(telNo) == false)
					throw new telNoException("");
				
				
				fixedPay emp = new fixedPay(name, empID, tax,salary, addPay, telNo);
				p.appendEmp(emp);
				
				}
				catch(nameException comma)
				{
					JOptionPane.showMessageDialog(null, "Invalid name");
					x=1;
				}
				catch(telNoException telex)
				{
					JOptionPane.showMessageDialog(null,"Please enter valid Phone No (10 or 11 numerical digit)");
					x=1;
				}
				catch(empIDException empE)
				{
					JOptionPane.showMessageDialog(null, "Invalid repeating Employee ID");
					x=1;
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER ONLY NUMBER IN THESE FIELD \n -Employee ID\n -Salary Per Hour\n -Work hours\n -Tax");
					x=1;
				}
				catch(NullPointerException npe)
				{
					JOptionPane.showMessageDialog(null, "Please fill all the field");
					x=1;
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Unknown Error Detected");
					x=1;
				}

				if(x ==0)
				{
			        JOptionPane.showConfirmDialog(save2, "Save Successfully, Backing to Main!","Confirmation", JOptionPane.PLAIN_MESSAGE);	
				    
			        id2.setText("");
			        name2.setText("");
			        phone2.setText("");
			        tax2.setText("");
			        salary2.setText("");
			        addPay2.setText("");
					switchPanels(panel_mainPanel);
				}
	
			}
		});
		save2.setBounds(324, 409, 200, 50);
		panel_fixedPay.add(save2);
		
		JLabel lblNewLabel_2 = new JLabel("Name: ");
		lblNewLabel_2.setBounds(22, 129, 200, 50);
		panel_fixedPay.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Employee ID: ");
		lblNewLabel_4.setBounds(22, 216, 200, 50);
		panel_fixedPay.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Phone No:");
		lblNewLabel_5.setBounds(22, 310, 200, 50);
		panel_fixedPay.add(lblNewLabel_5);
		
		name2 = new JTextField();
		name2.setToolTipText("Enter employee name");
		name2.setBounds(115, 130, 200, 50);
		panel_fixedPay.add(name2);
		name2.setColumns(10);
		
		id2 = new JTextField();
		id2.setToolTipText("Enter employee ID");
		id2.setBounds(115, 216, 200, 50);
		panel_fixedPay.add(id2);
		id2.setColumns(10);
		
		phone2 = new JTextField();
		phone2.setToolTipText("Enter employee phone number");
		phone2.setBounds(115, 311, 200, 50);
		panel_fixedPay.add(phone2);
		phone2.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Employee Fixed Pay");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_20.setBounds(289, 10, 224, 50);
		panel_fixedPay.add(lblNewLabel_20);
		///////////////////////////////////////////
		JPanel panel_hourlyPay = new JPanel();
		layeredPane.add(panel_hourlyPay, "name_817578680630400");
		panel_hourlyPay.setLayout(null);
		
		JLabel lblNewLabel_16 = new JLabel("Salary Per Hour:");
		lblNewLabel_16.setBounds(426, 90, 200, 50);
		panel_hourlyPay.add(lblNewLabel_16);
		
		salary1 = new JTextField();
		salary1.setToolTipText("Enter salary per hour worked");
		salary1.setBounds(527, 90, 200, 50);
		panel_hourlyPay.add(salary1);
		salary1.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("Worked Hours:");
		lblNewLabel_17.setBounds(426, 181, 200, 50);
		panel_hourlyPay.add(lblNewLabel_17);
		
		time1 = new JTextField();
		time1.setToolTipText("Enter total worked hours");
		time1.setBounds(527, 181, 200, 50);
		panel_hourlyPay.add(time1);
		time1.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Additional Pay:");
		lblNewLabel_18.setBounds(428, 256, 200, 50);
		panel_hourlyPay.add(lblNewLabel_18);
		
		addPay1 = new JTextField();
		addPay1.setToolTipText("Additonal pay: any bonus or supplemental pay");
		addPay1.setBounds(527, 256, 200, 50);
		panel_hourlyPay.add(addPay1);
		addPay1.setColumns(10);
		
		JLabel Tax = new JLabel("Total Tax(%): ");
		Tax.setBounds(426, 337, 200, 50);
		panel_hourlyPay.add(Tax);
		
		tax1 = new JTextField();
		tax1.setToolTipText("Enter total tax rate (normally between 0 to 1)");
		tax1.setBounds(527, 337, 200, 50);
		panel_hourlyPay.add(tax1);
		tax1.setColumns(10);
		
		JButton save1 = new JButton("Save");
		save1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int x = 0;
				try
				{
				name = name1.getText();
				if(checkComma(name)==false)
					throw new nameException("");
				
				empID = Integer.parseInt(id1.getText());
				if(p.searchEmp(empID) != -1)
					
					throw new empIDException("");
				salary = Double.parseDouble(salary1.getText());
				time = Double.parseDouble(time1.getText());
				tax = Double.parseDouble(tax1.getText());
				
				telNo = phone1.getText();
				if(checkTelNo(telNo) == false)
					throw new telNoException("");
				
				addPay = Double.parseDouble(addPay1.getText());
				hourlyPay emp = new hourlyPay(name, empID, tax, time,salary, addPay, telNo);
				p.appendEmp(emp);			
				
				}
				catch(nameException comma)
				{
					JOptionPane.showMessageDialog(null, "Invalid name");
					x=1;
				}
				catch(telNoException telex)
				{
					JOptionPane.showMessageDialog(null,"Please enter valid Phone No (10 or 11 numerical digit)");
					x=1;
				}
				catch(empIDException empE)
				{
					JOptionPane.showMessageDialog(null, "Invalid reapeating  Employee ID");
					x=1;
				}
				catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(null, "PLEASE ENTER ONLY NUMBER IN THESE FIELD \n -Employee ID\n -Salary Per Hour\n -Work hours\n -Tax");
					x=1;
				}
				catch(NullPointerException npe)
				{
					JOptionPane.showMessageDialog(null, "Please fill all the field");
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Unknown Error Detected");
					x=1;
				}
				if(x ==0)
				{
	

			        JOptionPane.showConfirmDialog(save2, "Save Successfully, Backing to Main!","Confirmation", JOptionPane.PLAIN_MESSAGE);	
			        id1.setText("");
			        name1.setText("");
			        phone1.setText("");
			        time1.setText("");
			        tax1.setText("");
			        salary1.setText("");
			        addPay1.setText("");

					switchPanels(panel_mainPanel);
				}
				}

		});
		

		save1.setBounds(287, 428, 200, 50);
		panel_hourlyPay.add(save1);
		
		JLabel lblNewLabel_22 = new JLabel("Employee Hourly Pay");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_22.setBounds(287, 0, 296, 50);
		panel_hourlyPay.add(lblNewLabel_22);
		
		JLabel lblNewLabel_6 = new JLabel("Name: ");
		lblNewLabel_6.setBounds(43, 90, 200, 50);
		panel_hourlyPay.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Employee ID: ");
		lblNewLabel_7.setBounds(43, 181, 200, 50);
		panel_hourlyPay.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Phone No: ");
		lblNewLabel_8.setBounds(43, 256, 200, 50);
		panel_hourlyPay.add(lblNewLabel_8);
		
		name1 = new JTextField();
		name1.setToolTipText("Enter employee's name");
		name1.setBounds(147, 90, 200, 50);
		panel_hourlyPay.add(name1);
		name1.setColumns(10);
		
		id1 = new JTextField();
		id1.setToolTipText("Enter employee's  ID");
		id1.setBounds(147, 181, 200, 50);
		panel_hourlyPay.add(id1);
		id1.setColumns(10);
		
		phone1 = new JTextField();
		phone1.setToolTipText("Enter employee\u2018s phone number");
		phone1.setBounds(147, 256, 200, 50);
		panel_hourlyPay.add(phone1);
		phone1.setColumns(10);
		
		/////////////////////////////////////////////////////////////////
		JButton btnNewButton_6 = new JButton("Salary and Wages Information");
		btnNewButton_6.setToolTipText("Fill employee's salary information");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (comboBox.getSelectedItem().equals("Hourly Pay"))
				switchPanels(panel_hourlyPay);
				
				else if (comboBox.getSelectedItem().equals("Fixed Pay"))
					switchPanels(panel_fixedPay);
			}
		});
		btnNewButton_6.setBounds(307, 248, 238, 50);
		panel_chooseEmp.add(btnNewButton_6);
		
		JLabel lblNewLabel_23 = new JLabel("ADD EMPLOYEE");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel_23.setBounds(10, 11, 185, 50);
		panel_chooseEmp.add(lblNewLabel_23);
		
		/////////////////////////////////////////////////////////////////////
		
		
		JButton btnNewButton_menu_addRecord = new JButton("Add record");
		btnNewButton_menu_addRecord.setToolTipText("Add record");
		Image addRecordIcon = new ImageIcon(this.getClass().getResource("/addRecord.png")).getImage();
		btnNewButton_menu_addRecord.setIcon(new ImageIcon(addRecordIcon));
		btnNewButton_menu_addRecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_menu_addRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_chooseEmp);
			}
		});
		btnNewButton_menu_addRecord.setBounds(10, 51, 115, 47);
		frame.getContentPane().add(btnNewButton_menu_addRecord);
		
		JButton btnNewButton_menu_recordList = new JButton("Record list");
		btnNewButton_menu_recordList.setToolTipText("Record list");
		btnNewButton_menu_recordList.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_menu_recordList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_recordList);
			}
		});
		btnNewButton_menu_recordList.setBounds(10, 117, 115, 47);
		Image recordListIcon = new ImageIcon(this.getClass().getResource("/list.png")).getImage();
		btnNewButton_menu_recordList.setIcon(new ImageIcon(recordListIcon));
		frame.getContentPane().add(btnNewButton_menu_recordList);
		
		JButton btnNewButton_menu_searchRecord = new JButton("Search ");
		btnNewButton_menu_searchRecord.setToolTipText("Search record");
		Image searchIcon = new ImageIcon(this.getClass().getResource("/search.png")).getImage();
		btnNewButton_menu_searchRecord.setIcon(new ImageIcon(searchIcon));
		btnNewButton_menu_searchRecord.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_menu_searchRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_searchRecord);
			}
		});
		btnNewButton_menu_searchRecord.setBounds(10, 182, 115, 47);
		frame.getContentPane().add(btnNewButton_menu_searchRecord);
		
		JButton btnNewButton_menu_printPaySlip = new JButton("Print pay slip");
		btnNewButton_menu_printPaySlip.setToolTipText("Print pay slip");
		Image printIcon = new ImageIcon(this.getClass().getResource("/print.png")).getImage();
		btnNewButton_menu_printPaySlip.setIcon(new ImageIcon(printIcon));
		btnNewButton_menu_printPaySlip.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_menu_printPaySlip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(panel_printPaySlip);
			}
		});
		btnNewButton_menu_printPaySlip.setBounds(10, 248, 115, 47);
		frame.getContentPane().add(btnNewButton_menu_printPaySlip);
		
		JButton btnNewButton_4 = new JButton("CALCULATOR");
		btnNewButton_4.setToolTipText("Open calculator");
		Image calcIcon = new ImageIcon(this.getClass().getResource("/calculator.png")).getImage();
		btnNewButton_4.setIcon(new ImageIcon(calcIcon));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Runtime runTime = Runtime.getRuntime();
				
				try
				{
					runTime.exec("calc");
				}
				catch (IOException e1)
				{
					System.out.println(e1);
				}
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnNewButton_4.setBounds(10, 314, 115, 50);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Exit");
		Image exitIcon = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		btnNewButton_5.setIcon(new ImageIcon(exitIcon));
		btnNewButton_5.setToolTipText("Exit payroll programme");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int respond = JOptionPane.showConfirmDialog(null, "Do you sure want to quit ?","Yes",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(respond == JOptionPane.YES_OPTION)
				{
					System.exit(1);
				}
				else
				 switchPanels(panel_mainPanel);
			}
		});
		btnNewButton_5.setBounds(10, 385, 115, 47);
		frame.getContentPane().add(btnNewButton_5);
		
		JLabel lblNewLabel_21 = new JLabel("");
		Image frameBackground = new ImageIcon(this.getClass().getResource("/frameBackground.jpg")).getImage();
		lblNewLabel_21.setIcon(new ImageIcon(frameBackground));
		lblNewLabel_21.setBounds(-35, -37, 1061, 721);
		frame.getContentPane().add(lblNewLabel_21);
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu lbl_date = new JMenu("New menu");
		menuBar.add(lbl_date);
		
		JMenu lbl_time = new JMenu("New menu");
		menuBar.add(lbl_time);

		Thread clock = new Thread()
		{
	public void run()
	{
		
			for(;;)
			{
				Calendar calendar = new GregorianCalendar();
				int month = calendar.get(Calendar.MONTH);
				int year = calendar.get(Calendar.YEAR);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				
				int second = calendar.get(Calendar.SECOND);
				int minute = calendar.get(Calendar.MINUTE);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				
				lbl_date.setText((month + 1) + "/" + day + "/" + year);
				lbl_time.setText(hour + ":" + (minute) + ":" + second);
			}
	
	}
		};
		clock.start();
		
		JMenu mnNewMenu = new JMenu(" WELCOME TO COMPANY PAYROLL PROGRAM ! ! !");
		menuBar.add(mnNewMenu);
		
	}
}
