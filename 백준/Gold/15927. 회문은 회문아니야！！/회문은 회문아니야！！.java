import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine();
        var arr = inputs.toCharArray();

        if (arr.length == 1 || isAllSame(arr)) {
            bw.write("-1");
        } else {
            int result = arr.length - (isPalindrome(arr) ? 1 : 0);

            bw.write(result + "\n");
        }

        bw.flush();
    }

    private static boolean isAllSame(char[] arr) {
        char prev = arr[0];

        for (var c : arr) {
            if (prev != c) {
                return false;
            }
        }

        return true;
    }

    private static boolean isPalindrome(char[] arr) {
        int last = arr.length - 1;

        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[last - i]) {
                return false;
            }
        }

        return true;
    }
}
