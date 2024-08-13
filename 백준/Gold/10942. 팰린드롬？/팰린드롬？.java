import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var dp = new boolean[n][n];
        var inputs = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            checkPalindrome(i, i, inputs, dp);
            checkPalindrome(i - 1, i, inputs, dp);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            inputs = br.readLine().split(" ");

            int left = Integer.parseInt(inputs[0]) - 1, right = Integer.parseInt(inputs[1]) - 1;
            bw.write(dp[left][right] ? "1" : "0");
            bw.write("\n");
        }

        bw.flush();
    }

    private static void checkPalindrome(int left, int right, String[] arr, boolean[][] dp) {
        if (left < 0 || right == arr.length || !arr[left].equals(arr[right])) {
            return;
        }

        dp[left][right] = true;
        checkPalindrome(left - 1, right + 1, arr, dp);
    }
}