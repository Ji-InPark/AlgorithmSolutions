

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
        int n = Integer.parseInt(numInputs[0]), t = Integer.parseInt(numInputs[1]);
        var arr = new int[n + 1][2];
        var dp = new int[t + 1][n + 1];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            arr[i + 1][0] = Integer.parseInt(inputs[0]);
            arr[i + 1][1] = Integer.parseInt(inputs[1]);
        }

        for (int i = 1; i <= t; i++) {
            for (int j = 1; j <= n; j++) {
                if (i - arr[j][0] < 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j- 1]);
                    continue;
                }

                dp[i][j] = Math.max(dp[i - arr[j][0]][j - 1] + arr[j][1], Math.max(dp[i - 1][j], dp[i][j- 1]));
            }
        }

        bw.write(dp[t][n] + "\n");
        bw.flush();
    }
}
