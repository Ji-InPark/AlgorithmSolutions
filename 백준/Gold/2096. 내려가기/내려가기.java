import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var minArr = new int[n][5];
        var maxArr = new int[n][5];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                minArr[i][1 + j] = maxArr[i][1 + j] = Integer.parseInt(inputs[j]);
            }
            minArr[i][0] = minArr[i][4] = Integer.MAX_VALUE;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= 3; j++) {
                minArr[i][j] += Math.min(minArr[i - 1][j - 1], Math.min(minArr[i - 1][j], minArr[i - 1][j + 1]));
                maxArr[i][j] += Math.max(maxArr[i - 1][j - 1], Math.max(maxArr[i - 1][j], maxArr[i - 1][j + 1]));
            }
        }

        var min = Math.min(minArr[n - 1][1], Math.min(minArr[n - 1][2], minArr[n - 1][3]));
        var max = Math.max(maxArr[n - 1][1], Math.max(maxArr[n - 1][2], maxArr[n - 1][3]));

        bw.write(max + " " + min);
        bw.flush();

    }
}
