import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws NumberFormatException, IOException {
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

        var result = 0;
        for (int i = 0; i <= 100; i++) {
            var visited = new boolean[n][n];
            var count = 0;

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (arr[x][y] <= i) {
                        visited[x][y] = true;
                    }
                }
            }

            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (!visited[x][y]) {
                        findIsland(x, y, n, visited);
                        count++;
                    }
                }
            }

            result = Math.max(result, count);
        }

        bw.write(result + "\n");
        bw.flush();

    }

    private static void findIsland(int x, int y, int n, boolean[][] visited) {
        if (isInValidPoint(x, y, n) || visited[x][y]) {
            return;
        }

        visited[x][y] = true;

        for (var dir : dirs) {
            findIsland(x + dir[0], y + dir[1], n, visited);
        }
    }

    private static boolean isInValidPoint(int x, int y, int n) {
        return x < 0 || y < 0 || x >= n || y >= n;
    }
}
