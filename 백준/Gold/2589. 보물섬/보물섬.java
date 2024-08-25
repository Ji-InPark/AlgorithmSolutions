import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int[] dirX = new int[]{0, 1, 0, -1};
    static int[] dirY = new int[]{1, 0, -1, 0};
    static int m, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        n = Integer.parseInt(inputs[0]);
        m = Integer.parseInt(inputs[1]);
        var visited = new boolean[n][m];
        var arr = new char[n][m];

        for (int i = 0; i < n; i++) {
            var input = br.readLine();

            for (int j = 0; j < m; j++) {
                arr[i][j] = input.charAt(j);
                visited[i][j] = arr[i][j] == 'W';
            }
        }

        var result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }

                var length = getShortestLength(visited, i, j);
                revertVisited(visited, arr, i, j);
                result = Math.max(result, length);
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int getShortestLength(boolean[][] visited, int i, int j) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{i, j});
        visited[i][j] = true;

        int level = 0;
        while (!q.isEmpty()) {
            var size = q.size();
            for (int k = 0; k < size; k++) {
                var now = q.poll();

                for (int l = 0; l < 4; l++) {
                    if (isValidPoint(now[0] + dirX[l], now[1] + dirY[l]) &&
                            !visited[now[0] + dirX[l]][now[1] + dirY[l]]) {
                        q.add(new int[]{now[0] + dirX[l], now[1] + dirY[l]});
                        visited[now[0] + dirX[l]][now[1] + dirY[l]] = true;
                    }
                }
            }
            level++;
        }

        return level - 1;
    }

    private static void revertVisited(boolean[][] visited, char[][] arr, int i, int j) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{i, j});
        visited[i][j] = false;

        while (!q.isEmpty()) {
            var size = q.size();
            for (int k = 0; k < size; k++) {
                var now = q.poll();

                for (int l = 0; l < 4; l++) {
                    if (isValidPoint(now[0] + dirX[l], now[1] + dirY[l]) &&
                            visited[now[0] + dirX[l]][now[1] + dirY[l]] &&
                            arr[now[0] + dirX[l]][now[1] + dirY[l]] == 'L') {
                        q.add(new int[]{now[0] + dirX[l], now[1] + dirY[l]});
                        visited[now[0] + dirX[l]][now[1] + dirY[l]] = false;
                    }
                }
            }
        }
    }

    private static boolean isValidPoint(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
}
