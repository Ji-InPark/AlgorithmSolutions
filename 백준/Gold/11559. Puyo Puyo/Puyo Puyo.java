

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static char[][] arr = new char[12][6];
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < 12; i++) {
            var inputs = br.readLine();
            for (int j = 0; j < 6; j++) {
                arr[i][j] = inputs.charAt(j);
            }
        }

        var result = 0;

        while (chainPuyo()) {
            goDown();
            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean chainPuyo() {
        var result = false;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] != '.' && isChain(i, j)) {
                    result = true;
                    removeChain(i, j);
                }
            }
        }

        return result;
    }

    private static void removeChain(int sy, int sx) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx});
        var flag = arr[sy][sx];
        arr[sy][sx] = '.';

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && arr[ny][nx] == flag) {
                    arr[ny][nx] = '.';
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

    private static boolean isChain(int sy, int sx) {
        var visited = new boolean[12][6];
        visited[sy][sx] = true;
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx});
        var flag = arr[sy][sx];
        var count = 1;

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && !visited[ny][nx] && arr[ny][nx] == flag) {
                    count++;
                    visited[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }

        return count >= 4;
    }


    private static void goDown() {
        for (int k = 0; k < 12; k++) {
            for (int i = 11; i >= 1; i--) {
                for (int j = 0; j < 6; j++) {
                    if (arr[i][j] == '.') {
                        arr[i][j] = arr[i - 1][j];
                        arr[i - 1][j] = '.';
                    }
                }
            }
        }

    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < 12 && 0 <= x && x < 6;
    }
}
