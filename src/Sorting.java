public class Sorting {

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i  ; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    // Insertion sort
    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }


    // Selection Sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }


    // Quick Sort
    public static void quickSort(int[] arr, int start, int end) {
        if (end <= start) {return;}
        int pivot = partition(arr, start, end);
        quickSort(arr, start, pivot -1);
        quickSort(arr, pivot + 1, end);
    }
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;

        return i;
    }


    // Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int middle = arr.length / 2;
        int[] leftArr = new int[middle];
        int[] rightArr = new int[arr.length - middle];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < middle) {
                leftArr[i] = arr[i];
            } else {
                rightArr[j] = arr[i];
                j++;
            }
        }
        mergeSort(leftArr);
        mergeSort(rightArr);
        merge(leftArr, rightArr, arr);
    }
    private static void merge(int[] leftArr, int[] rightArr, int[] arr) {
        int i = 0, l = 0, r = 0;
        while (l < leftArr.length && r < rightArr.length) {
            if (leftArr[l] < rightArr[r]) {
                arr[i] = leftArr[l];
                i++;
                l++;
            } else {
                arr[i] = rightArr[r];
                i++;
                r++;
            }
        }
        while (l < leftArr.length) {
            arr[i] = leftArr[l];
            l++;
            i++;
        }
        while (r < rightArr.length) {
            arr[i] = rightArr[r];
            r++;
            i++;
        }
    }


    // Heap Sort
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n/2 - 1; i >= 0; i--) {
            heapify(arr, n,  i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr,int n, int i) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        int max = i;
        if (l < n && arr[l] > arr[max]) {
            max = l;
        }
        if (r < n && arr[r] > arr[max]) {
            max = r;
        }

        if (max != i) {
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
            heapify(arr, n , max);
        }
    }
}
