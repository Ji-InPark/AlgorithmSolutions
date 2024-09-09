import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var map = new HashMap<Integer, Node>();
        var n = Integer.parseInt(br.readLine());
        if (n == 1) {
            bw.write("0");
            bw.flush();
            return;
        }

        for (int i = 1; i <= n; i++) {
            map.put(i, new Node(i));
        }

        for (int i = 0; i < n - 1; i++) {
            var inputs = br.readLine().split(" ");

            var parent = Integer.parseInt(inputs[0]);
            var child = Integer.parseInt(inputs[1]);
            var weight = Integer.parseInt(inputs[2]);

            var childNode = map.get(child);
            var parentNode = map.get(parent);
            childNode.weight = weight;
            parentNode.children.add(childNode);
            childNode.parent = parentNode;
        }

        var maxVal = 0;
        var maxNode = new Node(0);

        var q = new LinkedList<int[]>();
        q.add(new int[]{1, 0});

        while (!q.isEmpty()) {
            var now = q.poll();
            var node = map.get(now[0]);

            if (node.children.isEmpty()) {
                if (maxVal < now[1]) {
                    maxVal = now[1];
                    maxNode = node;
                }
            } else {
                for (var child : node.children) {
                    q.add(new int[]{child.val, now[1] + child.weight});
                }
            }
        }

        var result = 0;
        var visited = new HashSet<Integer>();
        q.add(new int[]{maxNode.val, 0});

        while (!q.isEmpty()) {
            var now = q.poll();
            var node = map.get(now[0]);

            if (visited.contains(now[0])) {
                continue;
            }

            visited.add(node.val);

            result = Math.max(result, now[1]);

            if (node.parent != null) {
                q.add(new int[]{node.parent.val, now[1] + node.weight});
            }
            for (var child : node.children) {
                q.add(new int[]{child.val, now[1] + child.weight});
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    static class Node {

        int val, weight;
        ArrayList<Node> children = new ArrayList<>();
        Node parent;

        public Node(int val) {
            this.val = val;
        }
    }
}
