import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        var arr = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            arr.add(new int[]{Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])});
        }

        arr.sort(Comparator.comparingInt(o -> o[0]));

        int result = 0;
        for (var entry : arr) {
            if (result < entry[0]) {
                result = entry[0] + entry[1];
            } else {
                result += entry[1];
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

}
