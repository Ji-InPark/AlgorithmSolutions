import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        n = Integer.parseInt(numInputs[0]);

        var arr = new int[n][n];
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);

                if (arr[i][j] != 0) {
                    pq.add(new int[]{arr[i][j], i, j});
                }
            }
        }

        numInputs = br.readLine().split(" ");
        int s = Integer.parseInt(numInputs[0]), x = Integer.parseInt(numInputs[1]), y = Integer.parseInt(numInputs[2]);

        for (int i = 0; i < s; i++) {
            var size = pq.size();
            var nextList = new ArrayList<int[]>();

            for (int j = 0; j < size; j++) {
                var now = pq.poll();
                int nowX = now[1], nowY = now[2];

                for (int k = 0; k < 4; k++) {
                    int nextX = nowX + dx[k], nextY = nowY + dy[k];

                    if (isValid(nextX, nextY) && arr[nextX][nextY] == 0) {
                        arr[nextX][nextY] = now[0];
                        nextList.add(new int[]{now[0], nextX, nextY});
                    }
                }
            }

            pq.addAll(nextList);
        }

        bw.write(arr[x - 1][y - 1] + "\n");
        bw.flush();
    }

    private static boolean isValid(int x, int y) {
        return x < n && x >= 0 && y < n && y >= 0;
    }
}
