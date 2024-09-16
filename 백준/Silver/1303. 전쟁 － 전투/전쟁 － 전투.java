import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        r = Integer.parseInt(inputs[0]);
        c = Integer.parseInt(inputs[1]);
        var arr = new char[c][r];

        for (int i = 0; i < c; i++) {
            var input = br.readLine();
            for (int j = 0; j < r; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        var result = new int[2];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (arr[i][j] != ' ') {
                    var index = arr[i][j] == 'W' ? 0 : 1;
                    var count = bfs(arr, i, j);

                    result[index] += count * count;
                }
            }
        }

        bw.write(result[0] + " " + result[1]);
        bw.flush();
    }

    private static int bfs(char[][] arr, int y, int x) {
        var flag = arr[y][x];
        var q = new LinkedList<int[]>();
        q.add(new int[]{y, x});

        int count = 0;
        while (!q.isEmpty()) {
            var now = q.poll();

            arr[now[0]][now[1]] = ' ';

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i], nx = now[1] + dx[i];

                if (isValidPoint(ny, nx) && arr[ny][nx] == flag) {
                    arr[ny][nx] = ' ';
                    q.add(new int[]{ny, nx});
                }
            }

            count++;
        }

        return count;
    }

    private static boolean isValidPoint(int y, int x) {
        return y >= 0 && y < c && x >= 0 && x < r;
    }
}
