

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {


    static int[] dx = new int[]{0, -1, 0, 1};
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int count = 0, sy = 0, sx = 0;
        var arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);

                if (arr[i][j] == 9) {
                    sy = i;
                    sx = j;
                    arr[i][j] = 0;
                }

                if (arr[i][j] != 0) {
                    count++;
                }
            }
        }

        int sSize = 2, sCount = 0, result = 0;
        for (int i = 0; i < count; i++) {
            var position = bfs(arr, sy, sx, sSize);

            if (position.length == 0) {
                break;
            }

            sy = position[0];
            sx = position[1];

            arr[sy][sx] = 0;

            result += position[2];

            if (sSize == ++sCount) {
                sSize++;
                sCount = 0;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int[] bfs(int[][] arr, int sy, int sx, int sSize) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx});
        var visited = new boolean[n][n];
        visited[sy][sx] = true;

        var level = 0;
        var arrivals = new ArrayList<int[]>();
        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x = now[1];

                for (int j = 0; j < 4; j++) {
                    int ny = y + dy[j], nx = x + dx[j];

                    if (isValid(ny, nx) && !visited[ny][nx] && arr[ny][nx] <= sSize) {
                        q.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                        if (arr[ny][nx] != 0 && arr[ny][nx] < sSize) {
                            arrivals.add(new int[]{ny, nx});
                        }
                    }
                }
            }

            level++;

            if (!arrivals.isEmpty()) {
                break;
            }
        }

        if (arrivals.isEmpty()) {
            return new int[]{};
        }

        arrivals.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }

            return o1[0] - o2[0];
        });

        var resultPos = arrivals.get(0);

        return new int[]{resultPos[0], resultPos[1], level};
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}
