import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};
    static int c, r;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        c = Integer.parseInt(inputs[0]);
        r = Integer.parseInt(inputs[1]);
        var isWall = new boolean[c][r];
        var arr = new int[c][r];
        var map = new HashMap<Integer, Integer>();

        for (int i = 0; i < c; i++) {
            var nums = br.readLine().split("");

            for (int j = 0; j < r; j++) {
                isWall[i][j] = nums[j].equals("1");
            }
        }

        int id = 1;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (!isWall[i][j] && arr[i][j] == 0) {
                    var landSize = setLandAndGetSize(arr, isWall, i, j, id);
                    map.put(id++, landSize);
                }
            }
        }

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (isWall[i][j]) {
                    bw.write(get4LandSizeSum(arr, map, i, j) + "");
                } else {
                    bw.write("0");
                }
            }
            bw.write("\n");
        }

        bw.flush();
    }

    private static int get4LandSizeSum(int[][] arr, HashMap<Integer, Integer> count, int y, int x) {
        var result = 1;
        var set = new HashSet<Integer>();

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i], nx = x + dx[i];

            if (isValid(ny, nx) && !set.contains(arr[ny][nx])) {
                var id = arr[ny][nx];
                result += count.getOrDefault(id, 0);
                set.add(id);
            }
        }

        return result % 10;
    }

    private static int setLandAndGetSize(int[][] arr, boolean[][] isWall, int y, int x, int id) {
        var q = new LinkedList<int[]>();
        q.add(new int[]{y, x});
        int result = 0;

        while (!q.isEmpty()) {
            var now = q.poll();

            result++;
            arr[now[0]][now[1]] = id;

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i], nx = now[1] + dx[i];

                if (isValid(ny, nx) && arr[ny][nx] == 0 && !isWall[ny][nx]) {
                    q.add(new int[]{ny, nx});
                    arr[ny][nx] = id;
                }
            }
        }

        return result;
    }

    private static boolean isValid(int y, int x) {
        return y >= 0 && y < c && x >= 0 && x < r;
    }
}
