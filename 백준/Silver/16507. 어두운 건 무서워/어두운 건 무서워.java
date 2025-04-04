

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int h = Integer.parseInt(numInputs[0]), w = Integer.parseInt(numInputs[1]), q = Integer.parseInt(numInputs[2]);
        var sums = new long[h + 2][w + 2];

        for (int i = 1; i <= h; i++) {
            var inputs = br.readLine().split(" ");
            var preSum = 0L;

            for (int j = 1; j <= w; j++) {
                var num = Long.parseLong(inputs[j - 1]);

                sums[i][j] = sums[i - 1][j] + preSum + num;
                preSum += num;
            }
        }

        for (int i = 0; i < q; i++) {
            var inputs = br.readLine().split(" ");
            int y1 = Integer.parseInt(inputs[0]), x1 = Integer.parseInt(inputs[1]);
            int y2 = Integer.parseInt(inputs[2]), x2 = Integer.parseInt(inputs[3]);

            var sum = sums[y2][x2] - sums[y1 - 1][x1 - 1];
            sum -= sums[y1 - 1][x2] - sums[0][x1 - 1];
            sum -= sums[y2][x1 - 1] - sums[y1 - 1][0];
            sum += sums[y1 - 1][x1 - 1] * 2;

            var result = sum / ((long) (y2 - y1 + 1) * (x2 - x1 + 1));
            bw.write(result + "\n");
        }

        bw.flush();
    }
}

