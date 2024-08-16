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
        int n = Integer.parseInt(inputs[0]), m = Integer.parseInt(inputs[1]), result = 0;

        var arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            var inputNums = br.readLine().split(" ");
            int n1 = Integer.parseInt(inputNums[0]), n2 = Integer.parseInt(inputNums[1]);

            if (n1 > n2) {
                var temp = n1;
                n1 = n2;
                n2 = temp;
            }

            var u1 = find(arr, n1);
            var u2 = find(arr, n2);

            if (u1 == u2) {
                result = i;
                break;
            }

            arr[u2] = u1;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static int find(int[] arr, int i) {
        if (arr[i] == i) {
            return i;
        }

        return arr[i] = find(arr, arr[i]);
    }
}