import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Main {

    static int result = Integer.MAX_VALUE, end;
    static int[] weight;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        weight = new int[n + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);

        var map = new HashMap<Integer, ArrayList<int[]>>();
        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");

            int n1 = Integer.parseInt(inputs[0]), n2 = Integer.parseInt(inputs[1]), n3 = Integer.parseInt(inputs[2]);

            if (!map.containsKey(n1)) {
                map.put(n1, new ArrayList<>());
            }

            if (!map.containsKey(n2)) {
                map.put(n2, new ArrayList<>());
            }

            if (!map.containsKey(n3)) {
                map.put(n3, new ArrayList<>());
            }

            map.get(n1).add(new int[]{n2, n3});
        }

        for (var entry : map.entrySet()) {
            entry.getValue().sort(Comparator.comparingInt(o -> o[1]));
        }

        var inputs = br.readLine().split(" ");
        int start = Integer.parseInt(inputs[0]);
        end = Integer.parseInt(inputs[1]);

        solve(map, start, 0);

        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(HashMap<Integer, ArrayList<int[]>> map, int start, int level) {
        if (start == end) {
            result = Math.min(result, level);
            return;
        }

        if (weight[start] <= level) {
            return;
        }

        weight[start] = level;

        for (var route : map.get(start)) {
            solve(map, route[0], level + route[1]);
        }
    }
}
