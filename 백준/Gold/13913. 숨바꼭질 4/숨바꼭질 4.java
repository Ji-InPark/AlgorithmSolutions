import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
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
            bw.write(n - m + " \n");
            for (int i = n; i >= m; i--) {
                bw.write(i + " ");
            }
        } else {
            int time = 0;
            var q = new LinkedList<Info>();
            q.add(new Info(m, new ArrayList<>()));

            Loop:
            while (!q.isEmpty()) {
                var size = q.size();

                for (int i = 0; i < size; i++) {
                    var info = q.poll();
                    var num = info.num;

                    if (num == n) {
                        bw.write(time + "\n");
                        Collections.reverse(info.list);
                        for (var result : info.list) {
                            bw.write(result + " ");
                        }
                        break Loop;
                    }

                    if (num % 2 == 0 && !set.contains(num / 2)) {
                        q.add(new Info(num / 2, info.list));
                        set.add(num / 2);
                    }

                    if (num - 1 >= 0 && !set.contains(num - 1)) {
                        q.add(new Info(num - 1, info.list));
                        set.add(num - 1);
                    }

                    if (num + 1 <= 100000 && !set.contains(num + 1)) {
                        q.add(new Info(num + 1, info.list));
                        set.add(num + 1);
                    }
                }
                time++;
            }

        }

        bw.flush();

    }

    static class Info {

        int num;
        ArrayList<Integer> list = new ArrayList<>();

        public Info(int num, ArrayList<Integer> list) {
            this.num = num;
            this.list.addAll(list);
            this.list.add(num);
        }
    }

}
