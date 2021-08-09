import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class PayRoll  
{
	private ArrayList <Employee> eList ;
	private Scanner sFile;
	private String file = "EmployeeInfo.txt";
	private File myFile = new File(file);
	
	private FileWriter fw; // used for appending
	private PrintWriter pw;
	
	public void fileOutputfP() //get data from textFile
	{
		try
		{
			sFile = new Scanner(myFile); 
			while (sFile.hasNext())
			{ 
				String data = sFile.nextLine();
				String [] values = data.split(",");
				if(values.length ==6)
				{
				addEmp(new fixedPay(values[0], Integer.parseInt(values[1]), Double.parseDouble(values[2]), 
						Double.parseDouble(values[3]), Double.parseDouble(values[4]), values[5]));
				 //String name, int empID, double tax, double fixedSalary, addPay, telNo
				}
				else
					if(values.length ==7)
					{
					addEmp(new hourlyPay(values[0], Integer.parseInt(values[1]), Double.parseDouble(values[2]),
							Double.parseDouble(values[3]),Double.parseDouble(values[4]), Double.parseDouble(values[5]), values[6]));
					//String name, int empID, double tax, double time, double salaryPerHour,double addPay, String telNo
					}
			}
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "File Not found, New Employee Info File created once you add employee");
		}
		catch (Exception e)
		{
			System.out.print("Unknown error detected");
		}

	}
	
	public PayRoll()
	{
		eList = new ArrayList<Employee>();
		fileOutputfP();
	}
	
	public void addEmp(hourlyPay hp) //addEmp depend on subclass // get from GUI and pass it here
	{
		eList.add(hp);
	}
	
	public void addEmp(fixedPay fp) //addEmp depend on subclass // get from GUI and pass it here
	{
		eList.add(fp);
	}
	public void appendEmp(Employee e)
	{

		try {
			fw = new FileWriter(myFile, true);
			pw = new PrintWriter(fw);
			if(e instanceof fixedPay)
			{
				pw.print("\n"+e.getName()+","+e.getEmpID()+","+e.getTax()+","+((fixedPay) e).getFixedSalary() + ","+e.getAddPay() + "," + e.getTelNo());
				pw.close();
				addEmp((fixedPay) e);
			}
			else
				if(e instanceof hourlyPay)
				{
					pw.print("\n"+e.getName() + "," + e.getEmpID() + ","+ e.getTax() + ","+ 
					((hourlyPay) e).getTime() + ","+ ((hourlyPay) e).getSalaryPerHour() + "," + e.getAddPay() + "," + e.getTelNo());
					pw.close();
					addEmp((hourlyPay) e);
				}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void delEmp(int removeID)    //update the file for deleted part
	{
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		eList.remove(removeID);
		for(int x = 0; x < eList.size();x++)
		{
			
				if(x==eList.size()-1)
				{
					pw.print(eList.get(x).Saver());
				}
				else
					pw.println(eList.get(x).Saver());
		}
		pw.close();
	}
	
	public void editEmp() 
	{
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int x = 0; x < eList.size();x++)
		{
				if(x==eList.size()-1)
				{
					pw.print(eList.get(x).Saver());
				}
				else
					pw.println(eList.get(x).Saver());
		}
		pw.close();

	}
	
	public int searchEmp(int empID)  //getText from the GUI
	{
		for(int x = 0; x< eList.size(); x++)
		{
			if(eList.get(x).getEmpID() == empID)
				return x;
		}
		return -1;
	}
	

	
	public Employee[] getList()
	{
		Employee [] e = new Employee[eList.size()];
		return(eList.toArray(e));
	}
}
