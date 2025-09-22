

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
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);
        var arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        long left = 0, right = 2000000000;

        while (left + 1 < right) {
            var mid = left + (right - left) / 2;

            if (isValid(arr, mid, m)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        var count = calCount(arr, left);
        var result = 0L;
        for (int i = 0; i < n; i++) {
            result += arr[i] % left;
        }
        result += Math.max(0, count - m) * left;

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isValid(long[] arr, long size, long m) {
        var count = calCount(arr, size);

        return count >= m;
    }

    private static long calCount(long[] arr, long size) {
        var count = 0L;
        for (var num : arr) {
            count += num / size;
        }

        return count;
    }
}
