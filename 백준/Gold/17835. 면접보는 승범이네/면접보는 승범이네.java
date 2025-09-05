

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]), k = Integer.parseInt(numInputs[2]);

        var map = new HashMap<Long, ArrayList<long[]>>();
        var distMap = new HashMap<Long, Long>();

        for (long i = 1; i <= n; i++) {
            distMap.put(i, Long.MAX_VALUE);
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            long u = Integer.parseInt(inputs[0]), v = Integer.parseInt(inputs[1]), c = Integer.parseInt(inputs[2]);

            map.get(v).add(new long[]{u, c});
        }

        var q = new LinkedList<long[]>();
        long resultNode = n + 1, resultDist = 0;

        var inputs = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            var now = Integer.parseInt(inputs[i]);
            q.add(new long[]{now, 0});
        }

        while (!q.isEmpty()) {
            var now = q.poll();
            long node = now[0], dist = now[1];

            if (distMap.get(node) <= dist) {
                continue;
            }

            distMap.put(node, dist);

            for (var next : map.get(node)) {
                q.add(new long[]{next[0], dist + next[1]});
            }
        }

        for (long i = 1; i <= n; i++) {
            if (distMap.get(i) > resultDist) {
                resultDist = distMap.get(i);
                resultNode = i;
            }
        }

        bw.write(resultNode + "\n" + resultDist + "\n");
        bw.flush();
    }
}
