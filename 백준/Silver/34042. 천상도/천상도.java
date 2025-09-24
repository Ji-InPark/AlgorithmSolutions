

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);

        for (int i = 0; i < k; i++) {
            var inputs = br.readLine().split(" ");
            var arr = new int[n];
            var minusCount = 0;

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(inputs[j]);
                if (arr[j] < 0) {
                    minusCount++;
                }
            }

            Arrays.sort(arr);

            if (n == 1) {
                bw.write(arr[0] + "\n");
            } else {
                var flag = false;
                var max = 1L;
                for (int j = n - 1; j >= 0; j--) {
                    if (arr[j] <= 0) {
                        break;
                    }

                    max *= arr[j];
                    flag = true;
                }

                if (minusCount % 2 == 1) {
                    minusCount--;
                }

                for (int j = 0; j < minusCount; j++) {
                    max *= arr[j];
                    flag = true;
                }

                if (!flag) {
                    max = 0;
                }

                bw.write(max + "\n");
            }
        }
        bw.flush();
    }
}
