import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static final String ERROR_MESSAGE = "Error!";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var input = br.readLine();
        var isCpp = input.contains("_");

        var sb = new StringBuilder();

        if (isCpp) {
            if (!isValidCpp(input)) {
                sb.append(ERROR_MESSAGE);
            } else {
                var flag = false;
                for (var c : input.toCharArray()) {
                    if (c == '_') {
                        if (flag) {
                            sb = new StringBuilder(ERROR_MESSAGE);
                            break;
                        }

                        flag = true;
                        continue;
                    }

                    if (flag) {
                        sb.append((char) (c - 32));
                    } else {
                        sb.append(c);
                    }

                    flag = false;
                }
            }
        } else {
            if (!isValidJava(input)) {
                sb.append(ERROR_MESSAGE);
            } else {
                for (var c : input.toCharArray()) {
                    if (c < 91) {
                        sb.append("_");
                        sb.append((char) (c + 32));
                        continue;
                    }
                    sb.append(c);
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean isValidJava(String str) {
        var isFirstUpperCase = str.charAt(0) < 91;

        return !isFirstUpperCase;
    }

    private static boolean isValidCpp(String str) {
        var isFirstUnderBar = str.charAt(0) == '_';
        var isLastUnderBar = str.charAt(str.length() - 1) == '_';
        var isAnyUpperCase = str.chars().anyMatch(c -> c < 91);

        return !isFirstUnderBar && !isLastUnderBar && !isAnyUpperCase;
    }
}
