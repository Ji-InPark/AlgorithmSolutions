

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int n;
    static int[][] origin, copy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        n = (int) Math.pow(2, Integer.parseInt(numInputs[0]));

        origin = new int[n][n];
        copy = new int[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                origin[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        var inputs = br.readLine().split(" ");

        for (String input : inputs) {
            fireStorm((int) Math.pow(2, Integer.parseInt(input)));
        }

        bw.write(sumOfIce() + "\n");
        bw.write(findBiggestIce() + "\n");

        bw.flush();
    }

    private static int sumOfIce() {
        var result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += origin[i][j];
            }
        }

        return result;
    }

    private static int findBiggestIce() {
        var max = 0;
        var visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && origin[i][j] > 0) {
                    max = Math.max(max, bfs(i, j, visited));
                }
            }
        }

        return max;
    }


    private static int bfs(int sy, int sx, boolean[][] visited) {
        var result = 1;
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx});
        visited[sy][sx] = true;

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && !visited[ny][nx] && origin[ny][nx] > 0) {
                    result++;
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }

        }

        return result;
    }

    private static void fireStorm(int level) {
        if (level > 1) {
            for (int i = 0; i < n; i += level) {
                for (int j = 0; j < n; j += level) {
                    rotate(i, j, level);
                }
            }

            copyToOrigin();
        }

        meltIce();
    }

    private static void meltIce() {
        var positions = new ArrayList<int[]>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (origin[i][j] > 0) {
                    if (isMelting(i, j)) {
                        positions.add(new int[]{i, j});
                    }
                }
            }
        }

        for (var p : positions) {
            origin[p[0]][p[1]]--;
        }
    }

    private static boolean isMelting(int sy, int sx) {
        var count = 0;
        for (int i = 0; i < 4; i++) {
            int ny = sy + dy[i], nx = sx + dx[i];

            if (isValid(ny, nx) && origin[ny][nx] > 0) {
                count++;
            }
        }

        return count < 3;
    }

    private static void copyToOrigin() {
        for (int i = 0; i < n; i++) {
            System.arraycopy(copy[i], 0, origin[i], 0, n);
        }
    }

    private static void rotate(int sy, int sx, int level) {
        if (level <= 1) {
            return;
        }

        int oy = sy, ox = sx, cy = sy, cx = sx + level - 1, od = 0, cd = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < level - 1; j++) {
                oy += dy[od];
                ox += dx[od];
                cy += dy[cd];
                cx += dx[cd];

                copy[cy][cx] = origin[oy][ox];
            }
            od = (od + 1) % 4;
            cd = (cd + 1) % 4;
        }

        rotate(sy + 1, sx + 1, level - 2);
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}
