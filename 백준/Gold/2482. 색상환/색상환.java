

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
        var k = Integer.parseInt(br.readLine());

        var mod = 1000000003;

        var dp = new long[1005][1005];
        for (int i = 4; i <= n; i++) {
            dp[i][1] = i;
        }
        dp[4][2] = 2;
        dp[5][2] = 5;

        for (int i = 6; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % mod;
            }
        }

        bw.write(dp[n][k] + "\n");
        bw.flush();
    }
}

