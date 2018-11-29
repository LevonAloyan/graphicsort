package levon_aloyan.stopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by levon on 6/11/16.
 */
public class ArrayUtil {


    public static Integer[] randomIntegerArray(int length, int n) {
        Integer[] a = new Integer[length];
        Random generator = new Random();
        for (int i = 0; i < length; i++) {
            a[i] = generator.nextInt();
        }
        return a;
    }

    public static List<Integer> createRandomList(int size, int n) {
        List<Integer> a = new ArrayList<>(size);
        Random generator = new Random();

        for (int i = 0; i < a.size(); i++) {
            a.add(generator.nextInt());

        }
        return a;
    }
}


























































