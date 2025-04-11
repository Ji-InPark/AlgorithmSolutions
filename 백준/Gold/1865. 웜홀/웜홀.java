

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

        var max = 100000000;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var numInputs = br.readLine().split(" ");
            int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]),
                    w = Integer.parseInt(numInputs[2]);

            var arr = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                Arrays.fill(arr[i], max);
            }

            for (int i = 0; i < m; i++) {
                var inputs = br.readLine().split(" ");
                int start = Integer.parseInt(inputs[0]), end = Integer.parseInt(inputs[1]),
                        dist = Integer.parseInt(inputs[2]);

                arr[start][end] = Math.min(arr[start][end], dist);
                arr[end][start] = Math.min(arr[end][start], dist);
            }

            for (int i = 0; i < w; i++) {
                var inputs = br.readLine().split(" ");
                int start = Integer.parseInt(inputs[0]), end = Integer.parseInt(inputs[1]),
                        dist = Integer.parseInt(inputs[2]);

                arr[start][end] = Math.min(arr[start][end], -dist);
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (arr[i][k] == max || arr[k][j] == max) {
                            continue;
                        }

                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }

            var result = false;
            for (int i = 1; i <= n; i++) {
                result |= arr[i][i] < 0;
            }

            bw.write(result ? "YES\n" : "NO\n");
        }

        bw.flush();
    }
}

