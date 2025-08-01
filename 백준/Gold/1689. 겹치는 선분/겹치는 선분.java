

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            arr[i][0] = Integer.parseInt(inputs[0]);
            arr[i][1] = Integer.parseInt(inputs[1]);
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }

            return o1[0] - o2[0];
        });

        int count = 0, result = 0;
        var map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            while (!map.isEmpty() && map.firstKey() <= arr[i][0]) {
                var entry = map.firstEntry();
                if (entry.getValue() == 1) {
                    map.remove(entry.getKey());
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
                count--;
            }

            map.put(arr[i][1], map.getOrDefault(arr[i][1], 0) + 1);
            count++;

            result = Math.max(result, count);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
