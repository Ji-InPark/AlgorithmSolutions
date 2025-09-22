

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int[][] arr, resultArr;
    static int r;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int h = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]);
        r = Integer.parseInt(numInputs[2]);

        arr = new int[h][w];
        resultArr = new int[h][w];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < w; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        solve(h, w, 0);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                bw.write(resultArr[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.flush();
    }

    private static void solve(int h, int w, int depth) {
        if (Math.min(h, w) < 2) {
            return;
        }

        int size = 2 * h + 2 * w - 4;
        var nextPos = findNextPositions(depth, depth, h, w, depth, 0, 0);
        int curY = nextPos[0], curX = nextPos[1], curDir = nextPos[2];
        var resPos = findNextPositions(curY, curX, h, w, depth, curDir, r % size);
        int resY = resPos[0], resX = resPos[1], resDir = resPos[2];

        for (int i = 0; i < size; i++) {
            resultArr[resY][resX] = arr[curY][curX];

            nextPos = findNextPositions(curY, curX, h, w, depth, curDir, 1);
            curY = nextPos[0];
            curX = nextPos[1];
            curDir = nextPos[2];

            resPos = findNextPositions(resY, resX, h, w, depth, resDir, 1);
            resY = resPos[0];
            resX = resPos[1];
            resDir = resPos[2];
        }

        solve(h - 2, w - 2, depth + 1);
    }

    private static int[] findNextPositions(int y, int x, int h, int w, int depth, int dir, int count) {
        while (count > 0) {
            int ny = y + dy[dir], nx = x + dx[dir];

            if (!isValid(ny, nx, h, w, depth)) {
                dir = (dir + 1) % 4;
                continue;
            }

            y = ny;
            x = nx;
            count--;
        }

        return new int[]{y, x, dir};
    }

    private static boolean isValid(int y, int x, int h, int w, int depth) {
        int ey = depth + h, ex = depth + w;
        return depth <= y && y < ey && depth <= x && x < ex;
    }
}
