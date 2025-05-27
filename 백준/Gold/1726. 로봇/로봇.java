

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 0, 1, -1};
    static int[][] dirs = new int[][]{{0, 0}, {4, 3}, {3, 4}, {1, 2}, {2, 1}};
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        var isWall = new boolean[h][w];
        var visited = new int[h][w][5];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < w; j++) {
                isWall[i][j] = Integer.parseInt(inputs[j]) == 1;
            }
        }

        var startInput = br.readLine().split(" ");
        int sy = Integer.parseInt(startInput[0]) - 1, sx = Integer.parseInt(startInput[1]) - 1,
                sd = Integer.parseInt(startInput[2]);
        var endInput = br.readLine().split(" ");
        int ey = Integer.parseInt(endInput[0]) - 1, ex = Integer.parseInt(endInput[1]) - 1,
                ed = Integer.parseInt(endInput[2]);

        int level = 0;
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx, sd});

        Loop:
        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x = now[1], dir = now[2];

                if (ey == y && ex == x && ed == dir) {
                    break Loop;
                }

                for (int j = 0; j < 2; j++) {
                    var nd = dirs[dir][j];

                    if (visited[y][x][nd] > level) {
                        q.add(new int[]{y, x, nd});
                        visited[y][x][nd] = level;
                    }
                }

                for (int j = 1; j <= 3; j++) {
                    int ny = y + j * dy[dir], nx = x + j * dx[dir];

                    if (isValid(ny, nx)) {
                        if (isWall[ny][nx]) {
                            break;
                        }

                        if (visited[ny][nx][dir] > level) {
                            q.add(new int[]{ny, nx, dir});
                            visited[ny][nx][dir] = level;
                        }
                    }
                }
            }

            level++;
        }

        bw.write(level + "\n");
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
