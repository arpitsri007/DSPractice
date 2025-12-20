package org.codekart.designDataStructure;

import java.util.HashMap;
import java.util.Map;
// TAG - Google

public class Logger {
    /**
     * Design a logger system that receives a stream of messages along with their
     * timestamps. Each unique message should only be printed at most every 10
     * seconds (i.e. a message printed at timestamp t will prevent other identical
     * messages from being printed until timestamp t + 10).
     * 
     * All messages will come in chronological order. Several messages may arrive at
     * the same timestamp.
     * 
     * Implement the Logger class:
     * 
     * Logger() Initializes the logger object.
     * bool shouldPrintMessage(int timestamp, string message) Returns true if the
     * message should be printed in the given timestamp, otherwise returns false.
     * If this method returns false, the message will not be printed. The timestamp
     * is in seconds granularity.
     * 
     * 0 <= timestamp <= 109
     * Every timestamp will be passed in non-decreasing order (chronological order).
     * 1 <= message.length <= 30
     * At most 104 calls will be made to shouldPrintMessage.
     */

    Map<String, Integer> map;
    public Logger() {
        map = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!map.containsKey(message)) {
            map.put(message, timestamp);
            return true;
        }

        int lastPrintedTime = map.get(message);
        if (timestamp - lastPrintedTime >= 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }

    

}
