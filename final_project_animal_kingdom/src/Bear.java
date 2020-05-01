import java.awt.*;

public class Bear extends Critter {
    private boolean polar;
    private int moves;

    public Bear(boolean polar){
        this.polar = polar;
        this.moves = -1;
    }

    @Override
    public Color getColor() {
        //Color.WHITE for a polar bear (when polar is true),
        // Color.BLACK otherwise (when polar is false)
        Color returnColor = Color.BLACK;
        if (polar){
            returnColor = Color.WHITE;
        }
        return returnColor;
    }

    @Override
    public Action getMove(CritterInfo info) {
        //always infect if an enemy is in front, otherwise hop if possible, otherwise turn left.
        this.moves++;
        Action returnAction = Action.LEFT;
        if(info.frontThreat()){
            returnAction = Action.INFECT;
        } else{
            //Check if it can hop
            if(info.getFront()==Neighbor.EMPTY){
                returnAction = Action.HOP;
            }
        }
        return returnAction;
    }

    @Override
    public String toString(){
        //Should alternate on each different move between a slash character (/)
        // and a backslash character () starting with a slash.
        if (moves%2==0){
            return "/";
        } else {
            return "\\";
        }

    }


}
