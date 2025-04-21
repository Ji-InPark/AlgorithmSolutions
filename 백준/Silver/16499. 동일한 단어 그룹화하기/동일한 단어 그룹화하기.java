

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    static int k, n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var set = new HashSet<String>();

        var n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            var count = new int[26];

            var arr = br.readLine().toCharArray();
            for (var c : arr) {
                count[c - 'a']++;
            }

            var sb = new StringBuilder();
            for (var num : count) {
                sb.append(num).append("-");
            }

            set.add(sb.toString());
        }

        bw.write(set.size() + "\n");
        bw.flush();
    }
}
