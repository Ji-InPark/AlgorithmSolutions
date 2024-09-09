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

        var n = Integer.parseInt(br.readLine());
        var inputs = new String[n];

        for (int i = 0; i < n; i++) {
            inputs[i] = br.readLine();
        }

        var result = inputs[0].length() - 1;
        for (int i = 0; i < inputs[0].length(); i++) {
            var set = new HashSet<String>();

            for (var s : inputs) {
                set.add(s.substring(i));
            }

            if (set.size() != n) {
                result = i - 1;
                break;
            }
        }

        if (result < 0) {
            bw.write(inputs[0].length() + "\n");
        } else {
            bw.write((inputs[0].length() - result) + "\n");
        }

        bw.flush();
    }
}
