

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInput = br.readLine().split(" ");
        long a = Long.parseLong(numInput[0]), b = Long.parseLong(numInput[1]), div = 1;

        while (div <= b) {
            var aAfter = findAfter(a, div);
            var bBefore = findBefore(b, div);
            
            if (aAfter < bBefore) {
                var diff = bBefore - aAfter;
                var count = (diff / (div * 2)) * div;
                result += count;
            }

            div *= 2;
        }

        bw.write(result + "\n");
        bw.flush();
    }

    private static long findAfter(long num, long div) {
        long share = num / div, remain = num % div;

        if (share % 2 == 1) {
            result -= remain;
            return div * share;
        } else {
            return div * (share + 1);
        }
    }

    private static long findBefore(long num, long div) {
        long share = num / div, remain = num % div;

        if (share % 2 == 1) {
            result += remain + 1;
            return div * share;
        } else {
            return div * (share + 1);
        }
    }

}
