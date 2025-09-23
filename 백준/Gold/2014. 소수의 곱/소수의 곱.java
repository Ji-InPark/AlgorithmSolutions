

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]), prevMax = 0, curMax = 0;

        var inputs = br.readLine().split(" ");
        var arr = new long[n];
        var set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            var num = Long.parseLong(inputs[i]);
            arr[i] = num;
            set.add(num);
        }

        for (int i = 0; i < k - 1; i++) {
            var num = set.pollFirst();
            var last = set.isEmpty() ? Integer.MAX_VALUE : set.last();

            for (var prime : arr) {
                var next = num * prime;
                if (set.size() >= k && next > last) {
                    continue;
                }
                set.add(num * prime);
            }
        }

        bw.write(set.pollFirst() + "\n");
        bw.flush();
    }
}
