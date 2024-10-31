import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var nodes = new Node[n];
        var lines = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            nodes[i] = new Node(Double.parseDouble(inputs[0]), Double.parseDouble(inputs[1]), i);
            lines[i] = new ArrayList<Line>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                var n1 = nodes[i];
                var n2 = nodes[j];

                var line = new Line(n1, n2);
                lines[n1.index].add(line);
                lines[n2.index].add(line);
            }
        }

        var tree = new HashSet<Node>();
        tree.add(nodes[0]);

        var pq = new PriorityQueue<Line>();
        pq.addAll(lines[0]);

        var result = 0d;
        while (tree.size() < n) {
            var line = pq.poll();

            if (tree.contains(line.n1) && tree.contains(line.n2)) {
                continue;
            }

            if (tree.contains(line.n1)) {
                tree.add(line.n2);
                pq.addAll(lines[line.n2.index]);
            } else {
                tree.add(line.n1);
                pq.addAll(lines[line.n1.index]);
            }

            result += line.distance;
        }

        bw.write(String.format("%.2f", result));
        bw.flush();

    }

    static class Node {

        int index;
        double x, y;

        public Node(double x, double y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class Line implements Comparable<Line> {

        Node n1, n2;
        double distance;

        public Line(Node n1, Node n2) {
            this.n1 = n1;
            this.n2 = n2;
            this.distance = calculateDistance(n1, n2);
        }

        private double calculateDistance(Node node1, Node node2) {
            return Math.sqrt(Math.pow(node1.x - node2.x, 2) + Math.pow(node1.y - node2.y, 2));
        }

        @Override
        public int compareTo(Line o) {
            return Double.compare(this.distance, o.distance);
        }
    }
}
