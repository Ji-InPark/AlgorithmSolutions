

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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
        int sy = 0, sx = 0, ey = 0, ex = 0;
        arr = new int[h][w];
        var visited = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                switch (inputs[j]) {
                    case '.':
                        break;
                    case 'g':
                        setGarbage(i, j);
                        break;
                    case 'F':
                        ey = i;
                        ex = j;
                        break;
                    case 'S':
                        sy = i;
                        sx = j;
                        break;
                }
            }
        }

        arr[ey][ex] = arr[sy][sx] = 0;

        var resultArr = new int[]{0, Integer.MAX_VALUE, Integer.MAX_VALUE};
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx, 0, 0});
        int level = 0;
        visited[sy][sx] = 0;

        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x = now[1];
                var gArr = new int[3];
                gArr[1] = now[2];
                gArr[2] = now[3];

                if (y == ey && x == ex) {
                    if (resultArr[2] > gArr[2] || resultArr[2] == gArr[2] && resultArr[1] > gArr[2]) {
                        resultArr[1] = gArr[1];
                        resultArr[2] = gArr[2];
                    }

                    continue;
                }

                for (int j = 0; j < 4; j++) {
                    int ny = y + dy[j], nx = x + dx[j];

                    if (isValid(ny, nx)) {
                        gArr[arr[ny][nx]]++;
                        var gs = getGarbageScore(gArr, level);

                        if (visited[ny][nx] > gs) {
                            visited[ny][nx] = gs;
                            q.add(new int[]{ny, nx, gArr[1], gArr[2]});
                        }

                        gArr[arr[ny][nx]]--;
                    }
                }
            }

            level++;
        }

        bw.write(resultArr[2] + " " + resultArr[1]);
        bw.flush();
    }

    private static int getGarbageScore(int[] arr, int level) {
        return arr[2] * 10000 + arr[1] * 100 + level;
    }

    private static void setGarbage(int y, int x) {
        arr[y][x] = 2;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];

            if (isValid(ny, nx)) {
                arr[ny][nx] = Math.max(arr[ny][nx], 1);
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
