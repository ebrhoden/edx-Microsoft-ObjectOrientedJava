import java.util.Scanner;

public class FractionCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String wholeLine;
        String[] separatedLine;
        boolean equalOrNot = false;
        Fraction fraction1 = null, fraction2 = null, result = null;
        int testBoolean;


        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Valid operations are of the form [FRAC][OPERATION][FRAC]");

        do{
            testBoolean=-1;
            fraction1=null;
            fraction2=null;
            System.out.print("Enter an operation (q or Q to quit):");
            wholeLine = input.nextLine();
            if (!wholeLine.toLowerCase().equals("q")){
                separatedLine = getLine(wholeLine);
                if(separatedLine.length == 3){
                    //The separated string is built like [FRACTION, OPERATION, FRACTION])
                    if(validFraction(separatedLine[0])){
                        if(validFraction(separatedLine[2])){
                            //Both of them are valid
                            fraction1 = transformStringToFraction(separatedLine[0]);
                            fraction2 = transformStringToFraction(separatedLine[2]);
                        }
                        else{
                            System.out.println("Your second fraction is invalid. " +
                                    "Please enter (a/b) or (a), where a and b are integers and b is not zero");
                        }
                    }else{
                        System.out.println("At least your least your first fraction is invalid. " +
                                "Please enter (a/b) or (a), where a and b are integers and b is not zero");
                    }
                    if((fraction1!=null) && (fraction2!=null)){
                        if(validOperation(separatedLine[1])){
                            switch (separatedLine[1]) {
                                case "+":
                                    result = fraction1.add(fraction2);
                                    break;

                                case "-":
                                    result = fraction1.subtract(fraction2);
                                    break;

                                case "*":
                                    result = fraction1.multiply(fraction2);
                                    break;

                                case "/":
                                    result = fraction1.divide(fraction2);
                                    break;

                                case "=":
                                    testBoolean = 0;
                                    equalOrNot = fraction1.equals(fraction2);
                                    break;
                            }

                            if(testBoolean==0){
                                System.out.println(wholeLine + " = " + equalOrNot);

                            }else{
                                System.out.println(wholeLine + " = " + result.toString());
                            }


                        }else{
                            System.out.println("Invalid operation. Must be [FRAC][OPERATION][FRAC]");

                        }
                    }

                }else{
                    System.out.println("Invalid operation. Must be [FRAC][OPERATION][FRAC]");
                }
            }



        } while (!wholeLine.toLowerCase().equals("q"));


    }


    public static Fraction transformStringToFraction(String stringFraction){
        int numerator, denominator;
        Fraction fraction = null;

        if(stringFraction.indexOf('/')==-1) {
            numerator = Integer.parseInt(stringFraction);
            denominator=1;
        } else{
            numerator = Integer.parseInt(stringFraction.substring(0, stringFraction.indexOf('/')));
            denominator = Integer.parseInt(stringFraction.substring(stringFraction.indexOf('/')+1));

        }

        fraction = new Fraction(numerator, denominator);
        return fraction;

    }

    public static boolean validOperation(String stringFraction){
        boolean returnValue=false;

        if((stringFraction.equals("+"))||(stringFraction.equals("-"))|| (stringFraction.equals("/"))||
                (stringFraction.equals("*"))||(stringFraction.equals("="))){

            returnValue=true;
        }
        return returnValue;
    }




    public static boolean validFraction(String stringFraction){
        //The user input a denominator
        boolean returnValue = true;
        int testingPossibleIntegerNumerator, testingPossibleIntegerDenominator;

        if(stringFraction.indexOf('/')==-1){

            try {
                testingPossibleIntegerNumerator = Integer.parseInt(stringFraction);
            }
            catch (NumberFormatException e)
            {
                returnValue = false;
            }
        } else{
            try{
                testingPossibleIntegerNumerator = Integer.parseInt(stringFraction.substring(0, stringFraction.indexOf('/')));
                testingPossibleIntegerDenominator = Integer.parseInt(stringFraction.substring(stringFraction.indexOf('/')+1));

                try{
                    Fraction fraction = new Fraction(testingPossibleIntegerNumerator,
                            testingPossibleIntegerDenominator);
                }
                catch (IllegalArgumentException e){
                    System.out.println("You're dividing by ZERO in at least one of your fractions!");
                    returnValue = false;
                }


            }
            catch (NumberFormatException e)
            {
                    returnValue = false;
            }
        }

        return returnValue;
    }


    public static String[] getLine(String wholeLine) {
        String[] separatedLine;
        separatedLine = wholeLine.split(" ");
        return separatedLine;
    }
}
