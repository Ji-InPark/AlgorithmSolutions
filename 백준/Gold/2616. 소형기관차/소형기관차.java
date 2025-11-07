

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var sumArr = new int[n + 1];
        var arr = new int[n + 1];
        var dp = new int[4][n + 1];

        var inputs = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        var k = Integer.parseInt(br.readLine());

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        for (int i = k; i < n; i++) {
            sumArr[i - k + 1] = sum;

            sum += arr[i] - arr[i - k];
        }

        for (int i = n - k; i < n; i++) {
            sumArr[i + 1] = sum;

            sum -= arr[i];
        }

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(Math.max(dp[i - 1][j], dp[i][j - 1]), sumArr[j]);

                if (j - k > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k] + sumArr[j]);
                }
            }
        }

        bw.write(dp[3][n] + "\n");
        bw.flush();
    }

}
