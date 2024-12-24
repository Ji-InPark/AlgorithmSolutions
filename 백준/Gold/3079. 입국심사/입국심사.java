import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), max = 0;
        m = Integer.parseInt(numInputs[1]);
        var arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        long l = 0, r = Long.MAX_VALUE;

        while (l + 1 < r) {
            var mid = l + (r - l) / 2;

            if (isValid(arr, mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        bw.write(r + "\n");
        bw.flush();
    }

    private static boolean isValid(int[] arr, long target) {
        long count = 0;

        for (var num : arr) {
            count += target / num;

            if (count >= m) {
                return true;
            }
        }

        return count >= m;
    }
}
