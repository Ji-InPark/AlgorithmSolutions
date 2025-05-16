

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        var coinIndex = 0;
        var isWall = new boolean[h][w];
        var coins = new int[4];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().toCharArray();

            for (int j = 0; j < w; j++) {
                isWall[i][j] = inputs[j] == '#';

                if (inputs[j] == 'o') {
                    coins[coinIndex++] = i;
                    coins[coinIndex++] = j;
                }
            }
        }

        var q = new LinkedList<int[]>();
        q.add(coins);
        int result = 11, level = 1;
        Loop:
        while (!q.isEmpty() && level < 11) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y1 = now[0], x1 = now[1], y2 = now[2], x2 = now[3];

                for (int j = 0; j < 4; j++) {
                    int ny1 = y1 + dy[j], nx1 = x1 + dx[j];
                    int ny2 = y2 + dy[j], nx2 = x2 + dx[j];

                    if (!isOut(ny1, nx1) && isWall[ny1][nx1]) {
                        ny1 -= dy[j];
                        nx1 -= dx[j];
                    }

                    if (!isOut(ny2, nx2) && isWall[ny2][nx2]) {
                        ny2 -= dy[j];
                        nx2 -= dx[j];
                    }

                    if ((isOut(ny1, nx1) && !isOut(ny2, nx2)) || (!isOut(ny1, nx1) && isOut(ny2, nx2))) {
                        result = level;
                        break Loop;
                    }

                    if (isOut(ny1, nx1) || isOut(ny2, nx2)) {
                        continue;
                    }

                    if (ny1 == ny2 && nx1 == nx2) {
                        continue;
                    }

                    q.add(new int[]{ny1, nx1, ny2, nx2});
                }
            }

            level++;
        }

        if (result == 11) {
            result = -1;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isOut(int y, int x) {
        return y < 0 || y >= h || x < 0 || x >= w;
    }
}
