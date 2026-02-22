package org.codekart.algo.lineSweep;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendarTwo {
    // 731. My Calendar II

    /*
     * Implement a MyCalendarTwo class to store your events. A new event can be
     * added if adding the event will not cause a triple booking.
     * 
     * Your class will have one method, book(int start, int end). Formally, this
     * represents a booking on the half open interval [start, end), the range of
     * real numbers x such that start <= x < end.
     * 
     * A triple booking happens when three events have some non-empty intersection
     * (i.e., there is some time that is common to all three events.)
     * 
     * For each call to the method MyCalendar.book, return true if the event can
     * be added to the calendar successfully without causing a triple booking.
     * Otherwise,
     * return false and do not add the event to the calendar.
     */

    Map<Integer, Integer> events; // Key: time, Value: change in number of ongoing events at that time

    public MyCalendarTwo() {
        events = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        events.put(startTime, events.getOrDefault(startTime, 0) + 1);
        events.put(endTime, events.getOrDefault(endTime, 0) - 1);

        int ongoingEvents = 0;
        for (int count : events.values()) {
            ongoingEvents += count;
            if (ongoingEvents >= 3) {
                events.put(startTime, events.get(startTime) - 1);
                events.put(endTime, events.get(endTime) + 1);
                return false;
            }
        }
        return true;
    }

    // MyCalendar3

    public int bookKEvent(int startTime, int endTime) {
        events.put(startTime, events.getOrDefault(startTime, 0) + 1);
        events.put(endTime, events.getOrDefault(endTime, 0) - 1);

        int ongoingEvents = 0;
        int maxOngoingEvents = 0;
        for (int count : events.values()) {
            ongoingEvents += count;
            maxOngoingEvents = Math.max(maxOngoingEvents, ongoingEvents);
        }
        return maxOngoingEvents;
    }

}
