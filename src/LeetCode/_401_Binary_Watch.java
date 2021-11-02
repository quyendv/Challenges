/*
    https://leetcode.com/problems/binary-watch/
    https://www.youtube.com/watch?v=yITg6Uk-o8Y&list=PLqLksqdSk4b37pGIyfy_266wP0-S68HDh&index=3
*/
package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _401_Binary_Watch {
//    public static int countBitOne(int num) {
//        int count = 0;
//        while (num != 0) {
//            if ((num & 1) == 1) count++;
//            num = num >> 1;
//        }
//        return count;
//    }

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
//                int totalBitCount = countBitOne(h) + countBitOne(m);
                int totalBitCount = Integer.bitCount(h) + Integer.bitCount(m); // CT nhanh, nhanh hơn dịch bit ở trên
                if (totalBitCount == turnedOn) {
//                    String time = String.format("%1d:%02d", h, m);  // Chú ý String.format giống printf
//                    result.add(time);
                    StringBuilder time = new StringBuilder();
                    time.append(h).append(":");
                    if (m < 10) time.append(0);
                    time.append(m);
                    result.add(time.toString());
                }
            }
        }
        return result;
    }
}
