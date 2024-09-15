public class BinarySearch {
    public static void main(String[] args) {
        int[] dataSet = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int target = 7;
        int result = binarySearch(dataSet, 0, dataSet.length - 1, target);
        
        if (result == -1) {
            System.out.println("Element not found in the array.");
        } else {
            System.out.println("Element found at index: " + result);
        }
    }

    public static int binarySearch(int[] array, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        int middle = (start + end) / 2;

        if (array[middle] == target) {
            return middle;
        } else if (target < array[middle]) {
            return binarySearch(array, start, middle - 1, target);
        } else {
            return binarySearch(array, middle + 1, end, target);
        }
    }
}
