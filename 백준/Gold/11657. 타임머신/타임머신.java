

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

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]), max = 10000000;
        var arr = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(arr[i], max);
        }

        for (int i = 1; i <= n; i++) {
            arr[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int start = Integer.parseInt(inputs[0]), end = Integer.parseInt(inputs[1]),
                    time = Integer.parseInt(inputs[2]);

            arr[start][end] = Math.min(arr[start][end], time);
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

        for (int i = 1; i <= n; i++) {
            if (arr[1][i] != max && arr[i][i] < 0) {
                bw.write("-1");
                bw.flush();
                return;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (arr[1][i] == max) {
                bw.write("-1\n");
            } else {
                bw.write(arr[1][i] + "\n");
            }
        }

        bw.flush();
    }
}

