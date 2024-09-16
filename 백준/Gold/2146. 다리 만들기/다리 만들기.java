import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static boolean[][] visited;
    static int n, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        result = n * n;
        visited = new boolean[n][n];
        var arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = inputs[j].equals("1") ? 1 : 0;
            }
        }

        int landNo = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    setLand(arr, i, j, landNo++);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) {
                    bfs(arr, i, j);
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
        br.close();
        bw.close();
    }

    private static void bfs(int[][] arr, int x, int y) {
        var startLandNo = arr[x][y];
        var q = new LinkedList<Integer>();
        q.add(x);
        q.add(y);
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }

        int level = 0;
        Loop:
        while (!q.isEmpty()) {
            var size = q.size() / 2;

            for (int i = 0; i < size; i++) {
                var nowx = q.poll();
                var nowy = q.poll();

                if (arr[nowx][nowy] != 0 && arr[nowx][nowy] != startLandNo) {
                    break Loop;
                }

                visited[nowx][nowy] = true;

                for (int j = 0; j < 4; j++) {
                    int nx = nowx + dx[j], ny = nowy + dy[j];

                    if (isValidPoint(nx, ny) && !visited[nx][ny] && arr[nx][ny] != startLandNo) {
                        visited[nx][ny] = true;
                        q.add(nx);
                        q.add(ny);
                    }
                }

            }

            level++;
        }

        if (level > 1) {
            result = Math.min(result, level - 1);
        }
    }

    private static void setLand(int[][] arr, int x, int y, int landNo) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {
            var now = q.poll();

            arr[now[0]][now[1]] = landNo;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i], ny = now[1] + dy[i];

                if (isValidPoint(nx, ny) && arr[nx][ny] == 1) {
                    arr[nx][ny] = landNo;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isValidPoint(int x, int y) {
        return x < n && x >= 0 && y < n && y >= 0;
    }
}
