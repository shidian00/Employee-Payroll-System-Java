public class hourlyPay extends Employee
{
	private double time; //work time in hour
	private double salaryPerHour;
	
	public hourlyPay(String name, int empID, double tax, double time, double salaryPerHour, double addPay, String telNo) 
	{
		super(name, empID, tax, addPay, telNo, "HourlyPay");
		this.time = time;
		this.salaryPerHour = salaryPerHour;
	}

	public double getSalaryPerHour()
	{
		return salaryPerHour;
	}
	public double getTime()
	{
		return time;
	}
	public void setSalaryPerHour(double salaryPerHour)
	{
		this.salaryPerHour = salaryPerHour;
	}
	public void setTime(double time)
	{
		this.time = time;
	}

	@Override
	public String toString()
	{
		return ("|Employee ID	:" + getEmpID() + "				"+
				"\n|Employee Name	:" + getName() +"			"+
				"\n|Employee Type	:" + getType() +"			"+
				"\n|Normal Salary	:RM" + computePay() +"				"+
				"\n|SalaryPerHour	:RM" + getSalaryPerHour() +"			"+
				"\n|Work Times	:" + getTime() +"					"+
				"\n|Tax		:" + getTax() +"%				"+
				"\n|Additional Pay	:RM" + getAddPay() + "			" +
				"\n|Total Payable	:RM" + computeGrossPay() +"			"
				);
				
			
	}
	
	@Override
	public String Saver()
	{
		return getName() + "," + getEmpID() + ","+ getTax() + ","+getTime() + ","+getSalaryPerHour() + "," + getAddPay() + "," + getTelNo() ;
	}
	
	@Override
	public double computePay() 
	{
		return getTime()*getSalaryPerHour();
	}
	
	@Override
	public double computeGrossPay()
	{
		return computePay() - (computePay() * getTax()/100)+ getAddPay();  
	}

}
