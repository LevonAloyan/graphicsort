package levon_aloyan.stopWatch;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class CollectionUtil {

    public static <E> void mergeSort(List<E> list, Comparator<E> comparator) {
        Object[] array = list.toArray();
        mergeSort(array, comparator);
        ListIterator<E> iterator = list.listIterator();
        for (Object obj : array) {
            iterator.next();
            iterator.set((E) obj);
        }
    }

    private static <E> void mergeSort(Object arr[], Comparator<E> comparator) {
        int studentsLength = arr.length;

        if (studentsLength < 2) return;

        Object[] firstHalf = new Object[studentsLength / 2];
        Object[] secondHalf = new Object[studentsLength - firstHalf.length];

        System.arraycopy(arr, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(arr, firstHalf.length, secondHalf, 0, secondHalf.length);

        mergeSort(firstHalf, comparator);
        mergeSort(secondHalf, comparator);

        int iFirst = 0;
        int iSecond = 0;
        int iStudents = 0;

        while (iFirst < firstHalf.length && iSecond < secondHalf.length) {
            if (comparator.compare((E) firstHalf[iFirst], ((E) secondHalf[iSecond])) > 0) {
                arr[iStudents] = secondHalf[iSecond];
                iStudents++;
                iSecond++;
            } else {
                arr[iStudents] = firstHalf[iFirst];
                iStudents++;
                iFirst++;
            }
        }

        System.arraycopy(secondHalf, iSecond, arr, iStudents, secondHalf.length - iSecond);
        System.arraycopy(firstHalf, iFirst, arr, iStudents, firstHalf.length - iFirst);
    }


    public static <E> void bubbleSort(List<E> list, Comparator<E> comparator) {
        for (int i = list.size(); i >= 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    swap(list, j, j + 1);
                }
            }
        }
    }

    public static <E> void selectionSort(List<E> list, Comparator<E> comparator) {
        int minIndex;
        for (int i = 0; i < list.size(); i++) {
            minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (comparator.compare(list.get(minIndex), (list.get(j))) > 0) {
                    minIndex = j;
                }

            }
            swap(list, i, minIndex);

        }
    }

    public static <E> void quickSort(List<E> list, Comparator<E> comparator) {

        partition(list, comparator, 0, list.size() - 1);

    }

    private static <E> void quickSort(List<E> list, Comparator<E> comparator, int startIndex, int lastIndex) {
        partition(list, comparator, startIndex, lastIndex);
    }

    private static <E> void partition(List<E> list, Comparator<E> comparator, int startIndex, int lastIndex) {
        if (startIndex >= lastIndex) {
            return;
        }

        E pivot = list.get(startIndex);

        int i = startIndex;
        int j = lastIndex;
        for (; ; ) {
            while (comparator.compare(list.get(i), pivot) < 0) {
                i++;
            }
            while (comparator.compare(list.get(j), pivot) > 0) {
                j--;
            }

            if (i >= j) {
                break;
            }

            swap(list, i, j);
            i++;
            j--;
        }
        quickSort(list, comparator, startIndex, j);
        quickSort(list, comparator, j + 1, lastIndex);
    }

    private static Integer getPivot(Integer arr[]) {
        Integer max = arr[0];
        Integer min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        return (max + min) / 2;
    }

    private static <E> void swap(List<E> list, int first, int second) {
        E tmp;
        tmp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, tmp);


    }
}
