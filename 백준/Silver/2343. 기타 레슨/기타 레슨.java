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
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var nums = br.readLine().split(" ");
        var arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(nums[i]);
        }

        long l = 0, r = Long.MAX_VALUE;

        while (l + 1 < r) {
            long mid = l + (r - l) / 2;

            if (isValid(arr, mid, m)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        bw.write(r + "\n");
        bw.flush();
    }

    private static boolean isValid(int[] arr, long maxVolume, long maxCount) {
        long count = 0, sum = 0;

        for (var num : arr) {
            sum += num;

            if (num > maxVolume) {
                return false;
            }

            if (sum > maxVolume) {
                sum = num;
                count++;
            }
        }
        return count < maxCount;
    }
}
