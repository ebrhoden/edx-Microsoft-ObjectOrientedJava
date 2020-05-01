public class Accountant extends BusinessEmployee {
    private TechnicalLead supportingTechnicalLead;
    private BusinessLead manager;

    public Accountant(String name){
        super(name);
        this.setBonusBudget(0);
        this.supportingTechnicalLead = null;

    }

    public TechnicalLead getTeamSupported(){
        return this.supportingTechnicalLead;
    }

    public void supportTeam(TechnicalLead lead){
        this.supportingTechnicalLead = lead;
        double sumOfSoftwareEngineersSalary = 0;
        for (int counter=0;counter<lead.getListDirectReports().size();counter++){
            sumOfSoftwareEngineersSalary += lead.getListDirectReports().get(counter).getBaseSalary();
        }
        sumOfSoftwareEngineersSalary *= 1.1;
        this.setBonusBudget(this.getBonusBudget()+sumOfSoftwareEngineersSalary);
    }

    public boolean approveBonus(double bonus){
        return ((this.supportingTechnicalLead!=null) && (bonus < this.getBonusBudget()));
    }

    public String employeeStatus(){
        String returnString;
        returnString = super.employeeStatus();
        if(this.supportingTechnicalLead!=null){
            returnString += " is supporting " + this.supportingTechnicalLead.getName();
        } else{
            returnString += " who is not supporting any Technical Lead";
        }

        return returnString;
    }




}
