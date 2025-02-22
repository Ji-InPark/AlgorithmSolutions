

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);
        var map = new TreeMap<Integer, Integer>();

        var inputs = br.readLine().split(" ");
        int result = 0;
        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(inputs[i]);

            if (num >= k) {
                result++;
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        while (true) {
            var firstEntry = map.firstEntry();
            var lastEntry = map.lastEntry();
            if (firstEntry == null || lastEntry == null) {
                break;
            }

            if (firstEntry.getKey() == lastEntry.getKey()) {
                if (firstEntry.getKey() + lastEntry.getKey() >= k) {
                    result += firstEntry.getValue() / 2;
                }

                break;
            }

            if (firstEntry.getValue() > 1) {
                map.put(firstEntry.getKey(), firstEntry.getValue() - 1);
            } else {
                map.remove(firstEntry.getKey());
            }

            if (firstEntry.getKey() + lastEntry.getKey() < k) {
                continue;
            }

            if (lastEntry.getValue() > 1) {
                map.put(lastEntry.getKey(), lastEntry.getValue() - 1);
            } else {
                map.remove(lastEntry.getKey());
            }

            result++;
        }

        if (result == 0) {
            result = -1;
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
