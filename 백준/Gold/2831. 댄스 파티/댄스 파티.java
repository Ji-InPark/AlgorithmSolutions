

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
        var menWantUpper = new TreeMap<Integer, Integer>();
        var menWantLower = new TreeMap<Integer, Integer>();

        var men = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            var man = Integer.parseInt(men[i]);

            if (man < 0) {
                man *= -1;
                menWantLower.put(man, menWantLower.getOrDefault(man, 0) + 1);
            } else {
                menWantUpper.put(man, menWantUpper.getOrDefault(man, 0) + 1);
            }
        }

        var result = 0;
        var women = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            var woman = Integer.parseInt(women[i]);

            if (woman < 0) {
                woman *= -1;

                var entry = menWantUpper.lowerEntry(woman);
                if (entry == null) {
                    continue;
                }

                result++;

                if (entry.getValue() == 1) {
                    menWantUpper.remove(entry.getKey());
                    continue;
                }

                menWantUpper.put(entry.getKey(), entry.getValue() - 1);
            } else {
                var entry = menWantLower.higherEntry(woman);
                if (entry == null) {
                    continue;
                }

                result++;

                if (entry.getValue() == 1) {
                    menWantLower.remove(entry.getKey());
                    continue;
                }

                menWantLower.put(entry.getKey(), entry.getValue() - 1);
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
