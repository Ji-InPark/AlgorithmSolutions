

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
        if (n >= 10) {
            int index = 1, count = 0;

            while (index < n) {
                index *= 10;
                count++;
            }

            var result = 1;
            int temp = 1;
            for (int i = 0; i < count; i++) {
                temp *= 10;
                if (n % temp != 0) {
                    result = 0;
                    break;
                }
            }

            for (int i = 1; i < count; i++) {
                result += (int) (9 * Math.pow(10, i - 1) * i);
            }

            index /= 10;
            n -= index - 1;

            result += count * n;

            bw.write(result + "\n");
        } else {
            bw.write(n + "\n");
        }

        bw.flush();
    }
}

