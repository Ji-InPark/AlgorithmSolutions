import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]);

        if (n == 1) {
            bw.write("1");
        } else if (n == 2) {
            bw.write(Math.min(((m + 1) / 2), 4) + "\n");
        } else {
            if (m <= 4) {
                bw.write(m + "\n");
            } else {
                bw.write(Math.max(m - 2, 4) + "\n");
            }
        }
        bw.flush();

    }
}