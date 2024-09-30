import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var input = br.readLine();

        var count = new int[26];

        int result = 0, left = 0, length = 0;
        for (var c : input.toCharArray()) {
            length++;
            if (count[c - 'a']++ == 0) {
                n--;
            }

            while (n < 0) {
                length--;
                if (--count[input.charAt(left++) - 'a'] == 0) {
                    n++;
                }
            }

            result = Math.max(result, length);
        }

        bw.write(result + "\n");
        bw.flush();

    }

}
