import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var set = new HashSet<Integer>();
        var inputNums = br.readLine().split(" ");
        int n = Integer.parseInt(inputNums[0]), m = Integer.parseInt(inputNums[1]);

        if (n >= m) {
            bw.write(n - m + " \n" + 1);
        } else {
            int time = 0, count = 1;
            var q = new LinkedList<Integer>();
            q.add(m);

            Loop:
            while (!q.isEmpty()) {
                var size = q.size();

                var tempSet = new HashSet<Integer>();
                for (int i = 0; i < size; i++) {
                    var num = q.poll();

                    if (num == n) {
                        break Loop;
                    }

                    if (num % 2 == 0 && !set.contains(num / 2)) {
                        q.add(num / 2);
                        tempSet.add(num / 2);
                    }

                    if (num - 1 >= 0 && !set.contains(num - 1)) {
                        q.add(num - 1);
                        tempSet.add(num - 1);
                    }

                    if (num + 1 <= 100000 && !set.contains(num + 1)) {
                        q.add(num + 1);
                        tempSet.add(num + 1);
                    }
                }

                set.addAll(tempSet);

                time++;
            }

            while (!q.isEmpty()) {
                if (n == q.poll()) {
                    count++;
                }
            }

            bw.write(time + "\n" + count);
        }

        bw.flush();

    }

}
