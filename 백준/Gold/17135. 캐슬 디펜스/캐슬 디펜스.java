

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{-1, 0, 1};
    static int[] dy = new int[]{0, -1, 0};
    static int h, w, d, result = 0;
    static int[][] originalArr;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        d = Integer.parseInt(numInputs[2]);

        originalArr = new int[h][w];
        arr = new int[h][w];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                originalArr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        solve(0, new HashSet<>());

        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(int level, HashSet<Integer> set) {
        if (set.size() == 3) {
            copyArr();
            calculate(set);
            return;
        }

        if (level >= w) {
            return;
        }

        for (int i = level; i < w; i++) {
            set.add(i);
            solve(i + 1, set);
            set.remove(i);
        }
    }

    private static void calculate(HashSet<Integer> set) {
        var count = 0;
        for (int i = 0; i < h; i++) {
            var points = new ArrayList<int[]>();
            for (var num : set) {
                points.add(bfs(num));
            }

            for (var point : points) {
                int y = point[0], x = point[1];

                if (!isValid(y, x)) {
                    continue;
                }

                count += arr[y][x];
                arr[y][x] = 0;
            }
            moveForward();
        }

        result = Math.max(result, count);
    }

    private static int[] bfs(int start) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{h, start});
        var visited = new boolean[h][w];

        for (int i = 0; i <= d; i++) {
            var size = q.size();
            for (int j = 0; j < size; j++) {
                var now = q.poll();
                int y = now[0], x = now[1];

                if (isValid(y, x) && arr[y][x] == 1) {
                    return now;
                }

                for (int k = 0; k < 3; k++) {
                    int ny = y + dy[k], nx = x + dx[k];

                    if (isValid(ny, nx) && !visited[ny][nx]) {
                        q.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        return new int[]{-1, -1};
    }

    private static void moveForward() {
        for (int i = h - 1; i > 0; i--) {
            System.arraycopy(arr[i - 1], 0, arr[i], 0, w);
        }
        Arrays.fill(arr[0], 0);
    }

    private static void copyArr() {
        for (int i = 0; i < h; i++) {
            System.arraycopy(originalArr[i], 0, arr[i], 0, w);
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= x && x < w && 0 <= y && y < h;
    }
}
