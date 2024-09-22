import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            var points = new int[4][2];

            for (int i = 0; i < 4; i++) {
                var inputs = br.readLine().split(" ");

                points[i][0] = Integer.parseInt(inputs[0]);
                points[i][1] = Integer.parseInt(inputs[1]);
            }

            Arrays.sort(points, (o1, o2) -> {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }

                return o1[0] - o2[0];
            });

            if (points[0][0] == points[1][0]) {
                int d1 = points[1][1] - points[0][1], d2 = points[2][0] - points[0][0];
                int d3 = points[3][0] - points[1][0], d4 = points[3][1] - points[2][1];

                var isSameDistance = d1 == d2 && d2 == d3 && d3 == d4;

                var isSquare = isSameDistance &&
                        points[0][1] == points[2][1] &&
                        points[1][1] == points[3][1] &&
                        points[2][0] == points[3][0];

                bw.write(isSquare ? "1\n" : "0\n");
            } else {
                int xInc1 = points[1][0] - points[0][0], xInc2 = points[3][0] - points[2][0];
                int yInc1 = points[1][1] - points[0][1], yInc2 = points[3][1] - points[2][1];
                int xInc3 = points[2][0] - points[0][0], xInc4 = points[3][0] - points[1][0];
                int yInc3 = points[2][1] - points[0][1], yInc4 = points[3][1] - points[1][1];

                int xDInc1 = points[3][0] - points[0][0], xDInc2 = points[2][0] - points[1][0];
                int yDInc1 = points[3][1] - points[0][1], yDInc2 = points[2][1] - points[1][1];

                var isParallel1 = xInc1 == xInc2 && yInc1 == yInc2;
                var isParallel2 = xInc3 == xInc4 && yInc3 == yInc4;

                int d1 = xInc1 * xInc1 + yInc1 * yInc1, d2 = xInc3 * xInc3 + yInc3 * yInc3;
                int d3 = xInc2 * xInc2 + yInc2 * yInc2, d4 = xInc4 * xInc4 + yInc4 * yInc4;

                int dd1 = xDInc1 * xDInc1 + yDInc1 * yDInc1, dd2 = xDInc2 * xDInc2 + yDInc2 * yDInc2;

                var isSameDistance = d1 == d2 && d3 == d4 && d2 == d3 && dd1 == dd2;

                var isSquare = isParallel1 && isParallel2 && isSameDistance;
                bw.write(isSquare ? "1\n" : "0\n");
            }
        }

        bw.flush();
    }
}
