

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);
        var arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int low = 0, high = Integer.MAX_VALUE;

        while (low + 1 < high) {
            var mid = low + (high - low) / 2;

            if (isValid(arr, mid, k)) {
                low = mid;
            } else {
                high = mid;
            }
        }

        bw.write(low + "\n");
        bw.flush();
    }

    public static boolean isValid(int[] arr, int target, int k) {
        long count = 0;

        for (var num : arr) {
            count += Math.max(target - num, 0);
        }

        return count <= k;
    }
}
