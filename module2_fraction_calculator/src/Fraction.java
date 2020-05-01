import java.util.InputMismatchException;

public class Fraction {
    private int numerator, denominator;

    //Constructor with two parameters
    public Fraction (int numerator, int denominator){
            if(denominator==0){
                throw new IllegalArgumentException();
            }

            if(denominator<0) {
                numerator=numerator*-1;
            }



            this.numerator = numerator;
            this.denominator = denominator;
    }

    //Constructor with one parameter
    public Fraction(int numerator){
        this(numerator, 1);
    }

    //Constructor with no parameters
    public Fraction(){
        this(0);
    }

    public int getNumerator(){
        return this.numerator;
    }

    public int getDenominator(){
        return this.denominator;
    }

    public String toString(){
        String fractionConvertedToString;
        if(this.denominator>1){
            fractionConvertedToString = this.numerator + "/" + this.denominator;
        }else{
            fractionConvertedToString = Integer.toString(this.numerator);
        }

        return fractionConvertedToString;
    }

    public Double toDouble(){
        double fractionResult = this.numerator / this.denominator;
        return fractionResult;
    }

    //**************************** OPERATIONS ON FRACTIONS ***********************************

    public Fraction add(Fraction other){
        int returnFractionNumerator=0,returnFractionDenominator;
        returnFractionDenominator = this.denominator*other.denominator/
                gcd(this.denominator,other.denominator);


        returnFractionNumerator = returnFractionDenominator/this.denominator*this.numerator +
                                  returnFractionDenominator/other.denominator*other.numerator;

        Fraction returnFraction = new Fraction(returnFractionNumerator,returnFractionDenominator);
        returnFraction.toLowestTerms();
        return returnFraction;
    }

    //Pretty much the same as the add method, the only difference is that you subtract (of course)
    public Fraction subtract(Fraction other){
        int returnFractionNumerator,returnFractionDenominator;
        returnFractionDenominator = this.denominator*other.denominator/
                gcd(this.denominator,other.denominator);

        System.out.println("this.denominator: "+this.denominator);
        System.out.println("other.denominator: "+other.denominator);
        System.out.println("returnFractoinDenominator: "+returnFractionDenominator);

        returnFractionNumerator = returnFractionDenominator/this.denominator*this.numerator -
                returnFractionDenominator/other.denominator*other.numerator;

        Fraction returnFraction = new Fraction(returnFractionNumerator,returnFractionDenominator);
        returnFraction.toLowestTerms();

        return returnFraction;
    }

    public Fraction divide(Fraction other){
        int returnFractionNumerator,returnFractionDenominator;

        returnFractionNumerator = this.numerator * other.denominator;
        returnFractionDenominator = this.denominator * other.numerator;

        Fraction returnFraction = new Fraction(returnFractionNumerator, returnFractionDenominator);
        returnFraction.toLowestTerms();

        return returnFraction;
    }

    public Fraction multiply(Fraction other){
        int returnFractionNumerator, returnFractionDenominator;

        returnFractionNumerator = this.numerator * other.numerator;
        returnFractionDenominator = this.denominator * other.denominator;

        Fraction returnFraction = new Fraction(returnFractionNumerator, returnFractionDenominator);
        returnFraction.toLowestTerms();

        return returnFraction;
    }



    //********************************************************************************************



    //Least common multiple
    public static int lcm(int a, int b){
        int lcm;
        lcm = (a*b)/(gcd(a,b));
        return lcm;

    }

    //Greatest common divisor
    public static int gcd(int numerator, int denominator){
        int remainder;
        while((numerator!=0) && (denominator!=0)){
            remainder = numerator%denominator;
            numerator = denominator;
            denominator = remainder;
        }

        return numerator;
    }

    public Fraction toLowestTerms(){
        int gcdResult = gcd(this.numerator, this.denominator);

        this.denominator = this.denominator/gcdResult;
        this.numerator = this.numerator/gcdResult;

        return this;
    }

    //Must take in an "Object" to properly override the Object class's equals method
    public boolean equals(Object other){
        boolean returnBoolean = false;
        if((other == null) || (other.getClass() != this.getClass())){
            throw new InputMismatchException("Fraction expected.");
        } else{
            Fraction otherFraction = ((Fraction) other);
            otherFraction.toLowestTerms();
            Fraction thisLowestTerms = this.toLowestTerms();

            if((otherFraction.getDenominator()==thisLowestTerms.getDenominator()) &&
                    (otherFraction.getNumerator()==thisLowestTerms.getNumerator())){
                returnBoolean = true;
            }
        }
        return returnBoolean;
    }








}
