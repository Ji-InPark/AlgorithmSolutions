

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int count = 1; ; count++) {
            var n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }

            var arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                var inputs = br.readLine().split(" ");

                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(inputs[j]);
                }
            }

            bw.write("Problem " + count + ": " + solve(arr, n) + "\n");
        }

        bw.flush();
    }

    private static int solve(int[][] arr, int n) {
        var visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[0][0] = arr[0][0];

        var q = new LinkedList<int[]>();
        q.add(new int[]{0, 0, arr[0][0]});

        while (!q.isEmpty()) {
            var now = q.poll();
            int x = now[0], y = now[1], coin = now[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (isValid(nx, ny, n) && visited[nx][ny] > coin + arr[nx][ny]) {
                    visited[nx][ny] = coin + arr[nx][ny];
                    q.add(new int[]{nx, ny, coin + arr[nx][ny]});
                }
            }
        }

        return visited[n - 1][n - 1];
    }

    private static boolean isValid(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
