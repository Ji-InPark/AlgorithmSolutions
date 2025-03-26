

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), m = Integer.parseInt(numInputs[1]);

        var indexMap = new HashMap<Character, Integer>();
        indexMap.put('A', 0);
        indexMap.put('C', 1);
        indexMap.put('G', 2);
        indexMap.put('T', 3);

        var arr = br.readLine().toCharArray();

        var countInputs = br.readLine().split(" ");
        var minCounts = new int[4];

        for (int i = 0; i < 4; i++) {
            minCounts[i] = Integer.parseInt(countInputs[i]);
        }

        var counts = new int[4];
        for (int i = 0; i < m; i++) {
            counts[indexMap.get(arr[i])]++;
        }

        var result = isValid(minCounts, counts) ? 1 : 0;
        for (int i = m; i < n; i++) {
            counts[indexMap.get(arr[i])]++;
            counts[indexMap.get(arr[i - m])]--;

            if (isValid(minCounts, counts)) {
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static boolean isValid(int[] minCounts, int[] counts) {
        for (int i = 0; i < 4; i++) {
            if (minCounts[i] > counts[i]) {
                return false;
            }
        }

        return true;
    }
}

