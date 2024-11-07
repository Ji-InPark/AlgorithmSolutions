import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());

        var inputs = br.readLine().split(" ");
        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(inputs[i]);
        }

        var counts = new int[10];
        int count = 0, left = 0, result = 0;

        for (int i = 0; i < n; i++) {
            if (counts[arr[i]]++ == 0) {
                count++;
            }

            while (count > 2) {
                if (--counts[arr[left++]] == 0) {
                    count--;
                }
            }

            result = Math.max(result, i - left + 1);
        }

        bw.write(result + "\n");
        bw.flush();
    }
}
