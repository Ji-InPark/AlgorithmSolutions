

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    static HashMap<String, Long> map = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            var n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }

            bw.write(solve(n, 0) + "\n");
        }

        bw.flush();
    }

    private static long solve(int full, int half) {
        if (half < 0) {
            return 0;
        }

        var key = full + "-" + half;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (full == 0) {
            return 1;
        }

        var result = solve(full - 1, half + 1) + solve(full, half - 1);
        map.put(key, result);

        return result;
    }
}
