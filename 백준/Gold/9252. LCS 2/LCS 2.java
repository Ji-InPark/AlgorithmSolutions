import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input1 = br.readLine();
        var input2 = br.readLine();
        var dp = new int[input1.length() + 1][input2.length() + 1];

        for (int i = 1; i <= input1.length(); i++) {
            for (int j = 1; j <= input2.length(); j++) {
                if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        bw.write(dp[input1.length()][input2.length()] + "\n");

        var sb = new StringBuilder();

        int i = input1.length(), j = input2.length();
        while (j > 0) {
            if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else {
                sb.append(input2.charAt(j - 1));
                i--;
                j--;
            }
        }

        bw.write(sb.reverse().toString());
        bw.flush();
    }
}
