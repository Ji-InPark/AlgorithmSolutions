import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var zeros = new ArrayList<Point>();

        var arr = new int[9][9];
        for (int i = 0; i < 9; i++) {
            var inputs = br.readLine().split("");
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);

                if (arr[i][j] == 0) {
                    zeros.add(new Point(i, j));
                }
            }
        }

        solve(arr, zeros, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(arr[i][j] + "");
            }
            bw.write("\n");
        }

        bw.flush();
    }

    private static boolean solve(int[][] arr, ArrayList<Point> zeros, int index) {
        if (index == zeros.size()) {
            return true;
        }

        var point = zeros.get(index);

        for (int i = 1; i <= 9; i++) {
            if (isValid(arr, point, i)) {
                arr[point.y][point.x] = i;
                if (solve(arr, zeros, index + 1)) {
                    return true;
                }
                arr[point.y][point.x] = 0;
            }
        }

        return false;
    }

    private static boolean isValid(int[][] arr, Point point, int value) {
        for (int i = 0; i < 9; i++) {
            if (arr[i][point.x] == value || arr[point.y][i] == value) {
                return false;
            }
        }

        int dy = 3 * (point.y / 3), dx = 3 * (point.x / 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[dy + i][dx + j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    static class Point {

        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
