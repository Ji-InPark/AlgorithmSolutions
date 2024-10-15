import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine();
        var arr = new boolean[input.length()];
        Arrays.fill(arr, true);

        int zeros = 0, ones = 0;
        for (var c : input.toCharArray()) {
            if (c == '0') {
                zeros++;
            } else {
                ones++;
            }
        }

        for (int i = 0; i < arr.length && ones > 0; i++) {
            if (input.charAt(i) == '1') {
                arr[i] = false;
                ones -= 2;
            }
        }

        for (int i = arr.length - 1; i >= 0 && zeros > 0; i--) {
            if (input.charAt(i) == '0') {
                arr[i] = false;
                zeros -= 2;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]) {
                bw.write(input.charAt(i));
            }
        }

        bw.flush();
    }
}
