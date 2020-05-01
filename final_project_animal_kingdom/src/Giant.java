import java.awt.*;

public class Giant extends Critter {
    private int moves = 0;



    public Giant(){
        this.moves = 0;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }


    @Override
    public String toString() {
        String returnString = "fee";
        if(this.moves >= 6 && this.moves<12){
            returnString = "fie";
        } else if(this.moves >= 12 && this.moves<18){
            returnString = "foe";
        } else if(this.moves >= 18 && this.moves<24){
            returnString = "fum";
            if(this.moves==23){
                this.moves = 0;
            }

        }
        return returnString;
    }

    @Override
    public Action getMove(CritterInfo info) {
        Action returnAction = Action.RIGHT;
        if(info.frontThreat()){
            returnAction = Action.INFECT;
        } else if(info.getFront()==Neighbor.EMPTY){
            returnAction = Action.HOP;
        }
        this.moves++;
        return returnAction;
    }


}
