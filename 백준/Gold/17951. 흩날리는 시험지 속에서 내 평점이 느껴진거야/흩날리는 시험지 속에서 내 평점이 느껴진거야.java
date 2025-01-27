

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);

        var arr = new int[n];
        var inputs = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        int l = 0, r = 2000005;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;

            if (isPossible(arr, mid, k)) {
                l = mid;
            } else {
                r = mid;
            }
        }

        bw.write(l + "\n");
        bw.flush();
    }

    private static boolean isPossible(int[] arr, int targetSum, int targetCount) {
        int sum = 0, count = 0;
        for (var num : arr) {
            if (sum >= targetSum) {
                sum = 0;
                count++;
            }
            sum += num;
        }

        if (sum >= targetSum) {
            count++;
        }

        return count >= targetCount;
    }
}
