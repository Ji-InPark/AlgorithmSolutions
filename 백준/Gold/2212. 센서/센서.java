

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        var set = new HashSet<Integer>();
        var inputs = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(inputs[i]));
        }

        var arr = new ArrayList<>(set);
        arr.sort(Integer::compareTo);

        var diff = new LinkedList<Integer>();
        for (int i = 1; i < arr.size(); i++) {
            diff.add(arr.get(i) - arr.get(i - 1));
        }

        diff.sort(Collections.reverseOrder());
        for (int i = 0; i < k - 1; i++) {
            diff.poll();
        }

        var result = 0;
        while (!diff.isEmpty()) {
            result += diff.poll();
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

