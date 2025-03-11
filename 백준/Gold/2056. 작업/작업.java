

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var arr = new int[n + 1];
        var map = new HashMap<Integer, HashSet<Integer>>();
        var dependencyMap = new HashMap<Integer, HashSet<Integer>>();

        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
            dependencyMap.put(i, new HashSet<>());
        }

        var q = new LinkedList<int[]>();
        for (int i = 1; i <= n; i++) {
            var inputs = br.readLine().split(" ");
            int value = Integer.parseInt(inputs[0]), size = Integer.parseInt(inputs[1]);
            arr[i] = value;
            for (int j = 2; j < 2 + size; j++) {
                var num = Integer.parseInt(inputs[j]);
                map.get(i).add(num);
                dependencyMap.get(num).add(i);
            }

            if (size == 0) {
                q.add(new int[]{i, value});
            }
        }

        var result = 0;
        while (!q.isEmpty()) {
            q.sort(Comparator.comparingInt(o -> o[1]));

            var minNode = q.poll();
            int min = minNode[1], size = q.size();
            var mins = new LinkedList<Integer>();
            mins.add(minNode[0]);
            for (int i = 0; i < size; i++) {
                var now = q.poll();
                now[1] -= min;
                if (now[1] == 0) {
                    mins.add(now[0]);
                    continue;
                }
                q.add(now);
            }
            result += min;

            for (var minIndex : mins) {
                for (var di : dependencyMap.get(minIndex)) {
                    map.get(di).remove(minIndex);
                    if (map.get(di).isEmpty()) {
                        map.remove(di);
                        q.add(new int[]{di, arr[di]});
                    }
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

