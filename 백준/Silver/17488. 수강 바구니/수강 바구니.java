

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var subInputs = br.readLine().split(" ");
        var arr = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            arr[i] = Integer.parseInt(subInputs[i - 1]);
        }

        var map = new HashMap<Integer, HashSet<Integer>>();
        var resultMap = new HashMap<Integer, TreeSet<Integer>>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
            resultMap.put(i, new TreeSet<>());
        }

        var counts = new int[m + 1];
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < n; i++) {
                var inputs = br.readLine().split(" ");

                for (int j = 0; j < inputs.length - 1; j++) {
                    var num = Integer.parseInt(inputs[j]);
                    map.get(i).add(num);
                    counts[num]++;
                }
            }

            for (int i = 1; i <= m; i++) {
                if (counts[i] <= arr[i]) {
                    for (int j = 0; j < n; j++) {
                        if (map.get(j).contains(i)) {
                            resultMap.get(j).add(i);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            var now = resultMap.get(i);

            if (now.isEmpty()) {
                bw.write("망했어요\n");
            } else {
                for (var num : now) {
                    bw.write(num + " ");
                }
                bw.write("\n");
            }
        }

        bw.flush();
    }
}

