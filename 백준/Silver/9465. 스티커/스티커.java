import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var t = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            var n = Integer.parseInt(br.readLine());
            var dp = new int[2][n + 2];

            for (int i = 0; i < 2; i++) {
                var inputNums = br.readLine().split(" ");

                for (int j = 0; j < n; j++) {
                    dp[i][j + 2] = Integer.parseInt(inputNums[j]);
                }
            }

            int result = 0;
            for (int i = 2; i < n + 2; i++) {
                dp[0][i] += Math.max(dp[1][i - 1], Math.max(dp[0][i - 2], dp[1][i - 2]));
                dp[1][i] += Math.max(dp[0][i - 1], Math.max(dp[0][i - 2], dp[1][i - 2]));

                result = Math.max(result, Math.max(dp[0][i], dp[1][i]));
            }

            bw.write(result + "\n");
        }
        bw.flush();

    }

}
