

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
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);
        var binary = 1;
        while (binary < n) {
            binary *= 2;
        }

        var result = Math.abs(binary - n);
        for (int i = 0; i < k; i++) {
            while (n < binary) {
                binary /= 2;
            }

            n -= binary;
        }

        if (n == 0) {
            bw.write(n + "\n");
        } else {
            result = Math.min(result, binary - n);
            bw.write(result + "\n");
        }

        bw.flush();
    }
}

