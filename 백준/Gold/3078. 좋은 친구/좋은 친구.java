

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), k = Integer.parseInt(numInputs[1]);
        var map = new HashMap<Integer, LinkedList<Integer>>();

        var result = 0L;
        for (int i = 0; i < n; i++) {
            var size = br.readLine().length();

            if (!map.containsKey(size)) {
                map.put(size, new LinkedList<>());
            }

            var arr = map.get(size);
            while (!arr.isEmpty() && arr.peek() < i - k) {
                arr.poll();
            }
            result += arr.size();
            arr.add(i);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}

