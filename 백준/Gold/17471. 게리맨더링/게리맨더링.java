

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

public class Main {

    static int result = Integer.MAX_VALUE;
    static int[] peopleArr;
    static HashMap<Integer, ArrayList<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), totalSum = 0;
        var inputs = br.readLine().split(" ");
        peopleArr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            peopleArr[i + 1] = Integer.parseInt(inputs[i]);
            totalSum += peopleArr[i + 1];
        }

        var ufArr = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            ufArr[i] = i;
        }

        map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());

            var numInputs = br.readLine().split(" ");

            for (int j = 1; j < numInputs.length; j++) {
                int num = Integer.parseInt(numInputs[j]);
                map.get(i).add(num);

                int n1 = find(ufArr, i), n2 = find(ufArr, num);

                if (n1 < n2) {
                    ufArr[n2] = n1;
                } else {
                    ufArr[n1] = n2;
                }
            }
        }

        var diffSet = new TreeSet<Integer>();
        for (int i = 1; i < n + 1; i++) {
            diffSet.add(find(ufArr, i));
        }

        int diff = diffSet.size();
        if (diff > 2) {
            bw.write("-1");
        } else if (diff == 2) {
            diffSet.pollFirst();
            int sum = 0;
            for (int i = 1; i < n + 1; i++) {
                if (diffSet.contains(ufArr[i])) {
                    sum += peopleArr[i];
                }
            }

            bw.write(Math.abs(totalSum - 2 * sum) + "\n");
        } else {
            dfs(new boolean[n + 1], 0, n, 1, n);

            if (result == Integer.MAX_VALUE) {
                bw.write("-1");
            } else {
                bw.write(result + "\n");
            }
        }

        bw.flush();
    }

    static private void dfs(boolean[] arr, int tCount, int fCount, int index, int n) {
        if (index == n + 1) {
            if (tCount != n && fCount != n) {
                if (isAllConnected(arr, true, tCount) && isAllConnected(arr, false, fCount)) {
                    result = Math.min(result, Math.abs(getPeopleCount(arr, true, n) - getPeopleCount(arr, false, n)));
                }
            }

            return;
        }

        dfs(arr, tCount, fCount, index + 1, n);
        arr[index] = true;
        dfs(arr, tCount + 1, fCount - 1, index + 1, n);
        arr[index] = false;
    }

    static private int getPeopleCount(boolean[] arr, boolean flag, int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] == flag) {
                sum += peopleArr[i];
            }
        }

        return sum;
    }

    static private boolean isAllConnected(boolean[] arr, boolean flag, int count) {
        int start = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == flag) {
                start = i;
                break;
            }
        }

        var q = new LinkedList<Integer>();
        q.add(start);
        var visited = new HashSet<Integer>();
        visited.add(start);

        while (!q.isEmpty()) {
            var now = q.poll();

            for (var next : map.get(now)) {
                if (arr[next] == flag && !visited.contains(next)) {
                    q.add(next);
                    visited.add(next);
                }
            }
        }

        return visited.size() == count;
    }

    static private int find(int[] arr, int index) {
        if (arr[index] == index) {
            return index;
        }

        return arr[index] = find(arr, arr[index]);
    }
}
