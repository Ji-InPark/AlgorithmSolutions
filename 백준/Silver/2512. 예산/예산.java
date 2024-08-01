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

        int n = Integer.parseInt(br.readLine()), max = 0;
        var inputNums = br.readLine().split(" ");
        var nums = new ArrayList<Integer>();
        for (var inputNum : inputNums) {
            var num = Integer.parseInt(inputNum);
            nums.add(num);
            max = Math.max(num, max);
        }
        var totalMoney = Integer.parseInt(br.readLine());

        int low = 0, high = max;
        while (low <= high) {
            int mid = (high - low) / 2 + low, sum = 0;

            for (var num : nums) {
                sum += Math.min(num, mid);
            }

            if (sum > totalMoney) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        bw.write(high + "\n");
        bw.flush();

    }

}
