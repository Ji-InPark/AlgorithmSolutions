

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static final char WALL = '#';
    static final char WOLF = 'v';
    static final char SHEEP = 'o';
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int h, w;
    static boolean[][] visited;
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);

        arr = new char[h][w];
        visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().toCharArray();

            System.arraycopy(inputs, 0, arr[i], 0, w);
        }

        var result = new int[2];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && arr[i][j] != WALL) {
                    var bfsResult = bfs(i, j);
                    result[0] += bfsResult[0];
                    result[1] += bfsResult[1];
                }
            }
        }

        bw.write(result[0] + " " + result[1]);
        bw.flush();
    }

    private static int[] bfs(int sy, int sx) {
        var result = new int[2];
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];
            switch (arr[y][x]) {
                case SHEEP:
                    result[0]++;
                    break;
                case WOLF:
                    result[1]++;
                    break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && arr[ny][nx] != WALL && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }

        if (result[0] == 0 || result[1] == 0) {
            return result;
        } else if (result[0] > result[1]) {
            result[1] = 0;
        } else {
            result[0] = 0;
        }

        return result;
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
