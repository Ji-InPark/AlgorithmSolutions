

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {

    static int result = Integer.MAX_VALUE, n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        n = Integer.parseInt(numInputs[0]);
        m = Integer.parseInt(numInputs[1]);

        var countMap = new HashMap<Integer, Integer>();
        var map = new TreeMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < m; j++) {
                map.put(Integer.parseInt(inputs[j]), i);
            }
        }

        var entries = new LinkedList<Entry<Integer, Integer>>();

        for (var entry : map.entrySet()) {
            countMap.put(entry.getValue(), countMap.getOrDefault(entry.getValue(), 0) + 1);
            entries.add(entry);

            while (countMap.size() == n) {
                var firstEntry = entries.poll();
                result = Math.min(result, entry.getKey() - firstEntry.getKey());

                var count = countMap.get(firstEntry.getValue());
                if (count == 1) {
                    countMap.remove(firstEntry.getValue());
                } else {
                    countMap.put(firstEntry.getValue(), count - 1);
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
