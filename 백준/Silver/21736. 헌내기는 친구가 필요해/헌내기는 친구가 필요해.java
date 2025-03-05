

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int h, w;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        var arr = new char[h][w];
        var q = new LinkedList<int[]>();

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().toCharArray();

            for (int j = 0; j < w; j++) {
                arr[i][j] = inputs[j];
                if (inputs[j] == 'I') {
                    q.add(new int[]{i, j});
                }
            }
        }

        var visited = new boolean[h][w];
        var result = 0;

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];

            if (arr[y][x] == 'P') {
                result++;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && !visited[ny][nx] && arr[ny][nx] != 'X') {
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }

        if (result == 0) {
            bw.write("TT");
        } else {
            bw.write(result + "\n");
        }
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}

