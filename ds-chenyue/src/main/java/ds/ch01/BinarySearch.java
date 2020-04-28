package ds.ch01;

public class BinarySearch {

    public static int binarySearch(int[] array, int x) {
        int begin = 0, end = array.length - 1;
        while (begin <= end) {
            int middle = (begin + end) / 2;
            if (array[middle] == x) {
                return middle;
            } else if (array[middle] > x) {
                end = middle - 1;
            } else {
                begin = middle + 1;
            }
        }
        return -1;
    }

}
