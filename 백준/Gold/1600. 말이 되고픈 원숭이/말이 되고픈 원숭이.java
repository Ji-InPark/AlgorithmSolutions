

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int[] dx1 = new int[]{0, 1, 0, -1};
    static int[] dy1 = new int[]{1, 0, -1, 0};

    static int[] dx2 = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy2 = new int[]{2, 1, -1, -2, -2, -1, 1, 2};

    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var k = Integer.parseInt(br.readLine());
        var numInputs = br.readLine().split(" ");
        w = Integer.parseInt(numInputs[0]);
        h = Integer.parseInt(numInputs[1]);
        var visited = new boolean[h][w][k + 1];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < w; j++) {
                for (int l = 0; l <= k; l++) {
                    visited[i][j][l] = Integer.parseInt(inputs[j]) == 1;
                }
            }
        }

        var q = new LinkedList<int[]>();
        q.add(new int[]{0, 0, k});

        int result = 0;
        var success = false;
        Loop:
        while (!q.isEmpty()) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x = now[1], remainingK = now[2];

                if (y == h - 1 && x == w - 1) {
                    success = true;
                    break Loop;
                }

                for (int j = 0; j < 4; j++) {
                    int ny = y + dy1[j], nx = x + dx1[j];

                    if (isValid(ny, nx) && !visited[ny][nx][remainingK]) {
                        visited[ny][nx][remainingK] = true;
                        q.add(new int[]{ny, nx, remainingK});
                    }
                }

                if (remainingK == 0) {
                    continue;
                }

                for (int j = 0; j < 8; j++) {
                    int ny = y + dy2[j], nx = x + dx2[j];

                    if (isValid(ny, nx) && !visited[ny][nx][remainingK - 1]) {
                        visited[ny][nx][remainingK - 1] = true;
                        q.add(new int[]{ny, nx, remainingK - 1});
                    }
                }
            }

            result++;
        }

        if (!success) {
            result = -1;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
