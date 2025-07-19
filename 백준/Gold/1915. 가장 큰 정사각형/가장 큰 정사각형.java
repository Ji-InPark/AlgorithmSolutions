

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
        var arr = new int[h][w];
        var cArr = new int[h][w][2];

        for (int i = 0; i < h; i++) {
            var inputs = br.readLine().toCharArray();

            for (int j = 0; j < w; j++) {
                arr[i][j] = inputs[j] == '0' ? 0 : 1;
            }
        }

        for (int i = 0; i < h; i++) {
            var count = 0;
            for (int j = 0; j < w; j++) {
                count *= arr[i][j];
                count += arr[i][j];

                cArr[i][j][0] = count;
            }
        }

        for (int i = 0; i < w; i++) {
            var count = 0;
            for (int j = 0; j < h; j++) {
                count *= arr[j][i];
                count += arr[j][i];

                cArr[j][i][1] = count;
            }
        }

        var result = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (result >= Math.min(h - i, w - j)) {
                    continue;
                }

                result = Math.max(result, calc(cArr, i, j));
            }
        }

        bw.write(result * result + "\n");
        bw.flush();
    }

    private static int calc(int[][][] arr, int y, int x) {
        int result = 0, level = 1;
        while (isValid(y, x)) {
            if (Math.min(arr[y][x][0], arr[y][x][1]) < level) {
                break;
            }
            result++;
            level++;
            y++;
            x++;
        }

        return result;
    }

    private static boolean isValid(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}
