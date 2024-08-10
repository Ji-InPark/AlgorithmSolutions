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

        var T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            var inputs = br.readLine().split(" ");

            var result = solve(inputs[0] + " ", inputs[1] + " ", inputs[2]);

            bw.write("Data set " + t + ": " + (result ? "yes" : "no") + "\n");
        }

        bw.flush();

    }

    private static boolean solve(String word1, String word2, String finalWord) {
        var map = new HashMap<Character, Integer>();

        int index1 = 0, index2 = 0;

        for (var c : finalWord.toCharArray()) {
            if (word1.charAt(index1) == c && word2.charAt(index2) == c) {
                index1++;
                index2++;
                map.put(c, map.getOrDefault(c, 0) + 1);
            } else if (word1.charAt(index1) == c) {
                index1++;
            } else if (word2.charAt(index2) == c) {
                index2++;
            } else if (map.getOrDefault(c, 0) >= 1) {
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }

        return index1 + index2 == finalWord.length();
    }
}
