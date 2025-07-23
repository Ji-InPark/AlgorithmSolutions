

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int h, w;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        var visited = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine();

            for (int j = 0; j < w; j++) {
                visited[i][j] = inputs.charAt(j) == 'x';
            }
        }

        var result = 0;
        for (int i = 0; i < h; i++) {
            if (dfs(visited, i, 0)) {
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean dfs(boolean[][] visited, int y, int x) {
        if (x == w - 1) {
            visited[y][x] = true;
            return true;
        }

        for (int i = -1; i <= 1; i++) {
            int ny = y + i, nx = x + 1;

            if (isValid(ny, nx) && !visited[ny][nx]) {
                visited[y][x] = true;
                if (dfs(visited, ny, nx)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
