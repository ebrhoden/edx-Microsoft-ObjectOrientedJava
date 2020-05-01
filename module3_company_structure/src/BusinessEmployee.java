public abstract class BusinessEmployee extends Employee {
    private static double defaultSalary = 50000;
    private double bonusBudget = 0;

    public BusinessEmployee(String name) {
        super(name, defaultSalary);
        this.bonusBudget = 0;
    }

    public double getBonusBudget(){
        return this.bonusBudget;
    }


    @Override
    public String employeeStatus() {
        String returnString = null;
        returnString = this.getEmployeeID() + " " + this.getName() + " with a budget of " + this.getBonusBudget();
        return returnString;
    }

    public void setBonusBudget(double bonusBudget){
        this.bonusBudget = bonusBudget;
    }
}
