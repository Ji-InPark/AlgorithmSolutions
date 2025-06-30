

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class Main {

    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int n;
    static int[] arr = new int[4];
    static boolean[][] visited = new boolean[100][100];
    static double result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(inputs[i + 1]);
        }

        solve(50, 50, 0, new int[n]);

        bw.write(new BigDecimal(result) + "\n");
        bw.flush();
    }

    private static void solve(int y, int x, int index, int[] dirs) {
        if (visited[y][x]) {
            return;
        }

        if (index == n) {
            var probability = 1d;
            for (var dir : dirs) {
                probability *= (arr[dir] / 100d);
            }

            result += probability;

            return;
        }

        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];

            if (arr[i] == 0) {
                continue;
            }

            dirs[index] = i;
            solve(ny, nx, index + 1, dirs);
        }
        visited[y][x] = false;
    }

}
