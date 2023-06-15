public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world");
        delay(200L);
        System.out.println("Gut reicht auch wieder");

    }
    private static void delay (long millis){
        try{
            Thread.sleep(millis);
        }
        catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
