import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var pq = new PriorityQueue<BusRoute>();
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            pq.add(new BusRoute(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2])));
        }

        var result = new ArrayList<BusRoute>();

        while (!pq.isEmpty()) {
            var startBusRoute = pq.poll();

            while (!pq.isEmpty() && pq.peek().start <= startBusRoute.end) {
                var busRoute = pq.poll();
                startBusRoute.end = Math.max(startBusRoute.end, busRoute.end);
                startBusRoute.price = Math.min(startBusRoute.price, busRoute.price);
            }

            result.add(startBusRoute);
        }

        bw.write(result.size() + "\n");
        for (var s : result) {
            bw.write(s.start + " " + s.end + " " + s.price + "\n");
        }

        bw.flush();
    }

    static class BusRoute implements Comparable<BusRoute> {

        int start, end, price;

        public BusRoute(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }

        @Override
        public int compareTo(BusRoute o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }

            return this.start - o.start;
        }
    }
}
