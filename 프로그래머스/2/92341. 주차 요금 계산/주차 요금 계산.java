import java.util.*;

class Solution {
    public final int LAST_MINUTE = 1439;
    
    class Info {
        int carNo, totalTime, enterTime;
        boolean isEntered;
        
        public Info(int carNo) {
            this.carNo = carNo;
            this.totalTime = 0;
            this.enterTime = 0;
            this.isEntered = true;
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        var map = new TreeMap<Integer, Info>();
        
        for(int i = 0; i < records.length; i++) {
            var infos = records[i].split(" ");
            
            var time = extractMinutes(infos[0]);
            var carNo = Integer.parseInt(infos[1]);
            var isEntered = infos[2].equals("IN");
            
            var info = map.getOrDefault(carNo, new Info(carNo));
            if(isEntered) 
                info.enterTime = time;
            else 
                info.totalTime += time - info.enterTime;

            info.isEntered = isEntered;
            map.put(carNo, info);
        }
        
        var result = new ArrayList<Integer>();
        for(var entry : map.entrySet()) {
            var info = entry.getValue();
            
            if(info.isEntered) info.totalTime += LAST_MINUTE - info.enterTime;
            
            var fee = calculateFee(fees, info.totalTime);
            result.add(fee);
        }
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int extractMinutes(String time) {
        var times = time.split(":");
        
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }
    
    private int calculateFee(int[] fees, int totalTime) {
        var defaultTime = fees[0];
        var defaultFee = fees[1];
        var unitTime = fees[2];
        var unitFee = fees[3];
        
        totalTime -= defaultTime;
        
        if(totalTime <= 0) return defaultFee;
        
        return defaultFee + (int)Math.ceil(totalTime / (double)unitTime) * unitFee;
    }
}