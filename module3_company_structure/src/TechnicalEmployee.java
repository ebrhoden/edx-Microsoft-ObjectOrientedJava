public abstract class TechnicalEmployee extends Employee {
    private int checkinCounter = 0;
    private static double defaultSalary = 75000;

    //The only reason why this class is abstract is to prevent instantiation;

    public TechnicalEmployee(String name){
        super(name, defaultSalary);
        this.checkinCounter = 0;
    }

    public String employeeStatus(){
        String returnValue = this.getEmployeeID() + " "  + this.getName() + " has " + this.checkinCounter + " succesful check ins";
        return returnValue;

    }

    public int getCheckinCounter(){
        return this.checkinCounter;
    }

    public void setCheckinCounter(int checkinCounter){
        this.checkinCounter = checkinCounter;
    }

}
