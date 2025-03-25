

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        w = Integer.parseInt(numInputs[0]);
        h = Integer.parseInt(numInputs[1]);
        var isBlocked = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            var input = br.readLine().split("");

            for (int j = 0; j < w; j++) {
                isBlocked[i][j] = input[j].equals("1");
            }
        }

        var visited = new int[h][w];
        for (int i = 0; i < h; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[0][0] = 0;
        
        var q = new LinkedList<int[]>();
        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1], block = now[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx)) {
                    var extraBlock = isBlocked[ny][nx] ? 1 : 0;
                    if (visited[ny][nx] > block + extraBlock) {
                        visited[ny][nx] = block + extraBlock;
                        q.add(new int[]{ny, nx, block + extraBlock});
                    }
                }
            }
        }

        bw.write(visited[h - 1][w - 1] + "\n");
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}

