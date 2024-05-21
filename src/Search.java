public class Search {
    // Binary Search
    public static int binarySearch(int[] arr, int target) {
        Sorting.heapSort(arr);
        int low = 0;
        int high = arr.length - 1;
//        int iteration = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
//            System.out.println("Iteration: " + iteration);
//            System.out.println("The low is: " + low);
//            System.out.println("The high is: " + high);
//            System.out.println("The mid is: " + mid);
//            System.out.println("Current element is: " + arr[mid]);
//            System.out.println("-----------------------------------");
//            iteration++;

            if (arr[mid] == target) {
                return mid;
            }
            if (target > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

//        System.out.println("After not found: ");
//        System.out.println("The low is: " + low);
//        System.out.println("The high is: " + high);
        return -1;
    }


    // Linear Search
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
