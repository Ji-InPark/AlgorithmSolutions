import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int h, w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            var numInputs = br.readLine().split(" ");
            h = Integer.parseInt(numInputs[0]);
            w = Integer.parseInt(numInputs[1]);

            var arr = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                var input = br.readLine();

                for (int j = 0; j < w; j++) {
                    arr[i][j] = input.charAt(j) == '#';
                }
            }

            int result = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (arr[i][j]) {
                        bfs(arr, i, j);
                        result++;
                    }
                }
            }

            bw.write(result + "\n");
        }

        bw.flush();
    }

    private static void bfs(boolean[][] arr, int startY, int startX) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{startY, startX});

        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                int y = now[0], x = now[1];

                arr[y][x] = false;

                for (int j = 0; j < 4; j++) {
                    int ny = y + dy[j], nx = x + dx[j];

                    if (isValid(ny, nx) && arr[ny][nx]) {
                        q.add(new int[]{ny, nx});
                        arr[ny][nx] = false;
                    }
                }
            }
        }
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
