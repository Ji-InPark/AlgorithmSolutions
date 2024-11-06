import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var n = Integer.parseInt(br.readLine());
            int count = 0;
            var arr = new int[2 * n];
            var counts = new int[2 * n];

            for (int i = 0; i < 2 * n; i++) {
                arr[i] = i;
                counts[i] = 1;
            }

            var map = new HashMap<String, Integer>();

            for (int i = 0; i < n; i++) {
                var inputs = br.readLine().split(" ");

                for (var input : inputs) {
                    if (!map.containsKey(input)) {
                        map.put(input, count++);
                    }
                }

                int n1 = find(arr, counts, map.get(inputs[0])), n2 = find(arr, counts, map.get(inputs[1]));

                if (n1 == n2) {
                    bw.write(counts[n1] + "\n");
                    continue;
                }

                bw.write((counts[n1] + counts[n2]) + "\n");

                if (n1 < n2) {
                    arr[n2] = n1;
                    counts[n1] += counts[n2];
                } else {
                    arr[n1] = n2;
                    counts[n2] += counts[n1];
                }
            }
        }

        bw.flush();
    }

    private static int find(int[] arr, int[] counts, int index) {
        if (arr[index] == index) {
            return index;
        }

        var result = find(arr, counts, arr[index]);
        arr[index] = result;
        counts[index]++;

        return result;
    }
}
