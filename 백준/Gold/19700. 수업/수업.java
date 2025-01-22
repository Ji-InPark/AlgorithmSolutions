

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            int height = Integer.parseInt(inputs[0]), score = Integer.parseInt(inputs[1]);

            arr.add(new int[]{height, score});
        }

        arr.sort(Comparator.comparingInt(o -> -o[0]));

        var result = new ArrayList<int[]>();
        result.add(new int[]{Integer.MAX_VALUE, 0});

        var map = new TreeMap<Integer, LinkedList<Integer>>();
        map.put(0, new LinkedList<>());
        map.get(0).add(0);

        for (var entry : arr) {
            var lowerEntry = map.lowerEntry(entry[1]);

            if (lowerEntry != null) {
                var indexList = lowerEntry.getValue();
                var index = indexList.poll();

                var resultEntry = result.get(index);
                resultEntry[0] = entry[0];
                resultEntry[1]++;

                if (indexList.isEmpty()) {
                    map.remove(lowerEntry.getKey());
                }

                if (!map.containsKey(resultEntry[1])) {
                    map.put(resultEntry[1], new LinkedList<>());
                }

                map.get(resultEntry[1]).add(index);
            } else {
                entry[1] = 1;
                result.add(entry);

                if (!map.containsKey(1)) {
                    map.put(1, new LinkedList<>());
                }

                map.get(1).add(result.size() - 1);
            }
        }

        bw.write(result.size() + "\n");
        bw.flush();
    }
}
