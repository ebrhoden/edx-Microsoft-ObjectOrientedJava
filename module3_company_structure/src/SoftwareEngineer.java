public class SoftwareEngineer extends TechnicalEmployee {
    private boolean accessToCode;

    public SoftwareEngineer(String name) {
        super(name);
        this.accessToCode = false;
    }

    public boolean getCodeAcess(){
        return accessToCode;
    }

    public void setCodeAccess(boolean accessToCode){
        this.accessToCode = accessToCode;
    }

    public boolean checkInCode(){
        boolean returnValue = false;
        if(this.getManager()==null){
            System.out.println("Sorry, "+ this.getName() +", but you can't checkin yet because you have no designed manager!");
        } else{
            TechnicalLead manager = (TechnicalLead) this.getManager();
            if (manager.approveCheckIn(this)){
                this.setCheckinCounter(this.getCheckinCounter()+1);
                returnValue = true;
            } else {
                this.accessToCode=false;
            }

        }
        return returnValue;
    }

    public int getSuccessfulCheckIns(){
        return this.getCheckinCounter();
    }






}
