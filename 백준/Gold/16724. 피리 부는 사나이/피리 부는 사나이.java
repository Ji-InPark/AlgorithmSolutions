

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    static int h, w;
    static char[][] arr;
    static boolean[][] visited;
    static int[] unionArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        h = Integer.parseInt(numInputs[0]);
        w = Integer.parseInt(numInputs[1]);
        arr = new char[h][w];
        unionArr = new int[h * w];
        visited = new boolean[h][w];
        for (int i = 0; i < h * w; i++) {
            unionArr[i] = i;
        }

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().toCharArray();
            System.arraycopy(inputs, 0, arr[i], 0, w);
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                dfs(i, j, i * w + j);
            }
        }

        var result = new HashSet<Integer>();
        for (int i = 0; i < h * w; i++) {
            result.add(unionArr[i]);
        }

        bw.write(result.size() + "\n");
        bw.flush();
    }

    private static int dfs(int y, int x, int flag) {
        var key = y * w + x;
        if (visited[y][x]) {
            return unionArr[key] = Math.min(flag, unionArr[key]);
        }

        visited[y][x] = true;

        int ny = y, nx = x;
        switch (arr[y][x]) {
            case 'U':
                ny--;
                break;
            case 'D':
                ny++;
                break;
            case 'R':
                nx++;
                break;
            case 'L':
                nx--;
                break;
        }

        return unionArr[key] = dfs(ny, nx, flag);
    }
}
