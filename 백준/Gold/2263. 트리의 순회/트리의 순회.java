

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static int[] inorder, postorder;
    static int[][] indexArr;
    static int n;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        var inputs1 = br.readLine().split(" ");
        var inputs2 = br.readLine().split(" ");
        indexArr = new int[n + 1][2];
        visited = new boolean[n + 1];

        inorder = new int[n + 1];
        postorder = new int[n + 1];
        inorder[0] = postorder[0] = -1;

        for (int i = 1; i <= n; i++) {
            inorder[i] = Integer.parseInt(inputs1[i - 1]);
            postorder[i] = Integer.parseInt(inputs2[i - 1]);
        }

        for (int i = 1; i <= n; i++) {
            indexArr[inorder[i]][0] = i;
            indexArr[postorder[i]][1] = i;
        }

        makeTree(postorder[n], n + 1);

        bw.flush();
    }

    static void makeTree(int root, int prevRootIndex) throws IOException {
        if (root < 0 || visited[root]) {
            return;
        }

        var inRootIndex = indexArr[root][0];
        var postRootIndex = indexArr[root][1];
        var diff = Math.max(prevRootIndex - inRootIndex, 0);

        bw.write(root + " ");
        visited[root] = true;

        var leftRoot = postorder[postRootIndex - diff];
        var rightRoot = postorder[postRootIndex - 1];

        makeTree(leftRoot, inRootIndex);
        makeTree(rightRoot, prevRootIndex);
    }
}
