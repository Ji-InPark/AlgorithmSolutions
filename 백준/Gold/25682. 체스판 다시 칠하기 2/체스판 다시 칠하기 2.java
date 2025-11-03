

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int n, m, k;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        n = Integer.parseInt(numInputs[0]);
        m = Integer.parseInt(numInputs[1]);
        k = Integer.parseInt(numInputs[2]);
        var arr = new boolean[n + 1][m + 1];
        var checkArr = new boolean[2][n + 1][m + 1];
        var dp = new int[2][n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            var input = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i + 1][j + 1] = input.charAt(j) == 'B';
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= m; l++) {
                    checkArr[i][j][l] = (i + j + l) % 2 == 0;
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= m; l++) {
                    dp[i][j][l] = dp[i][j][l - 1] + (arr[j][l] != checkArr[i][j][l] ? 1 : 0);
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= n; j++) {
                for (int l = 1; l <= m; l++) {
                    dp[i][j][l] += dp[i][j - 1][l];
                }
            }
        }

        var result = Integer.MAX_VALUE;

        for (int i = 0; i < 2; i++) {
            for (int j = k; j <= n; j++) {
                for (int l = k; l <= m; l++) {
                    result = Math.min(result, dp[i][j][l] + dp[i][j - k][l - k] - dp[i][j - k][l] - dp[i][j][l - k]);
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
