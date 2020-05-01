import java.util.ArrayList;

public class TechnicalLead extends TechnicalEmployee implements ManagerInterface {
    private int headCount=0;
    private int numberOfDirectReports=0;
    private ArrayList<SoftwareEngineer> listDirectReports;
    //private BusinessLead supportingBusinessLead = null;
    //private Accountant supportingAccountant = null;

    public TechnicalLead(String name) {
        super(name);
        this.setBaseSalary(this.getBaseSalary()*1.3);
        this.headCount = 4;
        this.listDirectReports = new ArrayList<>();
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

    //This could've not been inherited or implementer through an interface because the
    //addReport from TechincalLead and addReport from BusinessLead take different parameters
    public boolean addReport(SoftwareEngineer e) {
        boolean returnValue = false;
        if(this.hasHeadCount()){
            this.listDirectReports.add(e);
            e.setManager(this);
            returnValue = true;
        }
        return returnValue;
    }


    public boolean approveCheckIn(SoftwareEngineer e){
        return ((e.getManager() == this) && (e.getCodeAcess()));

    }


    @Override
    public boolean hasHeadCount() {
        return (this.listDirectReports.size()<this.headCount);
    }


    @Override
    public boolean requestBonus(Employee e, double bonus) {
        return false;
    }

    public ArrayList<SoftwareEngineer> getListDirectReports() {
        return this.listDirectReports;
    }
}
