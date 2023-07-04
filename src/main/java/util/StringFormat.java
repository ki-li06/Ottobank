package util;


import static util.Round.round;

public class StringFormat {
    /**
     * gives the input-String in the given length (filed with spaces)

     */
    public static String format(String input, int length){
        String format = "%-" + length + "." + length + "s";
        return String.format(format, input);
    }

    /**
     * gibt einen Double ungefähr so zurück:
     * 123455.22424d   --> "123455.22"
     * 1234567.89      --> "1234567.89"
     * 123223323423.56 --> "1.23*10^11"
     */
    public static String formatDouble(double input){
        String rückgabe = "";
        if(input < 10000000){
            rückgabe = String.valueOf(round(input, 2));
        }
        else{
            int stellen = String.valueOf((int)round(input, 0)).length();
            rückgabe = String.valueOf(round(input / Math.pow(10, stellen - 1), 2));
            rückgabe += "*10^";
            rückgabe += String.valueOf(stellen-1);
        }
        return rückgabe;
    }
}
