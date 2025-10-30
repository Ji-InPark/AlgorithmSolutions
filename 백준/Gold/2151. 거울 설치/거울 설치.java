

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int n;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        var arr = new char[n][n];
        var visited = new boolean[n][n][4];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().toCharArray();

            if (n >= 0) {
                System.arraycopy(inputs, 0, arr[i], 0, n);
            }
        }

        var q = new LinkedList<int[]>();

        Loop:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '#') {
                    for (int k = 0; k < 4; k++) {
                        int y = i + dy[k], x = j + dx[k];

                        if (isValid(y, x)) {
                            q.add(new int[]{y, x, k});
                        }
                    }

                    arr[i][j] = '*';

                    break Loop;
                }
            }
        }

        int result = 0;
        Loop:
        while (true) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x = now[1], dir = now[2];

                if (arr[y][x] == '*') {
                    continue;
                }

                if (arr[y][x] == '#') {
                    break Loop;
                }

                while (true) {
                    int ny = y + dy[dir], nx = x + dx[dir];

                    if (arr[y][x] == '#') {
                        break Loop;
                    }

                    if (arr[y][x] == '!' && !visited[y][x][dir]) {
                        visited[y][x][dir] = true;
                        q.add(new int[]{y, x, dir});
                    }

                    if (!isValid(ny, nx) || arr[y][x] == '*') {
                        break;
                    }

                    y = ny;
                    x = nx;
                }
            }

            size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x = now[1], dir = now[2];

                if (arr[y][x] == '*') {
                    continue;
                }

                if (arr[y][x] == '#') {
                    break Loop;
                }

                for (int j = 1; j < 4; j += 2) {
                    int nd = (dir + j) % 4, ny = y + dy[nd], nx = x + dx[nd];

                    if (isValid(ny, nx)) {
                        q.add(new int[]{ny, nx, nd});
                    }
                }

                int ny = y + dy[dir], nx = x + dx[dir];
                if (isValid(ny, nx)) {
                    q.add(new int[]{ny, nx, dir});
                }
            }

            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}
