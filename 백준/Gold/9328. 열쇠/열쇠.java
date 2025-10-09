

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var numInputs = br.readLine().split(" ");
            h = Integer.parseInt(numInputs[0]);
            w = Integer.parseInt(numInputs[1]);

            var arr = new char[h][w];
            for (int i = 0; i < h; i++) {
                var input = br.readLine();

                for (int j = 0; j < w; j++) {
                    arr[i][j] = input.charAt(j);
                }
            }

            var keyInput = br.readLine();
            var keys = new HashSet<Character>();
            for (int i = 0; i < keyInput.length(); i++) {
                if (keyInput.charAt(i) == '0') {
                    break;
                }
                keys.add(keyInput.charAt(i));
            }

            var starts = findStart(arr);
            var q = findStart(arr);
            var visited = new int[h][w];
            var result = 0;

            for (int i = 0; i < h; i++) {
                Arrays.fill(visited[i], -1);
            }

            for (int i = 0; i < q.size(); i++) {
                var now = q.poll();
                int y = now[0], x = now[1];

                visited[y][x] = keys.size();

                q.add(now);
            }

            while (!q.isEmpty()) {
                var now = q.poll();
                int y = now[0], x = now[1];

                if (arr[y][x] == '$') {
                    result++;
                    arr[y][x] = '.';
                }

                if (arr[y][x] >= 97) {
                    keys.add(arr[y][x]);
                    arr[y][x] = '.';
                    q.addAll(starts);
                }

                if (arr[y][x] >= 65 && !keys.contains((char) (arr[y][x] + 32))) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i], nx = x + dx[i];

                    if (isValid(ny, nx) && visited[ny][nx] < keys.size() && arr[ny][nx] != '*') {
                        visited[ny][nx] = keys.size();
                        q.add(new int[]{ny, nx});
                    }
                }
            }
            bw.write(result + "\n");
        }

        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }

    private static LinkedList<int[]> findStart(char[][] arr) {
        var result = new LinkedList<int[]>();

        for (int i = 0; i < w; i++) {
            if (arr[0][i] != '*') {
                result.add(new int[]{0, i});
            }

            if (arr[h - 1][i] != '*') {
                result.add(new int[]{h - 1, i});
            }
        }

        for (int i = 1; i < h; i++) {
            if (arr[i][0] != '*') {
                result.add(new int[]{i, 0});
            }

            if (arr[i][w - 1] != '*') {
                result.add(new int[]{i, w - 1});
            }
        }

        return result;
    }
}
