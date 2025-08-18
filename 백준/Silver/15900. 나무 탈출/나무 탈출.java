
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

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var map = new HashMap<Integer, ArrayList<Integer>>();

        for (int i = 0; i < n - 1; i++) {
            var inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]), b = Integer.parseInt(inputs[1]);

            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }

            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }

            map.get(a).add(b);
            map.get(b).add(a);
        }

        int result = 0, level = 0;
        var visited = new HashSet<Integer>();
        visited.add(1);
        var q = new LinkedList<Integer>();
        q.add(1);

        while (!q.isEmpty()) {
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var now = q.poll();

                var isLeaf = true;

                for (var next : map.get(now)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.add(next);
                        isLeaf = false;
                    }
                }

                if (isLeaf) {
                    result += level;
                }
            }

            level++;
        }

        bw.write(result % 2 == 0 ? "No" : "Yes");
        bw.flush();
    }
}
