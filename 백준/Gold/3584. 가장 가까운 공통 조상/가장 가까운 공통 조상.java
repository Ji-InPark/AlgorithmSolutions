

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static HashMap<Integer, Integer> map = new HashMap<>();
    static HashSet<Integer> set = new HashSet<>();
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            map.clear();
            set.clear();
            result = -1;
            var n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n - 1; i++) {
                var input = br.readLine().split(" ");
                int parent = Integer.parseInt(input[0]), child = Integer.parseInt(input[1]);

                map.put(child, parent);
            }

            var input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]), n2 = Integer.parseInt(input[1]);

            dfs(n1);
            dfs(n2);

            bw.write(result + "\n");
        }

        bw.flush();
    }

    private static void dfs(int node) {
        if (!map.containsKey(node)) {
            if (set.contains(node) && result < 0) {
                result = node;
                return;
            }
            set.add(node);
            return;
        }

        if (set.contains(node) && result < 0) {
            result = node;
            return;
        }

        set.add(node);

        dfs(map.get(node));
    }
}
