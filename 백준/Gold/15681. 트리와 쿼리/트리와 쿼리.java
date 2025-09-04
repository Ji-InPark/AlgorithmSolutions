

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static HashMap<Integer, Integer> countMap = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), r = Integer.parseInt(numInputs[1]), q = Integer.parseInt(numInputs[2]);

        for (int i = 0; i < n - 1; i++) {
            var inputs = br.readLine().split(" ");
            int a = Integer.parseInt(inputs[0]), b = Integer.parseInt(inputs[1]);

            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }

            map.get(a).add(b);

            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<>());
            }
            map.get(b).add(a);
        }

        count(r, -1);

        for (int i = 0; i < q; i++) {
            var input = Integer.parseInt(br.readLine());

            bw.write(countMap.get(input) + "\n");
        }

        bw.flush();
    }

    private static int count(int node, int pre) {
        var sum = 1;
        for (var next : map.get(node)) {
            if (next == pre) {
                continue;
            }

            sum += count(next, node);
        }

        countMap.put(node, sum);
        return sum;
    }

}
