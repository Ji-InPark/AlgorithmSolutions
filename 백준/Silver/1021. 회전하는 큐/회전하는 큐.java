import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputArray = br.readLine().split(" ");
        var n = Integer.parseInt(inputArray[0]);

        var inputNums = br.readLine().split(" ");
        var nums = new ArrayList<Integer>();

        for (var inputNum : inputNums) {
            nums.add(Integer.parseInt(inputNum));
        }

        var result = getResult(n, nums);

        bw.write(result + "\n");
        bw.flush();
    }

    private static int getResult(int n, List<Integer> nums) {
        var arrayList = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            arrayList.add(i);
        }

        var result = 0;
        var index = 0;
        for (var num : nums) {
            var numIndex = arrayList.indexOf(num);
            var distance = Math.abs(index - numIndex);

            if (distance > (arrayList.size() / 2)) {
                distance = arrayList.size() - distance;
            }

            arrayList.remove(numIndex);
            result += distance;
            if (!arrayList.isEmpty()) {
                index = numIndex % arrayList.size();
            }
        }
        return result;
    }
}
