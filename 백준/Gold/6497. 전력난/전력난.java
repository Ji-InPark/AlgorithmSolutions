

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            var numInputs = br.readLine().split(" ");
            int m = Integer.parseInt(numInputs[0]), n = Integer.parseInt(numInputs[1]);
            if (m == 0 && n == 0) {
                break;
            }

            var map = new HashMap<Integer, ArrayList<int[]>>();
            var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1]));
            var visited = new HashSet<Integer>();
            var result = 0;

            for (int i = 0; i < m; i++) {
                map.put(i, new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                var inputs = br.readLine().split(" ");
                int n1 = Integer.parseInt(inputs[0]), n2 = Integer.parseInt(inputs[1]), dist = Integer.parseInt(
                        inputs[2]);

                result += dist;

                map.get(n1).add(new int[]{n2, dist});
                map.get(n2).add(new int[]{n1, dist});
            }
    
            visited.add(0);
            pq.addAll(map.get(0));

            while (visited.size() < m) {
                var now = pq.poll();
                int n1 = now[0], dist = now[1];

                if (visited.contains(n1)) {
                    continue;
                }

                pq.addAll(map.get(n1));

                visited.add(n1);
                result -= dist;
            }

            bw.write(result + "\n");
        }

        bw.flush();
    }
}