

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[][][] dirs = {
            {{0, 1}, {1, 0}, {0, 0}},
            {{0, 1}, {-1, 0}, {0, 0}},
            {{0, -1}, {1, 0}, {0, 0}},
            {{0, -1}, {-1, 0}, {0, 0}}};
    static int[][] arr;
    static boolean[][] visited;
    static int h, w, result = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        arr = new int[h][w];
        visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < w; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        solve(0, 0, 0);

        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(int y, int x, int score) {
        result = Math.max(result, score);

        var key = y * w + x;
        for (int k = key; k < h * w; k++) {
            int i = k / w, j = k % w;

            if (visited[i][j]) {
                continue;
            }

            for (var dir : dirs) {
                var isAllValid = true;
                for (var d : dir) {
                    int ny = i + d[0], nx = j + d[1];
                    isAllValid &= isValid(ny, nx) && !visited[ny][nx];
                }

                if (!isAllValid) {
                    continue;
                }

                var nextScore = score + arr[i][j];
                for (var d : dir) {
                    int ny = i + d[0], nx = j + d[1];
                    nextScore += arr[ny][nx];
                    visited[ny][nx] = true;
                }

                solve(i, j, nextScore);

                for (var d : dir) {
                    int ny = i + d[0], nx = j + d[1];
                    visited[ny][nx] = false;
                }
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
