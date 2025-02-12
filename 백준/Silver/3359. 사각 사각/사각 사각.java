

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < 2; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        var dp = new int[n][2];

        // 0 -> 가로, 1 -> 세로
        dp[0][0] = arr[0][1];
        dp[0][1] = arr[0][0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(
                    dp[i - 1][0] + Math.abs(arr[i - 1][0] - arr[i][0]) + arr[i][1],
                    dp[i - 1][1] + Math.abs(arr[i - 1][1] - arr[i][0]) + arr[i][1]
            );
            dp[i][1] = Math.max(
                    dp[i - 1][0] + Math.abs(arr[i - 1][0] - arr[i][1]) + arr[i][0],
                    dp[i - 1][1] + Math.abs(arr[i - 1][1] - arr[i][1]) + arr[i][0]
            );
        }

        var result = Math.max(dp[n - 1][0], dp[n - 1][1]);
        
        bw.write(result + "\n");
        bw.flush();
    }

}
