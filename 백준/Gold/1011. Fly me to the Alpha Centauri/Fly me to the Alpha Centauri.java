

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            var numInputs = br.readLine().split(" ");
            int x = Integer.parseInt(numInputs[0]), y = Integer.parseInt(numInputs[1]);
            long diff = y - x, count = 1, sum = 0, result = 0;
            var flag = true;

            do {
                sum += count;

                if (!flag) {
                    count++;
                }
                result++;
                flag = !flag;
            } while (sum < diff);

            bw.write(result + "\n");
        }

        bw.flush();
    }
}
