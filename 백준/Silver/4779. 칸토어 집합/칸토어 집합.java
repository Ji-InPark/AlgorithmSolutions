

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        var scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (scanner.hasNextInt()) {
            var num = scanner.nextInt();

            bw.write(solve(num, false) + "\n");
        }

        bw.flush();
    }

    private static String solve(int level, boolean isBlank) {
        var sb = new StringBuilder();
        if (isBlank) {
            for (int i = 0; i < Math.pow(3, level); i++) {
                sb.append(" ");
            }
            return sb.toString();
        }

        if (level == 0) {
            return "-";
        }

        sb.append(solve(level - 1, false));
        sb.append(solve(level - 1, true));
        sb.append(solve(level - 1, false));

        return sb.toString();
    }
}
