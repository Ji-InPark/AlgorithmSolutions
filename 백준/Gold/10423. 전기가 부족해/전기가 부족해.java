import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]), k = Integer.parseInt(numInputs[2]);

        var powerStationInputs = br.readLine().split(" ");
        var powerStations = new ArrayList<Integer>();
        for (int i = 0; i < k; i++) {
            powerStations.add(Integer.parseInt(powerStationInputs[i]));
        }

        var map = new HashMap<Integer, ArrayList<int[]>>();
        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            int a1 = Integer.parseInt(inputs[0]), a2 = Integer.parseInt(inputs[1]),
                    cost = Integer.parseInt(inputs[2]);

            if (!map.containsKey(a1)) {
                map.put(a1, new ArrayList<>());
            }

            if (!map.containsKey(a2)) {
                map.put(a2, new ArrayList<>());
            }

            map.get(a1).add(new int[]{a2, cost});
            map.get(a2).add(new int[]{a1, cost});
        }

        var visited = new HashSet<>(powerStations);
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1]));
        for (var ps : powerStations) {
            pq.addAll(map.get(ps));
        }

        int result = 0;
        while (visited.size() < n) {
            var now = pq.poll();

            if (visited.contains(now[0])) {
                continue;
            }

            visited.add(now[0]);
            result += now[1];

            pq.addAll(map.get(now[0]));
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
