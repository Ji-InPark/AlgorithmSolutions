

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]);

        var inputs = br.readLine().split(" ");
        var map = new LinkedHashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(inputs[i]);

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        var resultMap = new TreeMap<Integer, ArrayList<Integer>>();
        for (var entry : map.entrySet()) {
            if (!resultMap.containsKey(entry.getValue())) {
                resultMap.put(entry.getValue(), new ArrayList<>());
            }

            resultMap.get(entry.getValue()).add(entry.getKey());
        }

        while (!resultMap.isEmpty()) {
            var now = resultMap.pollLastEntry();

            for (var num : now.getValue()) {
                for (int i = 0; i < now.getKey(); i++) {
                    bw.write(num + " ");
                }
            }
        }

        bw.flush();
    }
}

