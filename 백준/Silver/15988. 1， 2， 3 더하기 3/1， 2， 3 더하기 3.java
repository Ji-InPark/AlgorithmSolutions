import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), max = 0;
        var nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            max = Math.max(nums[i], max);
        }

        var dp = new int[max + 1];
        dp[0] = 1;
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i - j < 0) {
                    break;
                }

                dp[i] += dp[i - j];
                dp[i] %= 1000000009;
            }
        }

        for (var num : nums) {
            bw.write(dp[num] + "\n");
        }

        bw.flush();
    }
}