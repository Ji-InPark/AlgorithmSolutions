

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class Main {


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]), t = Integer.parseInt(numInputs[2]);
        var result = Long.valueOf(t);
        var inputs = br.readLine().split(" ");
        var map = new TreeMap<Long, Integer>();
        for (int i = 0; i < n; i++) {
            var num = Long.parseLong(inputs[i]);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < k; i++) {
            var entry = map.lowerEntry(result);

            if (entry == null) {
                break;
            }

            result += entry.getKey();

            if (entry.getValue() == 1) {
                map.remove(entry.getKey());
            } else {
                map.put(entry.getKey(), entry.getValue() - 1);
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
