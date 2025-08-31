

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
        int h = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]);
        var arr = new int[h][w];
        var dp = new int[h][w];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < w; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        dp[0][0] = arr[0][0];
        for (int i = 1; i < h; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }

        for (int i = 1; i < w; i++) {
            dp[0][i] = dp[0][i - 1] + arr[0][i];
        }

        for (int i = 1; i < h; i++) {
            for (int j = 1; j < w; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], Math.max(dp[i - 1][j], dp[i - 1][j - 1])) + arr[i][j];
            }
        }

        bw.write(dp[h - 1][w - 1] + "\n");
        bw.flush();
    }
}
