import java.awt.*;
import java.util.Random;

public class Tiger extends Critter {
    private int moves = 0;
    private Color color;
    Random rand = new Random();



    public Tiger(){
        this.moves = 0;
        this.color = super.getColor(); //Color will always be black when starting
    }

    @Override
    public Color getColor() {
        //Randomly picks one of three colors (Color.RED, Color.GREEN, Color.BLUE) and uses that color for three moves,
        // then randomly picks one of those colors again for the next three moves,
        // then randomly picks another one of those colors for the next three moves, and so on.
        Color returnColor = this.color;
        int didHePickYet = 0;

        if(this.moves%3==0){


            //Cant color it with the color it currently has
            //0 = Red
            //1 = Green
            //2 = Blue
            while (didHePickYet==0){
                int random = rand.nextInt(3);

                if((this.color != Color.RED) && (random == 0)){
                    returnColor = Color.RED;
                    didHePickYet = 1;
                } else if((this.color!= Color.GREEN) && (random == 1)){
                    returnColor = Color.GREEN;
                    didHePickYet = 1;
                } else if((this.color!= Color.BLUE) && (random == 2)){
                    returnColor = Color.BLUE;
                    didHePickYet = 1;
                }
            }
            this.color = returnColor;
            this.moves = 0;
        }
        return returnColor;

    }

    @Override
    public Action getMove(CritterInfo info) {
        this.moves = this.moves + 1;
        Action returnAction = Action.HOP;
        if(info.frontThreat()){
            returnAction = Action.INFECT;
        } else if ((info.getFront()==Neighbor.WALL) || (info.getRight()==Neighbor.WALL)){
            returnAction = Action.LEFT;
        } else if (info.getFront()==Neighbor.SAME){
            returnAction = Action.RIGHT;
        }

        return returnAction;

    }

    @Override
    public String toString() {
        return "TGR";
    }
}
