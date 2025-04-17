

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.HashMap;

public class Main {

    static HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            var inputs = br.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]);

            if (n == 0 && m == 0) {
                break;
            }

            bw.write(solve(n, m) + "\n");
        }

        bw.flush();
    }

    static private long solve(int n, int m) {
        var result = new BigInteger("1");
        m = Math.min(m, n - m);

        for (int i = 0; i < m; i++) {
            result = result.multiply(new BigInteger(String.valueOf(n - i)));
        }

        for (int i = 1; i <= m; i++) {
            result = result.divide(new BigInteger(String.valueOf(i)));
        }

        return result.intValue();
    }
}
