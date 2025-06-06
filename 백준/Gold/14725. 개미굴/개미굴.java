

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var n = Integer.parseInt(br.readLine());
        var root = new TreeMap<String, TreeMap>();

        for (int i = 0; i < n; i++) {
            var input = br.readLine().split(" ");
            var num = Integer.parseInt(input[0]);
            var map = root;

            for (int j = 1; j <= num; j++) {
                if (!map.containsKey(input[j])) {
                    map.put(input[j], new TreeMap<String, TreeMap>());
                }

                map = map.get(input[j]);
            }
        }

        print(root, 0);
        bw.flush();
    }

    private static void print(TreeMap<String, TreeMap> map, int level) throws IOException {
        if (map.isEmpty()) {
            return;
        }

        for (var entry : map.entrySet()) {
            for (int i = 0; i < level; i++) {
                bw.write("--");
            }
            bw.write(entry.getKey() + "\n");
            print(entry.getValue(), level + 1);
        }
    }
}
