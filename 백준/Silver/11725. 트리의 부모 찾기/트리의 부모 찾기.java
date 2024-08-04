import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var map = new HashMap<Integer, Node>();

        var n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            var inputNums = br.readLine().split(" ");
            var num1 = Integer.parseInt(inputNums[0]);
            var num2 = Integer.parseInt(inputNums[1]);

            var node1 = map.getOrDefault(num1, new Node(num1));
            var node2 = map.getOrDefault(num2, new Node(num2));

            node1.children.add(num2);
            node2.children.add(num1);

            map.put(num1, node1);
            map.put(num2, node2);
        }

        var parentMap = new HashMap<Integer, Integer>();
        var nodes = new LinkedList<Integer>();
        nodes.add(1);

        while (!nodes.isEmpty()) {
            var node = map.get(nodes.poll());

            for (var child : node.children) {
                if (parentMap.containsKey(child)) {
                    continue;
                }

                parentMap.put(child, node.val);
                nodes.add(child);
            }
        }

        for (int i = 2; i <= n; i++) {
            bw.write(parentMap.get(i) + "\n");
        }
        bw.flush();

    }

    static class Node {

        int val;
        HashSet<Integer> children = new HashSet<>();

        public Node(int val) {
            this.val = val;
        }
    }

}
