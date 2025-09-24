

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

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        var arr = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine();

            for (int j = 0; j < w; j++) {
                arr[i][j] = inputs.charAt(j) == '1';
            }
        }

        var q = new LinkedList<int[]>();
        var visited = new boolean[h][w];
        for (int i = 0; i < w; i++) {
            if (!arr[0][i]) {
                q.add(new int[]{0, i});
                visited[0][i] = true;
            }
        }

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && !visited[ny][nx] && !arr[ny][nx]) {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }

        var result = false;
        for (int i = 0; i < w; i++) {
            result |= visited[h - 1][i];
        }

        bw.write(result ? "YES" : "NO");
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
