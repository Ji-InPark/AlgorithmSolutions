import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var firstWord = br.readLine();

        var result = 0;
        for (int i = 0; i < n - 1; i++) {
            if (isSimilar(firstWord, br.readLine())) {
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isSimilar(String first, String second) {
        var diff = first.length() - second.length();
        if (Math.abs(diff) > 1) {
            return false;
        }

        var fm = new HashMap<Character, Integer>();
        var sm = new HashMap<Character, Integer>();

        for (var c : first.toCharArray()) {
            fm.put(c, fm.getOrDefault(c, 0) + 1);
        }

        for (var c : second.toCharArray()) {
            sm.put(c, sm.getOrDefault(c, 0) + 1);
        }

        if (diff == 1) {
            int count = 0;
            for (var entry : fm.entrySet()) {
                count += Math.abs(sm.getOrDefault(entry.getKey(), 0) - entry.getValue());
            }

            if (count > 1) {
                return false;
            }

            count = 0;
            for (var entry : sm.entrySet()) {
                count += Math.abs(fm.getOrDefault(entry.getKey(), 0) - entry.getValue());
            }

            return count <= 1;
        } else {
            var set = new HashSet<>(fm.keySet());
            set.addAll(sm.keySet());

            int count = 0;
            for (var num : set) {
                count += Math.abs(fm.getOrDefault(num, 0) - sm.getOrDefault(num, 0));
            }

            return count <= 2;
        }
    }
}
