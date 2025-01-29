

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var map = new TreeMap<String, Integer>();

        for (int i = 0; i < n; i++) {
            var extension = br.readLine().split("\\.")[1];

            map.put(extension, map.getOrDefault(extension, 0) + 1);
        }

        for (var entry : map.entrySet()) {
            bw.write(entry.getKey() + " " + entry.getValue() + "\n");
        }
        bw.flush();
    }
}
