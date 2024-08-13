import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var map = new HashMap<Integer, Integer>();

        int n = Integer.parseInt(br.readLine()), max = 0;
        var nums = new int[n];
        var inputs = br.readLine().split(" ");
        
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(inputs[i]);
            max = Math.max(max, nums[i]);
            map.put(nums[i], 0);
        }

        for (int i = 0; i < n; i++) {
            var num = nums[i];
            for (int j = num * 2; j <= max; j += num) {
                if (!map.containsKey(j)) {
                    continue;
                }

                map.put(j, map.get(j) - 1);
                map.put(num, map.get(num) + 1);
            }
        }

        for (var num : nums) {
            bw.write(map.get(num) + " ");
        }
        bw.flush();

    }
}