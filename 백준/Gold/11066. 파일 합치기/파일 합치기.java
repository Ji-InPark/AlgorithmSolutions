

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var n = Integer.parseInt(br.readLine());
            var inputs = br.readLine().split(" ");
            var dp = new int[n + 1][n + 2];
            var preSum = new int[n + 2];

            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + Integer.parseInt(inputs[i - 1]);
            }

            for (int i = 2; i <= n; i++) {
                Arrays.fill(dp[i], 2000000000);
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= n - i + 1; j++) {
                    var sum = preSum[j + i - 1] - preSum[j - 1];

                    for (int k = 1; k < i; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i - k][j + k] + sum);
                    }
                }
            }

            bw.write(dp[n][1] + "\n");
        }

        bw.flush();
    }
}