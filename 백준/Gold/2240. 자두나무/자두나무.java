import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]), prev = 1, count = 0;
        var arr = new ArrayList<int[]>();

        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(br.readLine());

            if (num == prev) {
                count++;
                continue;
            }

            if (prev == 1) {
                arr.add(new int[]{count, 0});
            } else {
                arr.add(new int[]{0, count});
            }

            prev = num;
            count = 1;
        }
        if (prev == 1) {
            arr.add(new int[]{count, 0});
        } else {
            arr.add(new int[]{0, count});
        }

        var dp = new int[arr.size()][w + 1];
        dp[0][0] = arr.get(0)[0];
        dp[0][1] = arr.get(0)[1];
        for (int i = 1; i < arr.size(); i++) {
            dp[i][0] = dp[i - 1][0] + arr.get(i)[0];
        }

        for (int i = 1; i <= w; i++) {
            for (int j = i; j < arr.size(); j++) {
                dp[j][i] = Math.max(dp[j - 1][i], dp[j - 1][i - 1]) + arr.get(j)[i % 2];
            }
        }

        var result = 0;
        for (int i = 0; i <= w; i++) {
            result = Math.max(result, dp[arr.size() - 1][i]);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
