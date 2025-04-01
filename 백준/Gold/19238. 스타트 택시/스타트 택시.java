

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        n = Integer.parseInt(numInputs[0]);
        int m = Integer.parseInt(numInputs[1]), fuel = Integer.parseInt(numInputs[2]);
        var arr = new Integer[n][n];
        var map = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                var num = Integer.parseInt(inputs[j]);

                if (num == 0) {
                    arr[i][j] = 0;
                }
            }
        }

        var startInputs = br.readLine().split(" ");
        int startY = Integer.parseInt(startInputs[0]) - 1, startX = Integer.parseInt(startInputs[1]) - 1;

        for (int i = 1; i <= m; i++) {
            var inputs = br.readLine().split(" ");
            int fy = Integer.parseInt(inputs[0]), fx = Integer.parseInt(inputs[1]),
                    sy = Integer.parseInt(inputs[2]), sx = Integer.parseInt(inputs[3]);

            arr[fy - 1][fx - 1] = i;

            var key = (sy - 1) * n + (sx - 1);
            if (!map.containsKey(key)) {
                map.put(key, new HashSet<>());
            }
            map.get(key).add(i);
        }

        var success = true;
        for (int k = 0; k < m; k++) {
            var q = new LinkedList<int[]>();
            q.add(new int[]{startY, startX});
            var visited = new boolean[n][n];
            visited[startY][startX] = true;

            var dist = 0;
            var endPoints = new ArrayList<int[]>();
            while (!q.isEmpty()) {
                var size = q.size();

                for (int i = 0; i < size; i++) {
                    var now = q.poll();
                    int y = now[0], x = now[1];

                    if (arr[y][x] > 0) {
                        endPoints.add(new int[]{y, x});
                        continue;
                    }

                    for (int j = 0; j < 4; j++) {
                        int ny = y + dy[j], nx = x + dx[j];

                        if (isValid(ny, nx) && !visited[ny][nx] && arr[ny][nx] != null) {
                            visited[ny][nx] = true;
                            q.add(new int[]{ny, nx});
                        }
                    }
                }

                if (!endPoints.isEmpty()) {
                    break;
                }

                dist++;
            }

            fuel -= dist;
            dist = 0;

            if (fuel < 0 || endPoints.isEmpty()) {
                success = false;
                break;
            }

            endPoints.sort((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

            startY = endPoints.get(0)[0];
            startX = endPoints.get(0)[1];
            var endNum = arr[startY][startX];
            arr[startY][startX] = 0;

            q.clear();
            q.add(new int[]{startY, startX});
            visited = new boolean[n][n];
            visited[startY][startX] = true;

            var endSuccess = false;
            Loop:
            while (!q.isEmpty()) {
                var size = q.size();

                for (int i = 0; i < size; i++) {
                    var now = q.poll();
                    int y = now[0], x = now[1], key = y * n + x;

                    if (map.getOrDefault(key, new HashSet<>()).contains(endNum)) {
                        startY = y;
                        startX = x;
                        endSuccess = true;
                        break Loop;
                    }

                    for (int j = 0; j < 4; j++) {
                        int ny = y + dy[j], nx = x + dx[j];

                        if (isValid(ny, nx) && !visited[ny][nx] && arr[ny][nx] != null) {
                            visited[ny][nx] = true;
                            q.add(new int[]{ny, nx});
                        }
                    }
                }

                dist++;
            }

            fuel -= dist;
            if (fuel < 0 || !endSuccess) {
                success = false;
                break;
            }
            fuel += dist * 2;
        }

        if (success) {
            bw.write(fuel + "\n");
        } else {
            bw.write("-1");
        }

        bw.flush();
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}

