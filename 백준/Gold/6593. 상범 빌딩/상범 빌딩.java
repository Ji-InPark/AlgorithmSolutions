import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1, 0, 0};
    static int[] dy = new int[]{1, 0, -1, 0, 0, 0};
    static int[] dz = new int[]{0, 0, 0, 0, 1, -1};
    static int Z, Y, X;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Loop:
        while (true) {
            var inputs = br.readLine().split(" ");
            Z = Integer.parseInt(inputs[0]);
            Y = Integer.parseInt(inputs[1]);
            X = Integer.parseInt(inputs[2]);

            if (Z == 0 && Y == 0 && X == 0) {
                break;
            }

            var isWall = new boolean[Z][Y][X];
            Point startPoint = null, endPoint = null;

            for (int z = 0; z < Z; z++) {
                for (int y = 0; y < Y; y++) {
                    inputs = br.readLine().split("");

                    for (int x = 0; x < X; x++) {
                        var c = inputs[x];
                        if (c.equals("S")) {
                            startPoint = new Point(z, y, x);
                        } else if (c.equals("E")) {
                            endPoint = new Point(z, y, x);
                        } else {
                            isWall[z][y][x] = c.equals("#");
                        }
                    }
                }
                br.readLine();
            }

            var q = new LinkedList<Point>();
            q.add(startPoint);

            int level = 0;
            while (!q.isEmpty()) {
                var size = q.size();

                for (int i = 0; i < size; i++) {
                    var now = q.poll();

                    if (endPoint.z == now.z && endPoint.y == now.y && endPoint.x == now.x) {
                        bw.write("Escaped in " + level + " minute(s).\n");
                        continue Loop;
                    }

                    isWall[now.z][now.y][now.x] = true;

                    for (int j = 0; j < 6; j++) {
                        int nz = now.z + dz[j], ny = now.y + dy[j], nx = now.x + dx[j];
                        if (isValid(nz, ny, nx) && !isWall[nz][ny][nx]) {
                            q.add(new Point(nz, ny, nx));
                            isWall[nz][ny][nx] = true;
                        }
                    }
                }

                level++;
            }
            bw.write("Trapped!\n");
        }

        bw.flush();
    }

    private static boolean isValid(int z, int y, int x) {
        return z >= 0 && z < Z && y >= 0 && y < Y && x >= 0 && x < X;
    }

    static class Point {

        int z, x, y;

        public Point(int z, int y, int x) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }
}
