

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var m = Integer.parseInt(br.readLine());

        var map = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            for (int j = 0; j < n; j++) {
                if (!map.containsKey(i)) {
                    map.put(i, new HashSet<>());
                }

                if (!map.containsKey(j)) {
                    map.put(j, new HashSet<>());
                }
                
                if (Integer.parseInt(inputs[j]) == 1) {
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }

        var resultMap = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 0; i < n; i++) {
            var q = new LinkedList<Integer>();
            q.add(i);
            var visited = new HashSet<Integer>();
            visited.add(i);

            while (!q.isEmpty()) {
                var now = q.poll();

                for (var next : map.get(now)) {
                    if (!visited.contains(next)) {
                        q.add(next);
                        visited.add(next);
                    }
                }
            }

            resultMap.put(i, visited);
        }

        var inputs = br.readLine().split(" ");
        var arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(inputs[i]) - 1;
        }

        var result = true;
        for (int i = 0; i < m - 1 && result; i++) {
            if (!resultMap.get(arr[i]).contains(arr[i + 1])) {
                result = false;
            }
        }

        bw.write(result ? "YES" : "NO");
        bw.flush();
    }
}
