import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var map = new HashMap<Integer, HashSet<Integer>>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                if (inputs[j] == 'N') {
                    continue;
                }

                map.get(i).add(j);
            }
        }

        var result = 0;
        for (int i = 0; i < n; i++) {
            var set = new HashSet<Integer>();
            set.add(i);
            set.addAll(map.get(i));
            for (var num : map.get(i)) {
                set.addAll(map.get(num));
            }

            result = Math.max(set.size(), result);
        }

        bw.write(--result + "\n");
        bw.flush();
    }
}