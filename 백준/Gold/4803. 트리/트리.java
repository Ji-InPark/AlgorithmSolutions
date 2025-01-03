import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int t = 1; ; t++) {
            var numInputs = br.readLine().split(" ");
            int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

            if (n == 0 && m == 0) {
                break;
            }

            var arr = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                arr[i] = i;
            }

            var ignoreSet = new HashSet<Integer>();
            for (int i = 0; i < m; i++) {
                var inputs = br.readLine().split(" ");
                int first = Integer.parseInt(inputs[0]), second = Integer.parseInt(inputs[1]);

                int ff = find(arr, first), fs = find(arr, second);

                if (ff == fs || ignoreSet.contains(ff) || ignoreSet.contains(fs)) {
                    ignoreSet.add(ff);
                    ignoreSet.add(fs);
                }

                arr[Math.max(ff, fs)] = Math.min(ff, fs);
            }

            var set = new HashSet<Integer>();
            for (int i = 1; i <= n; i++) {
                set.add(find(arr, arr[i]));
            }

            set.removeAll(ignoreSet);

            if (set.isEmpty()) {
                bw.write("Case " + t + ": No trees.\n");
            } else if (set.size() == 1) {
                bw.write("Case " + t + ": There is one tree.\n");
            } else {
                bw.write("Case " + t + ": A forest of " + set.size() + " trees.\n");
            }
        }

        bw.flush();
    }

    private static int find(int[] arr, int index) {
        if (arr[index] == index) {
            return index;
        }

        return arr[index] = find(arr, arr[index]);
    }
}
