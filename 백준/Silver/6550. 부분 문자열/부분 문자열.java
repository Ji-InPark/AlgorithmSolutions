

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        var scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (scanner.hasNext()) {
            var first = scanner.next() + " ";
            var second = scanner.next();

            int index = 0;
            for (var c : second.toCharArray()) {
                if (c == first.charAt(index)) {
                    index++;
                }
            }

            if (index == first.length() - 1) {
                bw.write("Yes\n");
            } else {
                bw.write("No\n");
            }
        }

        bw.flush();
    }
}
