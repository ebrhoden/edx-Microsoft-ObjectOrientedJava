public abstract class Employee {
    private String name;
    private double baseSalary;
    private int id;

    private Employee manager=null;

    static private int idCounter=0;


    public Employee(String name, double baseSalary){
    idCounter++;
    this.name = name;
    this.baseSalary = baseSalary;
    this.id = idCounter;
    }

    public double getBaseSalary(){
        return this.baseSalary;
    }

    public void setBaseSalary(double baseSalary){ this.baseSalary = baseSalary;}

    public String getName(){
        return this.name;
    }

    public int getEmployeeID(){
        return this.id;
    }

    public boolean equals(Employee other){
        return (this.id == other.id);
    }

    public String toString(){
        return this.id + " " + this.baseSalary;
    }

    public Employee getManager(){
        return this.manager;
    }

    public void setManager(Employee manager){
        this.manager = manager;
    }


    public abstract String employeeStatus();


}

