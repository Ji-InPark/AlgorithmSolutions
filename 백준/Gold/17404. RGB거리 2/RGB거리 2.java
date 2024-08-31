import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var rgb = new int[n][3];
        var dp = new int[n][3];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            rgb[i][0] = Integer.parseInt(inputs[0]);
            rgb[i][1] = Integer.parseInt(inputs[1]);
            rgb[i][2] = Integer.parseInt(inputs[2]);
        }

        dp[0][0] = rgb[0][0];
        dp[0][1] = 1000000;
        dp[0][2] = 1000000;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
        }

        int result = Math.min(dp[n - 1][1], dp[n - 1][2]);

        dp[0][0] = 1000000;
        dp[0][1] = rgb[0][1];
        dp[0][2] = 1000000;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
        }

        result = Math.min(result, Math.min(dp[n - 1][0], dp[n - 1][2]));

        dp[0][0] = 1000000;
        dp[0][1] = 1000000;
        dp[0][2] = rgb[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
        }

        result = Math.min(result, Math.min(dp[n - 1][0], dp[n - 1][1]));

        bw.write(result + "\n");
        bw.flush();
    }
}
