

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static boolean[][] isPrincess = new boolean[5][5];
    static boolean[][] arr = new boolean[5][5];

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 5; i++) {
            var inputs = br.readLine().toCharArray();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = 'S' == inputs[j];
            }
        }

        setPrincess(0, 0, 0);

        bw.write(result + "\n");
        bw.flush();
    }

    private static void setPrincess(int index, int yCount, int level) {
        if (yCount == 4) {
            return;
        }

        if (level == 7) {
            if (checkAllConnected()) {
                result++;
            }
            return;
        }

        for (int i = index; i < 25; i++) {
            int y = i / 5, x = i % 5;
            isPrincess[y][x] = true;
            setPrincess(i + 1, yCount + (!arr[y][x] ? 1 : 0), level + 1);
            isPrincess[y][x] = false;
        }
    }

    private static boolean checkAllConnected() {
        int start = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (isPrincess[i][j]) {
                    start = i * 5 + j;
                    break;
                }
            }
        }

        var q = new LinkedList<Integer>();
        q.add(start);
        var visited = new HashSet<Integer>();
        visited.add(start);

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now / 5, x = now % 5;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];
                var next = ny * 5 + nx;

                if (isValid(ny, nx) && isPrincess[ny][nx] && !visited.contains(next)) {
                    visited.add(next);
                    q.add(next);
                }
            }
        }

        return visited.size() == 7;
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < 5 && 0 <= x && x < 5;
    }
}
