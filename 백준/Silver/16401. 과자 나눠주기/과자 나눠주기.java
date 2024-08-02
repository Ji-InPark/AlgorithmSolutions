import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputNums = br.readLine().split(" ");
        int n = Integer.parseInt(inputNums[0]), m = Integer.parseInt(inputNums[1]);
        inputNums = br.readLine().split(" ");
        var arrayList = new ArrayList<Integer>();
        for (var inputNum : inputNums) {
            arrayList.add(Integer.parseInt(inputNum));
        }

        int low = 0, high = Integer.MAX_VALUE;
        while (low + 1 < high) {
            int mid = (high - low) / 2 + low, count = 0;

            for (var num : arrayList) {
                count += num / mid;
            }

            if (count < n) {
                high = mid;
            } else {
                low = mid;
            }
        }

        bw.write(low + "\n");
        bw.flush();

    }

}
