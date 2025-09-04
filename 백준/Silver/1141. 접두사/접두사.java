

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var arr = new String[n];
        var root = new Trie();
        var result = n;

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            if (root.insert(arr[i], 0, false)) {
                result--;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static class Trie {

        boolean isEnd = false;
        Trie[] children = new Trie[26];

        public boolean insert(String s, int level, boolean flag) {
            if (level == s.length()) {
                if (this.isEnd) {
                    return true;
                }
                
                this.isEnd = true;
                return flag;
            }

            if (this.isEnd) {
                this.isEnd = false;
                flag = true;
            }

            int index = s.charAt(level) - 'a';
            if (children[index] == null) {
                children[index] = new Trie();
            }

            return children[index].insert(s, level + 1, flag);
        }
    }

}
