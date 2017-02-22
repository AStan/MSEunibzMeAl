package it.meal.unibz.mseunibzmeal;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

/**
 * TimeSlot class to handle TimeSlots of a typical UNIBZ lecture day.
 */

public class TimeSlotTest {

    @Ignore
    public void checkLenghtsAndInitialization(){
        TimeSlot t = new TimeSlot();
        System.out.println(t.getOccupiedSize());
        System.out.println(t.getTimeSlotSize());

/*
        System.out.println(t.getOccupied());
        System.out.println(t.getTimeSlot());

        boolean[] b = t.getOccupied();
        for(int i = 0; i<b.length;i++){
            System.out.println("Occupies is equal to: "+b[i]);//expected all false
        }

        double[] d = t.getTimeSlot();
        for(int i = 0; i<d.length;i++){
            System.out.println("Time slot value equals to: "+d[i]);//expected all false
        }*/
    }

    @Test
    public void testBusyTime(){

        TimeSlot t1 = new TimeSlot();

        //t1.addBusyTime("08:30-10:00");
        t1.addBusyTime("10:30-12:30");
        t1.addBusyTime("14:00-16:00");
        //t1.addBusyTime("16:00-18:00");
        t1.addBusyTime("18:00-20:00");


        boolean[] b = t1.getOccupations();
        String[] d = t1.getTimeSlots();
        for(int i = 0; i<b.length;i++){
            System.out.println("Time slot: "+d[i]+"__ Occupied: "+b[i]);
        }
    }

    @Ignore
    public void getFreeTime(){

        TimeSlot t1 = new TimeSlot();
        //TimeSlot t3 = new TimeSlot();

        //t1.addBusyTime("08:30-10:00");
        t1.addBusyTime("10:30-12:30");
        t1.addBusyTime("14:00-16:00");
        //t1.addBusyTime("16:00-18:00");
        t1.addBusyTime("18:00-20:00");

        boolean[] b = t1.getOccupations();
        String[] d = t1.getTimeSlots();
        for(int i = 0; i<b.length;i++){
            System.out.println("Time slot: "+d[i]+"__ Occupied: "+b[i]);
        }
        System.out.println("Free Time Slots for this room: \n\r");

        //prints the actual free-time slots for the room
        ArrayList<String> s = t1.getFreeTime();
        for(int i=0;i<s.size();i++){
            System.out.println(s.get(i));
        }


    }
}
