import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    public static int n;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n], weight = new int[n][n];
        var q = new LinkedList<int[]>();
        for (int y = 0; y < n; y++) {
            var inputs = br.readLine().split(" ");

            for (int x = 0; x < n; x++) {
                arr[y][x] = Integer.parseInt(inputs[x]);
            }
        }

        for (int y = 0; y < n; y++) {
            Loop:
            for (int x = 0; x < n; x++) {
                for (int i = 0; i < 4; i++) {
                    int ny = dy[i] + y, nx = dx[i] + x;

                    if (isValid(ny, nx) && arr[y][x] > arr[ny][nx]) {
                        continue Loop;
                    }
                }

                q.add(new int[]{y, x});
            }
        }

        var result = 0;
        while (!q.isEmpty()) {
            var now = q.poll();

            var distance = dfs(arr, weight, now[0], now[1], 1);
            result = Math.max(result, distance);
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int dfs(int[][] arr, int[][] weight, int y, int x, int level) {
        if (weight[y][x] > 0) {
            return weight[y][x] + level;
        }

        var result = 0;
        for (int i = 0; i < 4; i++) {
            int ny = dy[i] + y, nx = dx[i] + x;

            if (isValid(ny, nx) && arr[ny][nx] > arr[y][x]) {
                var distance = dfs(arr, weight, ny, nx, level + 1);
                result = Math.max(result, distance);
            }
        }

        if (result == 0) {
            return level;
        }

        weight[y][x] = result - level;
        return result;
    }


    private static boolean isValid(int y, int x) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
