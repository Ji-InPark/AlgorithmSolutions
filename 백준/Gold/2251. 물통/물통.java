import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {

    static boolean[] results = new boolean[201];
    static HashSet<Integer> visited = new HashSet<>();
    static int a, b, c;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var inputs = br.readLine().split(" ");
        a = Integer.parseInt(inputs[0]);
        b = Integer.parseInt(inputs[1]);
        c = Integer.parseInt(inputs[2]);

        solve(0, 0, c);

        for (int i = 0; i <= 200; i++) {
            if (results[i]) {
                bw.write(i + " ");
            }
        }

        bw.flush();
    }

    static void solve(int curA, int curB, int curC) {
        if (visited.contains(curA * 1000000 + curB * 1000 + curC) || curA < 0 || curB < 0 || curC < 0) {
            return;
        }

        if (curA > a || curB > b || curC > c) {
            return;
        }

        if (curA == 0) {
            results[curC] = true;
        }

        visited.add(curA * 1000000 + curB * 1000 + curC);

        if (a - curA <= curB) {
            solve(a, curB - (a - curA), curC);
        } else {
            solve(curA + curB, 0, curC);
        }
        if (a - curA <= curC) {
            solve(a, curB, curC - (a - curA));
        } else {
            solve(curA + curC, curB, 0);
        }

        if (b - curB <= curA) {
            solve(curA - (b - curB), b, curC);
        } else {
            solve(0, curB + curA, curC);
        }
        if (b - curB <= curC) {
            solve(curA, b, curC - (b - curB));
        } else {
            solve(curA, curB + curC, 0);
        }

        if (c - curC <= curA) {
            solve(curA - (c - curC), curB, c);
        } else {
            solve(0, curB, curC + curA);
        }
        if (c - curC <= curB) {
            solve(curA, curB - (c - curC), c);
        } else {
            solve(curA, 0, curC + curB);
        }
    }
}
