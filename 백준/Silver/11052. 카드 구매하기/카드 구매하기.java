import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var numInputs = br.readLine().split(" ");
        var nums = new int[n + 1];
        var dp = new int[2 * n + 1];

        for (int i = 0; i < n; i++) {
            nums[i + 1] = Integer.parseInt(numInputs[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i + j] = Math.max(dp[i + j], dp[i] + nums[j]);
            }
        }

        bw.write(dp[n] + "\n");
        bw.flush();
    }
}
