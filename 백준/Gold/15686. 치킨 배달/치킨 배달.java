import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);
        var arr = new int[n][n];
        var houses = new ArrayList<int[]>();
        var chickens = new ArrayList<int[]>();

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(inputs[j]);

                if (arr[i][j] == 1) {
                    houses.add(new int[]{i, j});
                } else if (arr[i][j] == 2) {
                    chickens.add(new int[]{i, j, 0});
                }
            }
        }

        var distances = new int[houses.size()][chickens.size()];

        for (int i = 0; i < houses.size(); i++) {
            for (int j = 0; j < chickens.size(); j++) {
                distances[i][j] += Math.abs(chickens.get(j)[0] - houses.get(i)[0]) +
                        Math.abs(chickens.get(j)[1] - houses.get(i)[1]);
            }
        }

        solve(distances, 0, 0, m, new boolean[chickens.size()]);

        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(int[][] distances, int index, int count, int m, boolean[] isNotUsed) {
        if (index == distances[0].length) {
            if (count == m) {
                var distance = calculate(distances, isNotUsed);
                result = Math.min(result, distance);
            }

            return;
        }

        isNotUsed[index] = true;
        solve(distances, index + 1, count, m, isNotUsed);
        isNotUsed[index] = false;

        solve(distances, index + 1, count + 1, m, isNotUsed);
    }

    private static int calculate(int[][] distances, boolean[] isNotUsed) {
        var sum = 0;
        for (int i = 0; i < distances.length; i++) {
            var min = Integer.MAX_VALUE;
            for (int j = 0; j < distances[0].length; j++) {
                if (isNotUsed[j]) {
                    continue;
                }

                min = Math.min(min, distances[i][j]);
            }
            sum += min;
        }

        return sum;
    }
}
