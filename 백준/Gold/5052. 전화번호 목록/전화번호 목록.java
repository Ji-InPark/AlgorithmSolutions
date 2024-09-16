import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());
        Loop:
        for (int t = 0; t < T; t++) {
            var n = Integer.parseInt(br.readLine());
            var inputs = new ArrayList<String>();
            var root = new Trie();

            for (int i = 0; i < n; i++) {
                inputs.add(br.readLine());
            }

            inputs.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length() == o2.length()) {
                        return o1.compareTo(o2);
                    }

                    return o1.length() - o2.length();
                }
            });

            for (var input : inputs) {
                if (isDuplicated(root, input)) {
                    bw.write("NO\n");
                    continue Loop;
                }
            }

            bw.write("YES\n");
        }

        bw.flush();
    }

    private static boolean isDuplicated(Trie trie, String str) {
        for (var c : str.toCharArray()) {
            if (trie.isEnd) {
                return true;
            }

            var index = c - '0';
            if (trie.tries[index] == null) {
                trie.tries[index] = new Trie();
            }
            trie = trie.tries[index];
        }

        trie.isEnd = true;
        return false;
    }

    static class Trie {

        boolean isEnd;
        Trie[] tries;

        public Trie() {
            this.tries = new Trie[10];
        }
    }
}
