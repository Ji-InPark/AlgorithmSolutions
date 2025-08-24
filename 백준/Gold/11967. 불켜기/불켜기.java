

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int n, m;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        var map = new HashMap<Integer, ArrayList<int[]>>();
        n = Integer.parseInt(numInputs[0]);
        m = Integer.parseInt(numInputs[1]);
        var isOn = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");

            int x = Integer.parseInt(inputs[0]), y = Integer.parseInt(inputs[1]);
            int nx = Integer.parseInt(inputs[2]), ny = Integer.parseInt(inputs[3]);
            int key = x * n + y;

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(new int[]{nx, ny});
        }

        int result = 1, prev = 0;
        isOn[1][1] = true;

        while (result != prev) {
            prev = result;
            var q = new LinkedList<int[]>();
            q.add(new int[]{1, 1});
            var visited = new boolean[n + 1][n + 1];
            visited[1][1] = true;

            while (!q.isEmpty()) {
                var now = q.poll();
                int x = now[0], y = now[1];
                var key = x * n + y;

                for (var next : map.getOrDefault(key, new ArrayList<>())) {
                    int nx = next[0], ny = next[1];

                    if (!isOn[nx][ny]) {
                        isOn[nx][ny] = true;
                        result++;
                    }
                }

                map.remove(key);

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i], ny = y + dy[i];

                    if (isValid(nx, ny) && !visited[nx][ny] && isOn[nx][ny]) {
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isValid(int x, int y) {
        return 0 < x && x <= n && 0 < y && y <= n;
    }
}
