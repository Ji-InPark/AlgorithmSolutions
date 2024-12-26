import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int n = Integer.parseInt(numInputs[0]), a = Integer.parseInt(numInputs[1]), b = Integer.parseInt(
                numInputs[2]), result = 0;

        var q = new LinkedList<Integer>();

        var aInputs = br.readLine().split(" ");
        for (int i = 0; i < a; i++) {
            q.add(Integer.parseInt(aInputs[i]));
        }

        q.sort(Collections.reverseOrder());

        if (n % 2 == 1) {
            result += q.poll();
        }

        var size = q.size();
        if (size % 2 == 1) {
            q.pollLast();
        }

        for (int i = 0; i < size / 2; i++) {
            var next = q.poll() + q.poll();
            q.add(next);
        }

        var bInputs = br.readLine().split(" ");
        for (int i = 0; i < b; i++) {
            q.add(Integer.parseInt(bInputs[i]));
        }

        q.sort(Collections.reverseOrder());

        for (int i = 0; i < n / 2; i++) {
            result += q.poll();
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
