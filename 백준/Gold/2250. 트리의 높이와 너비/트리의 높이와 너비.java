

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    static int count = 0;
    static int[][] arr;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var map = new HashMap<Integer, Node>();
        var set = new HashSet<Integer>();
        arr = new int[n * 2][2];
        for (int i = 1; i <= n; i++) {
            map.put(i, new Node());
            arr[i][0] = n * 2;
            set.add(i);
        }

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");
            int nodeNum = Integer.parseInt(inputs[0]), leftNum = Integer.parseInt(
                    inputs[1]), rightNum = Integer.parseInt(inputs[2]);

            if (leftNum > 0) {
                map.get(nodeNum).left = map.get(leftNum);
                set.remove(leftNum);
            }

            if (rightNum > 0) {
                map.get(nodeNum).right = map.get(rightNum);
                set.remove(rightNum);
            }
        }

        for (var num : set) {
            setIndex(map.get(num), 1);
        }

        int result = 0, resultLevel = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i][0] == n * 2) {
                break;
            }

            var diff = arr[i][1] - arr[i][0] + 1;
            if (result < diff) {
                result = diff;
                resultLevel = i;
            }
        }

        bw.write(resultLevel + " " + result);
        bw.flush();
    }

    private static void setIndex(Node node, int level) {
        if (node == null) {
            return;
        }

        setIndex(node.left, level + 1);

        arr[level][0] = Math.min(arr[level][0], count);
        arr[level][1] = Math.max(arr[level][1], count);

        node.index = count++;

        setIndex(node.right, level + 1);
    }

    static class Node {

        Node left, right;
        int index;
    }
}
