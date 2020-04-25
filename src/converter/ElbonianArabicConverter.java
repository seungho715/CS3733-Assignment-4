package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException Thrown if the value is an Elbonian number that does not conform
     * to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     * in the Elbonian number system.
     */
    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // TODO check to see if the number is valid, then set it equal to the string
        try{
            FormArabic(number);
        }catch (NumberFormatException e){
            FormElbonian(number);
        }
        this.number = number;
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        // TODO Fill in the method's body
        try{
            return Integer.parseInt(this.number);
        }catch(NumberFormatException e){
            int toReturn = 0;
            for(int i=0; i<this.number.length();i++){
                char c = this.number.charAt(i);
                switch(c){
                    case 'M':
                        toReturn += 1000;
                        break;
                    case 'C':
                        toReturn += 100;
                        break;
                    case 'X':
                        toReturn += 10;
                        break;
                    case 'I':
                        toReturn += 1;
                        break;
                    case 'L':
                        toReturn += 50;
                        break;
                    case 'l':
                        i++;
                        toReturn += 40;
                        break;
                    case 'D':
                        toReturn += 500;
                        break;
                    case 'd':
                        toReturn += 400;
                        i++;
                        break;
                    case 'V':
                        toReturn += 5;
                        break;
                    case 'v':
                        toReturn += 4;
                        i++;
                        break;
                }
            }
            return toReturn;
        }
    }

    private void FormArabic(String s) throws ValueOutOfBoundsException {
        int num = Integer.parseInt(s);
        if((0<=num) && (num<=4998)){

        }
        else{
            throw new ValueOutOfBoundsException("Input: "+ s + " is not between the range");
        }
    }

    private void FormElbonian(String s) throws MalformedNumberException {
        s = s.trim();
        String Upper = s.toUpperCase();
        for(Character c: Upper.toCharArray()){
            if(!(c == 'M') || (c == 'C') || (c == 'X') || (c == 'I') || (c == 'L') || (c == 'D') || (c=='V')){
                throw new MalformedNumberException(("Input: "+s+" contains illegal characters"));
            }
        }
        EfirstRule(s);
        ESecondRule(s);
        EThirdRule(s);
    }

    private void EfirstRule(String S) throws MalformedNumberException{
        int M = S.length() - S.replace("M", "").length();
        int C = S.length() - S.replace("C", "").length();
        int X = S.length() - S.replace("X", "").length();
        int I = S.length() - S.replace("I", "").length();
        if( M>3 || C>3 || X>3 || I>3){ throw new MalformedNumberException("Input: "+S+" breaks rule 1");}
    }

    private void ESecondRule(String S) throws MalformedNumberException{
        S = S.toUpperCase();
        int i = 0;

        while(((i <= S.length() - 1) ? (S.charAt(i) == 'M') : false)){ i++;}
        while(((i <= S.length() - 1) ? (S.charAt(i) == 'D') : false)){ i++;}
        while(((i <= S.length() - 1) ? (S.charAt(i) == 'C') : false)){ i++;}
        while(((i <= S.length() - 1) ? (S.charAt(i) == 'L') : false)){ i++;}
        while(((i <= S.length() - 1) ? (S.charAt(i) == 'X') : false)){ i++;}
        while(((i <= S.length() - 1) ? (S.charAt(i) == 'V') : false)){ i++;}
        while(((i <= S.length() - 1) ? (S.charAt(i) == 'I') : false)){ i++;}
        if(i != S.length()) {
            throw new MalformedNumberException("Input: " + S + " breaks rule 2");
        }
    }

    private void EThirdRule(String S) throws MalformedNumberException{
        int i;
        for(i =0; i<S.length();i++){
            if(Character.isLowerCase(S.charAt(i))){
                if( (i==S.length()-1) ||
                        ((i+1<S.length()) ?
                                (!(Character.toUpperCase(S.charAt(i)) == S.charAt(i+1))) :
                                false) ||
                        ((i+2<S.length()) ?
                                (Character.toUpperCase(S.charAt(i)) == S.charAt(i+2)) :
                                false)) {
                    throw new MalformedNumberException("Input: "+S+" breaks rule 3");
                }
            }
        }
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() {
        // TODO Fill in the method's body
        int valueStored;
        try{
            valueStored = Integer.parseInt(this.number);
        }
        catch (NumberFormatException e){
            //If we get here, this.number must not be an integer.
            //Hence, it must already be Elbonian!
            return this.number;
        }

        String toReturn = new String();
        int c = 0; //so no more than three of each letter
        while((c < 3) && (valueStored >= 1000)){
            c++; //Like the language!
            valueStored -= 1000;
            toReturn += "M";
        }
        c=0;
        while((c < 3) && (valueStored >= 500)){
            c++;
            valueStored -= 500;
            toReturn += "D";
        }
        while((c < 3) && (valueStored >= 400)){
            c++;
            valueStored -= 400;
            toReturn += "dD";
        }
        c=0;
        while((c < 3) && (valueStored >= 100)){
            c++;
            valueStored -= 100;
            toReturn += "C";
        }
        c=0;
        while((c < 3) && (valueStored >= 50)){
            c++;
            valueStored -= 50;
            toReturn += "L";
        }

        while((c < 3) && (valueStored >= 40)){
            c++;
            valueStored -= 40;
            toReturn += "lL";
        }
        c=0;
        while((c < 3) && (valueStored >= 10)){
            c++;
            valueStored -= 10;
            toReturn += "X";
        }
        c=0;
        while((c < 3) && (valueStored >= 5)){
            c++;
            valueStored -= 5;
            toReturn += "V";
        }

        while((c < 3) && (valueStored >= 4)){
            c++;
            valueStored -= 4;
            toReturn += "vV";
        }
        c=0;
        while((c < 3) && (valueStored >= 1)){
            c++;
            valueStored -= 1;
            toReturn += "I";
        }

        return toReturn;
    }
}

