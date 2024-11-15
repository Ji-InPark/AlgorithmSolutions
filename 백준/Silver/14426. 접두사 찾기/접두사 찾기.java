import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]);
        var root = new Trie();

        for (int i = 0; i < n; i++) {
            var input = br.readLine();

            root.append(input);
        }

        var result = 0;
        for (int i = 0; i < m; i++) {
            var input = br.readLine();

            result += root.startsWith(input) ? 1 : 0;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    public static class Trie {

        Trie[] children = new Trie[26];

        public void append(String str) {
            this.append(str, 0);
        }

        private void append(String str, int index) {
            if (str.length() == index) {
                return;
            }

            var c = str.charAt(index);

            if (children[c - 'a'] == null) {
                children[c - 'a'] = new Trie();
            }

            children[c - 'a'].append(str, index + 1);
        }


        public boolean startsWith(String str) {
            return this.startsWith(str, 0);
        }

        private boolean startsWith(String str, int index) {
            if (str.length() == index) {
                return true;
            }

            var c = str.charAt(index);

            if (children[c - 'a'] == null) {
                return false;
            }

            return children[c - 'a'].startsWith(str, index + 1);
        }
    }
}
