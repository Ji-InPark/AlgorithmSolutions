

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var messiGimossi = " Messi Gimossi";
        var n = Integer.parseInt(br.readLine());
        var arr = new ArrayList<Long>();
        arr.add(5L);
        arr.add(13L);

        while (arr.get(arr.size() - 1) < n) {
            var index = arr.size();

            arr.add(arr.get(index - 1) + arr.get(index - 2) + 1);
        }

        var index = arr.size() - 1;
        while (n > 13) {
            if (n > arr.get(index)) {
                if (n == arr.get(index) + 1) {
                    n = 0;
                    continue;
                }
                n -= arr.get(index) + 1;
            }
            index--;
        }

        var result = messiGimossi.charAt(n);
        if (result == ' ') {
            bw.write("Messi Messi Gimossi");
        } else {
            bw.write(result + "\n");
        }
        bw.flush();
    }

}

