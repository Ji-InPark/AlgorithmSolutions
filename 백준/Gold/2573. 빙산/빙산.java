

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int h, w;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[][] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);

        arr = new int[h][w];
        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        var result = 0;
        while (true) {
            result++;
            var counts = new int[h][w];
            var visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (arr[i][j] > 0) {
                        for (int k = 0; k < 4; k++) {
                            int ny = i + dy[k], nx = j + dx[k];

                            if (isValid(ny, nx) && arr[ny][nx] == 0) {
                                counts[i][j]++;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Math.max(0, arr[i][j] - counts[i][j]);
                }
            }

            var count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (arr[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j, visited);
                        count++;
                    }
                }
            }

            if (count == 0) {
                result = 0;
                break;
            } else if (count > 1) {
                break;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static void bfs(int sy, int sx, boolean[][] visited) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && arr[ny][nx] > 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
