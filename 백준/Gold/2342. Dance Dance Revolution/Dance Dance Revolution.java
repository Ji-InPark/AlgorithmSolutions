import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[][] weight = {{0, 2, 2, 2, 2}, {0, 1, 3, 4, 3}, {0, 3, 1, 3, 4}, {0, 4, 3, 1, 3}, {0, 3, 4, 3, 1}};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        var arr = new int[inputs.length - 1];
        var dp = new int[arr.length][5][5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        var result = solve(0, 0, 0, arr, dp);

        bw.write(result + "\n");
        bw.flush();
    }

    private static int solve(int index, int left, int right, int[] arr, int[][][] dp) {
        if (index == arr.length) {
            return 0;
        }

        if (dp[index][left][right] != 0) {
            return dp[index][left][right];
        }

        var next = arr[index];

        return dp[index][left][right] = Math.min(
                solve(index + 1, next, right, arr, dp) + weight[left][next],
                solve(index + 1, left, next, arr, dp) + weight[right][next]
        );
    }
}