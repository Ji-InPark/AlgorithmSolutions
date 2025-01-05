import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);

        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        var dp = new int[150000];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            dp[arr[i]] = 1;
            for (int j = 0; j <= k; j++) {
                if (dp[j] != Integer.MAX_VALUE) {
                    dp[j + arr[i]] = Math.min(dp[j + arr[i]], dp[j] + 1);
                }
            }
        }

        if (dp[k] == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(dp[k] + "\n");
        }

        bw.flush();
    }

}
