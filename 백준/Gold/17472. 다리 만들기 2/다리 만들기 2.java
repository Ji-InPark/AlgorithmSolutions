

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int h, w;
    static boolean[][] arr;
    static int[][] countArr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        var islandId = 1;
        var map = new HashMap<Integer, ArrayList<int[]>>();
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        arr = new boolean[h][w];
        countArr = new int[h][w];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < w; j++) {
                arr[i][j] = inputs[j].equals("1");
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (countArr[i][j] == 0 && arr[i][j]) {
                    bfs(i, j, islandId++);
                }
            }
        }

        for (int i = 1; i <= islandId; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (arr[i][j]) {
                    var bridge = searchBridge(i, j, 0, 1);

                    if (bridge == null) {
                        continue;
                    }

                    int a = bridge[0], b = bridge[1], length = bridge[2];

                    map.get(a).add(new int[]{b, length});
                    map.get(b).add(new int[]{a, length});
                }
            }
        }

        for (int j = 0; j < w; j++) {
            for (int i = 0; i < h; i++) {
                if (arr[i][j]) {
                    var bridge = searchBridge(i, j, 1, 0);

                    if (bridge == null) {
                        continue;
                    }

                    int a = bridge[0], b = bridge[1], length = bridge[2];

                    map.get(a).add(new int[]{b, length});
                    map.get(b).add(new int[]{a, length});
                }
            }
        }

        var visited = new HashSet<Integer>();
        visited.add(1);
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1]));
        pq.addAll(map.get(1));
        var result = 0;

        while (!pq.isEmpty() && visited.size() < islandId - 1) {
            var edge = pq.poll();
            int node = edge[0], length = edge[1];

            if (visited.contains(node)) {
                continue;
            }

            result += length;
            visited.add(node);
            pq.addAll(map.get(node));
        }

        if (visited.size() < islandId - 1) {
            bw.write("-1\n");
        } else {
            bw.write(result + "\n");
        }

        bw.flush();
    }

    private static void bfs(int sy, int sx, int id) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{sy, sx});
        countArr[sy][sx] = id;

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && arr[ny][nx] && countArr[ny][nx] == 0) {
                    countArr[ny][nx] = id;
                    q.add(new int[]{ny, nx});
                }
            }
        }
    }

    private static int[] searchBridge(int y, int x, int dy, int dx) {
        int length = -1, start = countArr[y][x];
        do {
            y += dy;
            x += dx;
            length++;
        } while (isValid(y, x) && !arr[y][x]);

        if (length < 2 || !isValid(y, x)) {
            return null;
        }

        int end = countArr[y][x];
        if (start == end) {
            return null;
        }

        return new int[]{start, end, length};
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
