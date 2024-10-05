import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var q = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        for (int i = 0; !q.isEmpty(); i++) {
            if (i % 2 == 0) {
                bw.write(q.poll() + " ");
            } else {
                q.add(q.poll());
            }
        }

        bw.flush();

    }

}
