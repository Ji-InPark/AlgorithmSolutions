import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var nums = br.readLine().split(" ");
        int n = Integer.parseInt(nums[0]), m = Integer.parseInt(nums[1]);
        var arr = new int[n + 1];

        var nodes = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            var inputs = br.readLine().split(" ");

            nodes[i] = new Node(Long.parseLong(inputs[0]), Long.parseLong(inputs[1]), i);
            arr[i] = i;
        }

        var pq = new LinkedList<Line>();
        for (int i = 0; i < m; i++) {
            var inputs = br.readLine().split(" ");
            var n1 = Integer.parseInt(inputs[0]);
            var n2 = Integer.parseInt(inputs[1]);

            int t1 = find(arr, n1), t2 = find(arr, n2);

            if (t1 == t2) {
                continue;
            }

            if (t1 < t2) {
                arr[t2] = t1;
            } else {
                arr[t1] = t2;
            }
        }

        for (int i = 1; i <= n; i++) {
            var n1 = nodes[i];

            for (int j = i + 1; j <= n; j++) {
                var n2 = nodes[j];

                var length = getLength(n1, n2);
                var line = new Line(n1, n2, length);
                pq.add(line);
            }
        }

        pq.sort(Comparator.comparingDouble(o -> o.length));

        var result = 0.0d;
        while (!pq.isEmpty()) {
            var line = pq.poll();

            int t1 = find(arr, line.n1.index), t2 = find(arr, line.n2.index);

            if (t1 == t2) {
                continue;
            }

            result += line.length;

            if (t1 < t2) {
                arr[t2] = t1;
            } else {
                arr[t1] = t2;
            }
        }

        bw.write(String.format("%.2f", result));
        bw.flush();
    }

    private static int find(int[] arr, int index) {
        if (arr[index] == index) {
            return index;
        }

        return arr[index] = find(arr, arr[index]);
    }

    private static double getLength(Node n1, Node n2) {
        return Math.sqrt((n1.x - n2.x) * (n1.x - n2.x) + (n1.y - n2.y) * (n1.y - n2.y));
    }

    static class Node {

        int index;
        long x, y;

        public Node(long x, long y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class Line {

        Node n1, n2;
        double length;

        public Line(Node n1, Node n2, double length) {
            this.n1 = n1;
            this.n2 = n2;
            this.length = length;
        }
    }
}
