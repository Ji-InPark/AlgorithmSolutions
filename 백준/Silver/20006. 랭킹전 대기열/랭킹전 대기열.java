

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int m;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var numInputs = br.readLine().split(" ");
        int p = Integer.parseInt(numInputs[0]);
        m = Integer.parseInt(numInputs[1]);
        var rooms = new ArrayList<Room>();

        for (int i = 0; i < p; i++) {
            var inputs = br.readLine().split(" ");
            var player = new Player(Integer.parseInt(inputs[0]), inputs[1]);

            var room = findJoinableRoom(rooms, player);
            if (room == null) {
                rooms.add(new Room(player));
            } else {
                room.addPlayer(player);
            }
        }

        for (var room : rooms) {
            bw.write(room.isFull() ? "Started!\n" : "Waiting!\n");
            Collections.sort(room.players);

            for (var player : room.players) {
                bw.write(player.level + " " + player.name + "\n");
            }
        }

        bw.flush();
    }

    private static Room findJoinableRoom(ArrayList<Room> rooms, Player p) {
        for (var room : rooms) {
            if (!room.isFull() && room.isJoinable(p)) {
                return room;
            }
        }

        return null;
    }

    public static class Player implements Comparable<Player> {

        int level;
        String name;

        public Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player p) {
            return this.name.compareTo(p.name);
        }
    }

    public static class Room {

        int level;
        ArrayList<Player> players = new ArrayList<>();

        public Room(Player p) {
            this.level = p.level;
            addPlayer(p);
        }

        public boolean isJoinable(Player p) {
            return (this.level - 10) <= p.level && p.level <= (this.level + 10);
        }

        public boolean isFull() {
            return players.size() == m;
        }

        public void addPlayer(Player p) {
            players.add(p);
        }
    }
}
