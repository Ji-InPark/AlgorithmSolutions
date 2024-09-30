import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.TreeMap;

public class Main {

    static int n;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        var arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        int id = 2;
        var map = new TreeMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    int size = bfs(arr, i, j, id++);

                    map.put(size, map.getOrDefault(size, 0) + 1);
                }
            }
        }

        bw.write(id - 2 + "\n");
        for (var entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                bw.write(entry.getKey() + "\n");
            }
        }

        bw.flush();

    }

    private static int bfs(int[][] arr, int startY, int startX, int flag) {
        int result = 0;
        var q = new LinkedList<int[]>();
        q.add(new int[]{startY, startX});
        arr[startY][startX] = flag;

        while (!q.isEmpty()) {
            var now = q.poll();
            int y = now[0], x = now[1];
            result++;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i], nx = x + dx[i];

                if (isValid(ny, nx) && arr[ny][nx] == 1) {
                    q.add(new int[]{ny, nx});
                    arr[ny][nx] = flag;
                }
            }
        }

        return result;
    }

    private static boolean isValid(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }

}
