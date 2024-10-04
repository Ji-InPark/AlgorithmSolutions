import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        int[] nums1 = new int[n], sums1 = new int[n + 1];

        for (int i = 0; i < n; i++) {
            nums1[i] = Integer.parseInt(inputs[i]);

            sums1[i + 1] = sums1[i] + nums1[i];
        }

        int m = Integer.parseInt(br.readLine());
        inputs = br.readLine().split(" ");
        int[] nums2 = new int[m], sums2 = new int[m + 1];

        for (int i = 0; i < m; i++) {
            nums2[i] = Integer.parseInt(inputs[i]);

            sums2[i + 1] = sums2[i] + nums2[i];
        }

        var map1 = new HashMap<Integer, Integer>();
        var map2 = new HashMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                var num = sums1[j] - sums1[i];

                map1.put(num, map1.getOrDefault(num, 0) + 1);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j <= m; j++) {
                var num = sums2[j] - sums2[i];

                map2.put(num, map2.getOrDefault(num, 0) + 1);
            }
        }

        var result = 0L;
        for (var entry : map1.entrySet()) {
            var target = t - entry.getKey();

            result += (long) entry.getValue() * map2.getOrDefault(target, 0);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
