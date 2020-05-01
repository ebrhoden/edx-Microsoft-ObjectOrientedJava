import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*I accidentally inverted the horizontal and vertical axis ... this was a big mistake btw
So when the program asks for X, its really asking the VERTICAL coordinate
And when it asks for Y, its the HORIZONTAL coordinate

The game keeps track of the coordinates that have already been selected.
If you try a coordinate that you have already tried, the program will stop you
If you try a coordinate that the computer has already tried, the program will stop you

 */
public class battleship {
    public static int userShips=5;
    public static int computerShips=5;
    public static Scanner input = new Scanner(System.in); //This line creates a Scanner for you to use

    public static void main(String[] args) {
        int endGame=-1;
        char[][] grid = new char[10][10];
        ArrayList<String> computerGuesses = new ArrayList<>();
        ArrayList<String> playerGuesses = new ArrayList<>();
        ArrayList<String> computerMisses = new ArrayList<>();


        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, the sea is empty.\n");

        print_grid(grid);

        //Player deploys his ships
        for(int i=0; i<5; i++){
            place_player(grid, i);
        }

        //Computer deploys his ships
        System.out.println("\nComputer is deploying his ships");
        for(int i=0; i<5; i++){
            place_computer(grid);
            System.out.println((i+1) + ".ship DEPLOYED");
        }

        //Battle begins

        while(endGame==-1){
            player_turn(grid, playerGuesses, computerMisses);
            if(userShips==0 || computerShips==0){
                endGame=0;
            } else{
                computer_turn(grid, computerGuesses, computerMisses, playerGuesses);
            }
            System.out.println("");
            print_grid(grid);
            print_footer(userShips, computerShips);
        }

        if(userShips==0){
            System.out.println("\n*** GAME OVER ***");
        } else if (computerShips==0){
            System.out.println("Hooray! You win the Battle :)");
        }

    }

    public static boolean test_valid_coordinate(int x, int y){
        boolean retorno = true;
        if(x>9 || x<0 || y>9 || y<0){
            retorno = false;
        }
        return retorno;
    }

    public static void player_turn(char [][] grid, ArrayList<String> playerGuesses,
                                   ArrayList<String> computerMisses){
        int x = 9, y= 9;
        System.out.println("\nYOUR TURN");
        int error = 1;

        while(error==1){
            System.out.print("Enter X coordinate: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate: ");
            y = input.nextInt();
            if(test_valid_coordinate(x,y)){
                if(playerGuesses.contains(x +""+ y)){
                    System.out.println("You have already guessed this coordinate! Please enter a different one.");
                } else if (grid[x][y] == 'x') {
                    System.out.println("The computer has already sunken one of your ships here! " +
                            "Enter different coordinates.");
                } else if (grid[x][y] == '!'){
                    System.out.println("The computer has already sunken one of its own ships here! " +
                            "Enter different coordinates.");
                } else if(computerMisses.contains(x +""+ y)){
                    System.out.println("The computer missed when targeting here! Try different coordinates.");
                }
                else{
                    error=-1;
                }
            }
            else{
                System.out.println("Invalid coordinates! Please try another one with numbers ranging from 0 to 9");
            }
        }

        playerGuesses.add(x +""+ y);

        switch(grid[x][y]){
            case '#':
                System.out.println("Boom! You sunk the ship!");
                grid[x][y] = '!';
                computerShips--;
                break;
            case '@':
                System.out.println("Oh no, you sunk your own ship :(");
                grid[x][y] = 'x';
                userShips--;
                break;
            case 0:
                System.out.println("You missed.");
                grid[x][y] = '-';
        }
    }


    public static void computer_turn(char [][] grid,
                                     ArrayList<String> computerGuesses,
                                     ArrayList<String> computerMisses,
                                     ArrayList<String> playerGuesses){
        System.out.println("COMPUTER'S TURN");
        int x,y;
        do {
            x = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            y = ThreadLocalRandom.current().nextInt(0, 9 + 1);
        } while ((computerGuesses.contains(x +""+ y))
        || (playerGuesses.contains(x +""+ y)));
        //This doesn't allow the computer to guess where the player has already guesses OR
        //where the computer has already guessed.

        computerGuesses.add(x +""+ y);
        switch(grid[x][y]){
            case '#':
                System.out.println("The Computer sunk one of its own ships");
                grid[x][y] = '!';
                computerShips--;
                break;
            case '@':
                System.out.println("The Computer sunk one of your ships!");
                grid[x][y] = 'x';
                userShips--;
                break;
            case 0:
                System.out.println("Computer missed.");
                computerMisses.add(x +""+ y);
        }
        //System.out.println("Computer's guess was: "+ x +" "+y);




    }

    public static void place_player(char [][] grid, int i){
        int error = 1, x = 0, y = 0;


        while(error==1){
            System.out.print("\nEnter X coordinate for your " + (i+1) +". ship: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + (i+1) +". ship: ");
            y = input.nextInt();

            if (test_valid_coordinate(x,y)){
                if(grid[x][y] != 0){
                    System.out.println("You have already placed a ship here! You need different coordinates.");
                } else{
                    error = -1;
                }
            }
            else{
                System.out.println("Invalid coordinate! Please enter a different one.");
            }
        }

        //When it gets here, the coordinates are valid and there are no overlaps
        grid[x][y] = '@';

    }

    public static void place_computer(char [][] grid){
        //Generating int from 0 to 9 (including them)
        int x,y;
        do {
            x = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            y = ThreadLocalRandom.current().nextInt(0, 9 + 1);
        } while ((int)grid[x][y]!=0); //Check if there is no boat in the coordinates

        grid[x][y]='#';

    }


    public static void print_grid(char [][] grid){
        System.out.print(" |");
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i + "|");
        }
        System.out.println();
        for (int i = 0; i < grid.length ; i++) {
            System.out.print(i+ "|");
            for (int j = 0; j <10 ; j++) {

                //if there is nothing stored in the character or its the computer's ship location
                if (((int)grid[i][j] == 0) || (grid[i][j]=='#') ){
                    System.out.print(" |");
                }
                else {
                    System.out.print(grid[i][j]+"|");
                }
            }
            System.out.println();
        }
    }

    public static void print_footer(int userShips, int computerShips){
        System.out.print("Your ships: " + userShips);
        System.out.println(" | Computer ships: "+computerShips);
    }


}


