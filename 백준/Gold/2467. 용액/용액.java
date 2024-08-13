import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), min = Integer.MAX_VALUE;
        var inputs = br.readLine().split(" ");
        var set = new TreeSet<Integer>();
        var result = "";

        for (int i = 0; i < n; i++) {
            var num = Integer.parseInt(inputs[i]);

            if (set.isEmpty()) {
                set.add(num);
                continue;
            }

            var n1 = set.floor(-num);
            var n2 = set.ceiling(-num);

            if (n1 != null && Math.abs(num + n1) < min) {
                if(n1 < num) result = n1 + " " + num;
                else result = num + " " + n1;
                min = Math.abs(num + n1);
            }

            if (n2 != null && Math.abs(num + n2) < min) {
                if(n2 < num) result = n2 + " " + num;
                else result = num + " " + n2;
                min = Math.abs(num + n2);
            }

            set.add(num);
        }

        bw.write(result);
        bw.flush();

    }
}