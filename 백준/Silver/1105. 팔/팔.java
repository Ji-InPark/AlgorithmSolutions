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

        if (inputs[0].length() == inputs[1].length()) {
            var result = 0;
            for (int i = 0; i < inputs[0].length(); i++) {
                if (inputs[0].charAt(i) < inputs[1].charAt(i)) {
                    break;
                }

                if (inputs[0].charAt(i) == '8' && inputs[1].charAt(i) == '8') {
                    result++;
                }
            }

            bw.write(result + "\n");
        } else {
            bw.write("0");
        }

        bw.flush();

    }

}
