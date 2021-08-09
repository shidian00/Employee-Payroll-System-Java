
public abstract class Employee 
{
	private String name, telNo, type;
	private int empID;
	private double tax, addPay;

	public Employee(String name, int empID, double tax, double addPay,String telNo, String type)
	{
		this.name =name;
		this.empID = empID;
		this.tax = tax;
		this.addPay = addPay;
		this.telNo =telNo;
		this.type = type;
	}

	public double getTax()
	{
		return tax;
	}
	public String getTelNo() 
	{
		return telNo;
	}
	public double getAddPay() 
	{
		return addPay;
	}
	public String getName()
	{
		return name;
	}
	public int getEmpID()
	{
		return empID;
	}
	public String getType()
	{
		return type;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setEmpID(int empID)
	{
		this.empID = empID;
	}
	public void setAddPay(double addPay) 
	{
		this.addPay = addPay;
	}
	public void setTelNo(String telNo) 
	{
		this.telNo = telNo;
	}
	public void setTax(double tax)
	{
		this.tax = tax;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	
	abstract public double computePay();
	abstract public double computeGrossPay();
	public abstract String toString();
	public abstract String Saver();  //seperate the data with comma
}
