

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var n = Integer.parseInt(br.readLine());
        var schedules = new ArrayList<Schedule>();
        var map = new HashMap<String, Integer>();
        var possibleSchedules = new ArrayList<Schedule>();

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            schedules.add(new Schedule(inputs[0],
                    Integer.parseInt(inputs[1]),
                    Integer.parseInt(inputs[2]),
                    Integer.parseInt(inputs[3]))
            );
        }

        Collections.sort(schedules);

        for (int i = 0; i < n; i++) {
            var inputs = br.readLine().split(" ");

            map.put(inputs[0], Integer.parseInt(inputs[1]));
        }

        possibleSchedules.add(new Schedule("", 0, 0, 0));
        for (int i = 0; i < n; i++) {
            var schedule = schedules.get(i);

            if (map.get(schedule.name) >= schedule.price) {
                possibleSchedules.add(schedule);
            }
        }

        int index = 1, result = 0, count = 0;
        Loop:
        for (int week = 1; week <= 10; week++) {
            for (int day = 0; day <= 6; day++) {
                if (index == possibleSchedules.size()) {
                    break Loop;
                }

                var preSchedule = possibleSchedules.get(index - 1);
                var schedule = possibleSchedules.get(index);

                if (schedule.week == week && schedule.day == day) {
                    count++;
                    result = Math.max(result, count);
                    do {
                        index++;
                        if (index == possibleSchedules.size()) {
                            break Loop;
                        }

                        preSchedule = possibleSchedules.get(index - 1);
                        schedule = possibleSchedules.get(index);
                    } while (preSchedule.week == schedule.week && preSchedule.day == schedule.day);
                } else {
                    count = 0;
                }
            }
        }

        bw.write(result + "\n");
        bw.flush();
    }

    static class Schedule implements Comparable<Schedule> {

        String name;
        int week, day, price;

        public Schedule(String name, int week, int day, int price) {
            this.name = name;
            this.week = week;
            this.day = day;
            this.price = price;
        }

        @Override
        public int compareTo(Schedule schedule) {
            if (this.week == schedule.week) {
                return this.day - schedule.day;
            }

            return this.week - schedule.week;
        }
    }
}

