package levon_aloyan.stopWatch;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PerformanceTester {
    private static double selectionSum;
    private static double mergeSum;
    private static double quickSum;
    private static double bubbleSum;
    private static StopWatch stopWatch = new StopWatch();

    public static double getSelectionSum() {
        return selectionSum;
    }

    public static double getMergeSum() {
        return mergeSum;
    }

    public static double getQuickSum() {
        return quickSum;
    }

    public static double getBubbleSum() {
        return bubbleSum;
    }

    public static List<Integer> arrayGenerator(int count, int range) {

        Random generator = new Random();
        List<Integer> list = new ArrayList<>(count);
        Integer nextInteger;
        for (int i = 0; i < count; i++) {
            nextInteger = generator.nextInt(range);
            list.add(nextInteger);
        }
        return list;
    }

    private static List<Integer> getCopy(List<Integer> original) {
        List<Integer> copy = new ArrayList<>(original);
        int i = 0;
        for (Integer current : original) {
            copy.set(i, new Integer(current));
            i++;
        }
        return copy;
    }

    private static double getElapsedTime(List<Integer> list, Method sortMethod) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        stopWatch.reset();
        stopWatch.start();
        sortMethod.invoke(CollectionUtil.class, list, new IntegerComparator());
        stopWatch.stop();
        return stopWatch.getElapsedTime();
    }

    public static void calculateAverages(int count, int size) {

        int range = count * 10;

        for (int i = 0; i < count; i++) {
            List<Integer> origin = arrayGenerator(size, range);
            List<Integer> copy;
            try {
                copy = PerformanceTester.getCopy(origin);
                bubbleSum += getElapsedTime(copy, getMethod(SortMode.BUBBLE));
                copy = PerformanceTester.getCopy(origin);
                selectionSum += getElapsedTime(copy, getMethod(SortMode.SELECTION));
                copy = PerformanceTester.getCopy(origin);
                quickSum += getElapsedTime(copy, getMethod(SortMode.QUICK));
                copy = PerformanceTester.getCopy(origin);
                mergeSum += getElapsedTime(copy, getMethod(SortMode.MERGE));


            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        bubbleSum /= count;
        selectionSum /= count;
        quickSum /= count;
        mergeSum /= count;
    }

    public static void resetSums() {
        bubbleSum = 0;
        selectionSum = 0;
        quickSum = 0;
        mergeSum = 0;
    }


    private static Method getMethod(SortMode mode) throws NoSuchMethodException {
        return CollectionUtil.class.getDeclaredMethod(mode.getMethodName(), List.class, Comparator.class);
    }

    public enum SortMode {
        BUBBLE("bubble"),
        SELECTION("selection"),
        QUICK("quick"),
        MERGE("merge");


        private String methodName;
        private String name;
        private int x;
        private int y;
        private int fontSize;

        SortMode(String name) {
            this.methodName = name + "Sort";
            this.name = name;
            fontSize = 20;
        }

        public String getMethodName() {
            return methodName;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getFontSize() {
            return fontSize;
        }

        public void setFontSize(int fontSize) {
            this.fontSize = fontSize;
        }

        public void draw(Graphics g) {
            g.drawString(name, x, y);
        }
    }
}

class IntegerComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.compare(o1, o2);
    }
}





