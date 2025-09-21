

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var password = br.readLine();
        var n = Integer.parseInt(br.readLine());
        var arr = new String[n];
        var diffArr = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        for (int i = 0; i < n; i++) {
            diffArr[i] = new ArrayList<Integer>();

            for (int j = 1; j < arr[i].length(); j++) {
                var diff = arr[i].charAt(j) - arr[i].charAt(j - 1);
                if (diff < 0) {
                    diff += 26;
                }
                diffArr[i].add(diff);
            }
        }

        var passwordDiff = new int[password.length() - 1];
        for (int i = 1; i < password.length(); i++) {
            passwordDiff[i - 1] = password.charAt(i) - password.charAt(i - 1);
            if (passwordDiff[i - 1] < 0) {
                passwordDiff[i - 1] += 26;
            }
        }

        int keyIndex = -1, keyCharAt = -1;
        for (int i = 0; i < n; i++) {
            var key = findIndex(diffArr[i], passwordDiff);
            if (key >= 0) {
                keyIndex = i;
                keyCharAt = key;
                break;
            }
        }

        var passChar = password.charAt(keyCharAt);
        var keyChar = arr[keyIndex].charAt(0);

        var keyDiff = passChar - keyChar;
        if (keyDiff < 0) {
            keyDiff += 26;
        }

        var map = new HashMap<Character, Character>();
        for (int i = 0; i < 26; i++) {
            map.put((char) ('a' + (i + keyDiff) % 26), (char) ('a' + i));
        }

        for (int i = 0; i < password.length(); i++) {
            bw.write(map.get(password.charAt(i)) + "");
        }
        bw.write("\n");

        bw.flush();
    }

    private static int findIndex(ArrayList<Integer> diffs, int[] passwordDiffs) {
        for (int i = 0; i <= passwordDiffs.length - diffs.size(); i++) {
            if (isSame(diffs, passwordDiffs, i)) {
                return i;
            }
        }

        return -1;
    }

    private static boolean isSame(ArrayList<Integer> diffs, int[] passwordDiffs, int index) {
        for (int i = 0; i < diffs.size(); i++) {
            if (diffs.get(i) != passwordDiffs[index + i]) {
                return false;
            }
        }

        return true;
    }
}
