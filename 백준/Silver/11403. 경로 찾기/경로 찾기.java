

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
        var arr = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]) == 1;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] |= arr[i][k] && arr[k][j];
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(arr[i][j] ? "1 " : "0 ");
            }
            bw.write("\n");
        }

        bw.flush();
    }
}
