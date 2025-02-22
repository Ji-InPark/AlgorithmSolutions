

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        var map = new HashMap<Integer, Integer>();
        var result = 0;

        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(inputs[i]);

            if (map.getOrDefault(num, 0) >= 2) {
                continue;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
            result++;
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
