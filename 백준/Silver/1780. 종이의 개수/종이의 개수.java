

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[] result = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        solve(arr, 0, 0, n, n);

        for (int i = 0; i < 3; i++) {
            bw.write(result[i] + "\n");
        }
        bw.flush();
    }

    private static void solve(int[][] arr, int sy, int sx, int ey, int ex) {
        if (isAllSame(arr, sy, sx, ey, ex)) {
            result[arr[sy][sx] + 1]++;
            return;
        }

        var diff = (ey - sy) / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int nsy = sy + diff * i, nsx = sx + diff * j;
                int ney = nsy + diff, nex = nsx + diff;
                solve(arr, nsy, nsx, ney, nex);
            }
        }
    }

    private static boolean isAllSame(int[][] arr, int sy, int sx, int ey, int ex) {
        var flag = arr[sy][sx];

        for (int i = sy; i < ey; i++) {
            for (int j = sx; j < ex; j++) {
                if (arr[i][j] != flag) {
                    return false;
                }
            }
        }

        return true;
    }
}
