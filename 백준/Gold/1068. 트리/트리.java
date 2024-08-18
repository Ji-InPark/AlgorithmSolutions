import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var map = new HashMap<Integer, Node>();

        Node root = null;
        int n = Integer.parseInt(br.readLine());
        var inputs = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            map.put(i, new Node(i));
        }

        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(inputs[i]);
            if (num == -1) {
                root = map.get(i);
                continue;
            }

            var parent = map.get(num);
            var child = map.get(i);

            parent.children.add(child);
            child.parent = parent;
        }

        int removeNum = Integer.parseInt(br.readLine());

        var removedNode = map.get(removeNum);
        if (removedNode == root) {
            bw.write("0");
            bw.flush();
            return;
        }
        removedNode.parent.children.remove(removedNode);

        var result = bfs(root);

        bw.write(result + "\n");
        bw.flush();
    }

    private static int bfs(Node root) {
        var q = new LinkedList<Node>();
        q.add(root);

        var result = 0;
        while (!q.isEmpty()) {
            var node = q.poll();
            q.addAll(node.children);
            if (node.children.isEmpty()) {
                result++;
            }
        }

        return result;
    }

    static class Node {

        int val;
        Node parent;
        ArrayList<Node> children = new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }
    }
}