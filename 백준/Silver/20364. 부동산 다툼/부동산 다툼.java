import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]), q = Integer.parseInt(inputs[1]);
        var set = new HashSet<Integer>();

        for (int i = 0; i < q; i++) {
            int duck = Integer.parseInt(br.readLine()), goDuck = duck;
            var result = 0;

            while (goDuck > 0) {
                if (set.contains(goDuck)) {
                    result = goDuck;
                }

                goDuck /= 2;
            }

            if (result == 0) {
                set.add(duck);
            }

            bw.write(result + "\n");
        }

        bw.flush();
    }
}
