

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var numInputs = br.readLine().split(" ");
            int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);

            var st = new StringTokenizer(br.readLine(), " ");
            var arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            int minDiff = Integer.MAX_VALUE, count = 0, l = 0, r = n - 1;
            while (l < r) {
                int sum = arr[l] + arr[r];
                int diff = Math.abs(k - sum);

                if (diff < minDiff) {
                    minDiff = diff;
                    count = 1;
                } else if (diff == minDiff) {
                    count++;
                }

                if (sum > k) {
                    r--;
                } else if (sum < k) {
                    l++;
                } else {
                    l++;
                    r--;
                }
            }

            bw.write(count + "\n");
        }

        bw.flush();
    }
}
