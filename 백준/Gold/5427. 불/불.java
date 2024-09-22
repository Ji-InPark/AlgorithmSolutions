import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    static final int BLOCK = -1000000, BLANK = 1000000;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int[][] arr;
    static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            var nums = br.readLine().split(" ");
            w = Integer.parseInt(nums[0]);
            h = Integer.parseInt(nums[1]);

            arr = new int[h][w];
            var startPoint = new int[2];
            var flames = new ArrayList<int[]>();

            for (int y = 0; y < h; y++) {
                var inputs = br.readLine().split("");
                for (int x = 0; x < w; x++) {
                    switch (inputs[x]) {
                        case "#":
                            arr[y][x] = BLOCK;
                            break;
                        case "@":
                            startPoint[0] = y;
                            startPoint[1] = x;
                            arr[y][x] = BLANK;
                            break;
                        case "*":
                            flames.add(new int[]{y, x});
                            break;
                        default:
                            arr[y][x] = BLANK;
                            break;
                    }
                }
            }

            setFlame(flames);
            var result = solve(startPoint);
            bw.write(result);
        }

        bw.flush();
    }

    private static String solve(int[] startPoint) {
        var q = new LinkedList<int[]>();
        q.add(startPoint);
        var visited = new boolean[h][w];

        var level = 1;
        while (!q.isEmpty()) {
            var size = q.size();

            for (int k = 0; k < size; k++) {
                var now = q.poll();
                int y = now[0], x = now[1];

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i], nx = x + dx[i];

                    if (isOut(ny, nx)) {
                        return level + "\n";
                    } else if (!isBlock(ny, nx) && !visited[ny][nx] && arr[ny][nx] > level) {
                        q.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }

            level++;
        }

        return "IMPOSSIBLE\n";
    }

    private static void setFlame(ArrayList<int[]> flames) {
        var q = new LinkedList<>(flames);
        var visited = new boolean[h][w];

        var level = 0;
        while (!q.isEmpty()) {
            var size = q.size();

            for (int k = 0; k < size; k++) {
                var now = q.poll();
                int y = now[0], x = now[1];

                arr[y][x] = Math.min(arr[y][x], level);

                for (int i = 0; i < 4; i++) {
                    int ny = y + dy[i], nx = x + dx[i];

                    if (!isOut(ny, nx) && !isBlock(ny, nx) && !visited[ny][nx]) {
                        q.add(new int[]{ny, nx});
                        visited[ny][nx] = true;
                    }
                }
            }

            level++;
        }
    }

    private static boolean isBlock(int y, int x) {
        return arr[y][x] == BLOCK;
    }

    private static boolean isOut(int y, int x) {
        return y < 0 || y >= h || x < 0 || x >= w;
    }
}
