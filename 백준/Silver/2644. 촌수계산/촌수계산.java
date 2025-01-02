import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), index = 0;
        int[] tree = new int[n + 1];

        var numInputs = br.readLine().split(" ");
        int start = Integer.parseInt(numInputs[0]), end = Integer.parseInt(numInputs[1]);

        var map = new HashMap<Integer, ArrayList<Integer>>();

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int first = Integer.parseInt(inputs[0]), second = Integer.parseInt(inputs[1]);

            if (!map.containsKey(first)) {
                map.put(first, new ArrayList<>());
            }

            if (!map.containsKey(second)) {
                map.put(second, new ArrayList<>());
            }

            map.get(first).add(second);
            map.get(second).add(first);
        }

        var visited = new HashSet<Integer>();
        var q = new LinkedList<Integer>();
        q.add(start);
        visited.add(start);

        int result = -1, level = 0;
        Loop:
        while (!q.isEmpty()) {
            var size = q.size();

            for (int i = 0; i < size; i++) {
                var now = q.poll();

                if (now == end) {
                    result = level;
                    break Loop;
                }

                for (var num : map.get(now)) {
                    if (visited.contains(num)) {
                        continue;
                    }

                    q.add(num);
                    visited.add(num);
                }
            }

            level++;
        }

        bw.write(result + "\n");
        bw.flush();

    }
}
