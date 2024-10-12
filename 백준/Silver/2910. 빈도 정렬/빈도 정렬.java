import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine();
        var inputNums = br.readLine().split(" ");
        var nums = new ArrayList<Integer>();
        for (var inputNum : inputNums) {
            nums.add(Integer.parseInt(inputNum));
        }

        var map = new HashMap<Integer, Info>();

        for (var num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new Info(num, map.size()));
            }

            var info = map.get(num);
            info.count++;
        }

        var infos = new ArrayList<>(map.values());
        Collections.sort(infos);

        for (var info : infos) {
            for (int i = 0; i < info.count; i++) {
                bw.write(info.num + " ");
            }
        }

        bw.write("\n");
        bw.flush();

    }

    static class Info implements Comparable<Info> {

        int index, count = 0, num;

        public Info(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Info info) {
            if (this.count == info.count) {
                return this.index - info.index;
            }

            return info.count - this.count;
        }
    }

}
