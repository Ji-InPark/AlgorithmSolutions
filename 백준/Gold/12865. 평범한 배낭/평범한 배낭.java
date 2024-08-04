import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var weights = new int[101];
        var values = new int[101];
        var dp = new int[101][100001];
        var inputNums = br.readLine().split(" ");
        int n = Integer.parseInt(inputNums[0]), k = Integer.parseInt(inputNums[1]);

        for (int i = 1; i <= n; i++) {
            var input = br.readLine().split(" ");

            weights[i] = Integer.parseInt(input[0]);
            values[i] = Integer.parseInt(input[1]);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j - weights[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(dp[n][k] + "\n");
        bw.flush();
    }
}
