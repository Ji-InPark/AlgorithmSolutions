import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputNums = br.readLine().split(" ");
        int n = Integer.parseInt(inputNums[0]), m = Integer.parseInt(inputNums[1]), result = 1;

        while (n < m) {
            if (m % 10 == 1) {
                m /= 10;
            } else if (m % 2 == 1) {
                result = -1;
                break;
            } else {
                m /= 2;
            }

            result++;
        }

        if (n != m) {
            result = -1;
        }

        bw.write(result + "\n");
        bw.flush();

    }

}
