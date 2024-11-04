import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]), k = Integer.parseInt(inputs[2]);
        var arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        var st = new SegmentTree(arr);

        for (int i = 0; i < m + k; i++) {
            inputs = br.readLine().split(" ");

            if (inputs[0].equals("1")) {
                st.updateValue(1, 0, arr.length - 1, Integer.parseInt(inputs[1]) - 1,
                        Long.parseLong(inputs[2]));
            } else {
                var result = st.getSum(1, 0, arr.length - 1, Integer.parseInt(inputs[1]) - 1,
                        Integer.parseInt(inputs[2]) - 1);
                bw.write(result + "\n");
            }
        }

        bw.flush();
    }

    static class SegmentTree {

        long[] tree, arr;

        public SegmentTree(long[] arr) {
            this.arr = arr;
            tree = new long[arr.length * 4];
            buildTree(1, 0, arr.length - 1);
        }

        public void updateValue(int node, int start, int end, int index, long value) {
            if (start == end) {
                tree[node] = value;
                return;
            }

            var mid = start + (end - start) / 2;

            if (index <= mid) {
                updateValue(node * 2, start, mid, index, value);
            } else {
                updateValue(node * 2 + 1, mid + 1, end, index, value);
            }

            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }

        public long getSum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            var mid = start + (end - start) / 2;
            return getSum(node * 2, start, mid, left, right) + getSum(node * 2 + 1, mid + 1, end, left, right);
        }

        private long buildTree(int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }

            var mid = start + (end - start) / 2;

            return tree[node] = buildTree(node * 2, start, mid) + buildTree(node * 2 + 1, mid + 1, end);
        }
    }
}
