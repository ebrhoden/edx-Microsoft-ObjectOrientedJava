import java.util.ArrayList;

public class BusinessLead extends BusinessEmployee implements ManagerInterface {
    private int headCount=0;
    private int numberOfDirectReports=0;
    private ArrayList<Accountant> listDirectReports;


    public BusinessLead(String name) {
        super(name);
        this.setBaseSalary(this.getBaseSalary()*2);
        this.headCount = 10;
        this.listDirectReports = new ArrayList<>();
    }

    @Override
    public boolean hasHeadCount() {
        return (listDirectReports.size()<this.headCount);
    }

    public boolean addReport(Accountant e, TechnicalLead supportTeam){
        boolean returnValue = false;

        if(this.hasHeadCount()){
            listDirectReports.add(e);
            e.setManager(this);
            e.supportTeam(supportTeam);
            this.setBonusBudget(this.getBonusBudget() + e.getBaseSalary()*1.1);
            returnValue = true;
        }
        return returnValue;


    }

    @Override
    public boolean requestBonus(Employee e, double bonus) {
        boolean returnValue = false;
        if(bonus < this.getBonusBudget()){
            this.setBonusBudget(this.getBonusBudget() - bonus);
            //Giving the employee his bonus
            e.setBaseSalary(e.getBaseSalary() + bonus);
            returnValue = true;
        }
        return returnValue;
    }

    public boolean approveBonus(Employee e, double bonus){
        boolean returnValue = false;
        for(int counter = 0; counter < listDirectReports.size(); counter++){
            if((listDirectReports.get(counter).getTeamSupported() == e.getManager()) &&(listDirectReports.get(counter).approveBonus(bonus))){
                //Giving the bonus to the employee
                e.setBaseSalary(e.getBaseSalary() + bonus);
                //The accountant's bonus should be decreased.
                listDirectReports.get(counter).setBonusBudget(listDirectReports.get(counter).getBonusBudget() - bonus);
                returnValue = true;
            }
        }
        return returnValue;

    }


    public String getTeamStatus(){
        String returnString = null;
        returnString = this.employeeStatus();
        if (!getListDirectReports().isEmpty()){
            returnString += " and is managing:";
            for (int counter = 0; counter < this.getListDirectReports().size(); counter++) {
                returnString += "\n   ";
                returnString += this.getListDirectReports().get(counter).employeeStatus();
            }
        }
        return returnString;

    }


    public ArrayList<Accountant> getListDirectReports() {
        return this.listDirectReports;
    }
}
