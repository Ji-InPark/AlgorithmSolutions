import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine();
        var target = br.readLine();

        var result = 0;
        for (int i = 0; i <= input.length() - target.length(); i++) {
            var str = input.substring(i, i + target.length());

            if (str.equals(target)) {
                i = i + target.length() - 1;
                result++;
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
