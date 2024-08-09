import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var dirs = new int[][]{{1, 2}, {2, 1}, {2, -1}, {1, -2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        var T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            var n = Integer.parseInt(br.readLine());
            var dp = new boolean[n][n];

            var inputArray = br.readLine().split(" ");
            var q = new LinkedList<Point>();
            var startPoint = new Point(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]));
            inputArray = br.readLine().split(" ");
            var endPoint = new Point(Integer.parseInt(inputArray[0]), Integer.parseInt(inputArray[1]));
            q.add(startPoint);
            dp[startPoint.x][startPoint.y] = true;

            var result = 0;
            Loop:
            while (!q.isEmpty()) {
                var size = q.size();
                for (int i = 0; i < size; i++) {
                    var point = q.poll();

                    if (endPoint.equals(point)) {
                        break Loop;
                    }

                    for (var dir : dirs) {
                        if (!isValidPoint(n, point.x + dir[0], point.y + dir[1])
                                || dp[point.x + dir[0]][point.y + dir[1]]) {
                            continue;
                        }

                        dp[point.x + dir[0]][point.y + dir[1]] = true;
                        q.add(new Point(point.x + dir[0], point.y + dir[1]));
                    }

                }
                result++;
            }
            bw.write(result + "\n");
        }

        bw.flush();

    }

    public static boolean isValidPoint(int n, int x, int y) {
        return !(x < 0 || x >= n || y < 0 || y >= n);
    }

    public static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean equals(Point point) {
            return this.x == point.x && this.y == point.y;
        }
    }

}
