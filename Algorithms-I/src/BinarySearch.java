/**
 * Created by arash on 2016-11-06.
 */
import edu.princeton.cs.algs4.StdOut;
public class BinarySearch
{
    public static int rank(int key, int[] a)
    { // Array must be sorted.
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi)
        { // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    public static int recursiveRank(int key, int[] arr, int low, int high, int depth){
        //Check right at the start if the numbers are too big or too small
        if (key < arr[low] || key > arr[high-1]) return -1;
        //Assume that arr is sorted
        int mid = low + (high - low) / 2;

        //Trace prints here
        for(int i = 0;i < depth; i++){
            StdOut.print("    ");
        }
        StdOut.println(low + " " + mid + " " + high);

        if (key == arr[mid]) return mid;
        else if (high - low == 1) return -1;
        else if (key < arr[mid]) return recursiveRank(key, arr, low, mid - 1, depth + 1); //high = mid - 1
        else if (key > arr[mid]) return recursiveRank(key, arr, mid + 1, high, depth + 1); //low = mid + 1
        return -1;
    }
}
