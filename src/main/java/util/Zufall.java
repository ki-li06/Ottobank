package util;

import java.util.Random;

public class Zufall {
    /**
     * returns a random integer
     * @param min excluded
     * @param max included
     */
    public static int RandomInt(int min, int max){
        Random r = new Random();
        return min + r.nextInt(max);
    }
}
