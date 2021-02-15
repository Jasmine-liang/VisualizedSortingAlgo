## MergeSort Algorithms (Another implementation)
```java
public class Test {

    public static void main(String[] args) {
    int[] arr = {6,2,3,1,9,10,15,13,12,17};
    Test.sort(arr);
    for (int i : arr){
        System.out.print(i+ " ");
    }

}
    public static void Merge(int[] left, int[] right, int[] arr){
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length){
            if (left[i] < right[j]){
                arr[k] = left[i];
                i++;
            }
            else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < left.length){
            arr[k] = left[i];
            k++; i++;
        }
        while (j < right.length){
            arr[k] = right[j];
            k++; j++;
        }

    }
    public static void sort(int[] arr){
            int n = arr.length;
            if(n < 2) return;
            int mid = n/2;
            int[] left = Arrays.copyOfRange(arr,0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, n);
            sort(left);
            sort(right);
            Merge(left, right, arr);

    }

}

```