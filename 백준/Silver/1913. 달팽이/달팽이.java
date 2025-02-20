

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[] dy = new int[]{0, -1, 0, 1};
    static int[] dx = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var target = Integer.parseInt(br.readLine());
        var arr = new int[n][n];

        int y = n - 1, x = 0, num = n * n, dIndex = 0, resultY = 0, resultX = 0;

        for (int i = 0; i < n; i++) {
            arr[i][0] = num--;
        }

        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < i; k++) {
                    y += dy[dIndex];
                    x += dx[dIndex];
                    arr[y][x] = num--;
                }
                dIndex = (dIndex + 1) % 4;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(arr[i][j] + " ");
                if (arr[i][j] == target) {
                    resultY = i + 1;
                    resultX = j + 1;
                }
            }
            bw.write("\n");
        }
        bw.write(resultY + " " + resultX);
        bw.flush();
    }

}
