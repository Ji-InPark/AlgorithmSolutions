

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var set = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(br.readLine()));
        }

        int left = 0, right = Integer.MAX_VALUE;
        while (left + 1 < right) {
            var mid = left + (right - left) / 2;

            if (isValid(set, mid, m)) {
                left = mid;
            } else {
                right = mid;
            }
        }

        bw.write(left + "\n");
        bw.flush();
    }

    private static boolean isValid(TreeSet<Integer> set, int target, int targetCount) {
        int count = 1, now = set.first();

        while (true) {
            var next = set.ceiling(now + target);

            if (next == null) {
                break;
            }

            now = next;
            count++;
        }

        return count >= targetCount;
    }
}
