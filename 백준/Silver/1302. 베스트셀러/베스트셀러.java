import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var map = new HashMap<String, Integer>();

        for (int i = 0; i < n; i++) {
            var book = br.readLine();
            map.put(book, map.getOrDefault(book, 0) + 1);
        }

        var result = "z";
        var max = 0;
        for (var entry : map.entrySet()) {
            if (entry.getValue() < max) {
                continue;
            }

            if (entry.getValue() != max) {
                result = entry.getKey();
                max = entry.getValue();
                continue;
            }

            if (entry.getKey().compareTo(result) < 0) {
                result = entry.getKey();
            }
        }

        bw.write(result);
        bw.flush();
    }
}
