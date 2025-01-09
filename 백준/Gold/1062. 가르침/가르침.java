import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    static int result = 0;
    static HashSet<Character> defaultAlphabetSet = new HashSet<>();
    static ArrayList<HashSet<Character>> characterSets = new ArrayList<>();
    static ArrayList<Character> containCharacters;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        defaultAlphabetSet.add('a');
        defaultAlphabetSet.add('c');
        defaultAlphabetSet.add('i');
        defaultAlphabetSet.add('n');
        defaultAlphabetSet.add('t');

        var arr = br.readLine().split(" ");
        var n = Integer.parseInt(arr[0]);
        var k = Integer.parseInt(arr[1]) - 5;

        if (k < 0) {
            bw.write("0");
            bw.flush();
            return;
        }

        var containCharacterSet = new HashSet<Character>();
        for (int i = 0; i < n; i++) {
            var input = br.readLine();
            var set = new HashSet<Character>();
            for (var c : input.toCharArray()) {
                set.add(c);
            }

            set.removeAll(defaultAlphabetSet);
            characterSets.add(set);
            containCharacterSet.addAll(set);
        }

        containCharacters = new ArrayList<>(containCharacterSet);

        solve(new HashSet<>(), Math.min(k, containCharacterSet.size()), 0);
        bw.write(result + "\n");
        bw.flush();
    }

    private static void solve(HashSet<Character> set, int k, int index) {
        if (k == 0) {
            updateResult(set);
            return;
        }

        for (int i = index; i < containCharacters.size(); i++) {
            var c = containCharacters.get(i);

            if (set.contains(c)) {
                continue;
            }

            set.add(c);
            solve(set, k - 1, i + 1);
            set.remove(c);
        }
    }

    private static void updateResult(HashSet<Character> resultSet) {
        int count = 0;
        for (var characterSet : characterSets) {
            if (resultSet.containsAll(characterSet)) {
                count++;
            }
        }

        result = Math.max(result, count);
    }
}
