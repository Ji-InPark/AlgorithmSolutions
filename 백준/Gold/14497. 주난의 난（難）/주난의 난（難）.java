

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
        numInputs = br.readLine().split(" ");
        int sy = Integer.parseInt(numInputs[0]) - 1, sx = Integer.parseInt(numInputs[1]) - 1,
                ey = Integer.parseInt(numInputs[2]) - 1, ex = Integer.parseInt(numInputs[3]) - 1;

        var arr = new int[h][w];
        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().toCharArray();

            for (int j = 0; j < w; j++) {
                arr[i][j] = inputs[j] == '1' ? 1 : 0;
            }
        }

        var result = 1;

        Loop:
        while (true) {
            var q = new LinkedList<int[]>();
            q.add(new int[]{sy, sx});
            var visited = new boolean[h][w];
            visited[sy][sx] = true;

            while (!q.isEmpty()) {
                var now = q.poll();
                int y = now[0], x = now[1];

                if (ey == y && ex == x) {
                    break Loop;
                }

                for (int j = 0; j < 4; j++) {
                    int ny = y + dy[j], nx = x + dx[j];

                    if (isValid(ny, nx) && !visited[ny][nx]) {
                        if (arr[ny][nx] == 1) {
                            arr[ny][nx] = 0;
                        } else {
                            q.add(new int[]{ny, nx});
                        }

                        visited[ny][nx] = true;
                    }
                }
            }

            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}