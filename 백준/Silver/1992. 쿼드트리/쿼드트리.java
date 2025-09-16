

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static char[][] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = inputs.charAt(j);
            }
        }

        var result = solve(0, 0, n);

        bw.write(result + "\n");
        bw.flush();
    }

    private static String solve(int y, int x, int level) {
        if (level == 1 || isAllSame(y, x, level)) {
            return String.valueOf(arr[y][x]);
        }

        var nextLevel = level / 2;

        return "(" +
                solve(y, x, nextLevel) +
                solve(y, x + nextLevel, nextLevel) +
                solve(y + nextLevel, x, nextLevel) +
                solve(y + nextLevel, x + nextLevel, nextLevel) +
                ")";
    }

    private static boolean isAllSame(int y, int x, int level) {
        var flag = arr[y][x];

        for (int i = y; i < y + level; i++) {
            for (int j = x; j < x + level; j++) {
                if (arr[i][j] != flag) {
                    return false;
                }
            }
        }

        return true;
    }

}
