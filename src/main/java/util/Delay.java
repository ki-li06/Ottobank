package util;

public class Delay {
    public static void delay (long millis){
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException io){
            io.printStackTrace();
        }
    }
}
