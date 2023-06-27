package util;

public class StringFormat {
    /**
     * gives the input-String in the given length (filed with spaces)

     */
    public static String format(String input, int length){
        String format = "%-" + length + "." + length + "s";
        return String.format(format, input);
    }
}
