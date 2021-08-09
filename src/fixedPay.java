public class fixedPay extends Employee
{
	public double fixedSalary;

	public fixedPay(String name, int empID, double tax, double fixedSalary, double addPay, String telNo) 
	{
		super(name, empID, tax, addPay, telNo,"FixedPay");
		this.fixedSalary = fixedSalary;
	}

	public double getFixedSalary()
	{
		return fixedSalary;
	}
	public void setFixedSalary(double fixedSalary)
	{
		this.fixedSalary = fixedSalary;
	}

	@Override
	public double computePay()
	{
		return getFixedSalary();
	}
	
	
	@Override
	public double computeGrossPay()
	{
		return getFixedSalary() - (getFixedSalary() * getTax()/100) + getAddPay();  
	}

	@Override
	public String toString() {
		return ("|Employee ID	:" + getEmpID() + "				"+
				"\n|Employee Name	:" + getName() +"			"+
				"\n|Employee Type	:" + getType() +"			"+
				"\n|Normal Salary	:RM" + computePay() +"			"+
				"\n|Fixed Pay	:RM" + getFixedSalary() +"			"+
				"\n|Tax		:" + getTax() +"%				"+
				"\n|Additional Pay	:RM" + getAddPay() + "			" +
				"\n|Total Payable	:RM" + computeGrossPay() +"			"
				);
	}	
	
	@Override
	public String Saver()
	{
		return getName()+","+ getEmpID()+ "," + getTax() + "," + getFixedSalary()+ "," + getAddPay() + "," + getTelNo();
	}


}
