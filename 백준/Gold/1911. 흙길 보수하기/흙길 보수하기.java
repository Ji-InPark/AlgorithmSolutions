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

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), l = Integer.parseInt(numInputs[1]);
        var map = new TreeMap<Integer, Integer>();

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            map.put(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }

        int result = 0;
        while (!map.isEmpty()) {
            var startEntry = map.firstEntry();
            var dist = startEntry.getValue() - startEntry.getKey();

            var count = (dist / l) + (dist % l == 0 ? 0 : 1);

            result += count;

            map.remove(startEntry.getKey());

            var calculatedKey = startEntry.getKey() + (count * l);
            var afterEntry = map.ceilingEntry(startEntry.getKey());

            while (afterEntry != null && afterEntry.getKey() < calculatedKey) {
                map.remove(afterEntry.getKey());
                if (afterEntry.getValue() > calculatedKey) {
                    map.put(calculatedKey, afterEntry.getValue());
                    break;
                }

                afterEntry = map.ceilingEntry(startEntry.getKey());
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
