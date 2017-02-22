package it.meal.unibz.mseunibzmeal;

import android.support.test.espresso.core.deps.guava.collect.Multimap;
import android.support.test.espresso.core.deps.guava.collect.MultimapBuilder;

import org.junit.Test;
import org.junit.Ignore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FreeRoomsActivityTest {

    @Test
    public void testFetchRooms(){
        String siteUrl = "http://aws.unibz.it/risweb/timetable.aspx?start=08.03.2017&end=08.03.2017&showtype=0";
        Multimap<String, String> busyRooms = FreeRoomsActivity.fetchRooms(siteUrl);

        /*
        EXAMPLE of iteration of a Multimap

        for (String firstName : multimap.keySet()) {
        List<String> lastNames = multimap.get(firstName);
        out.println(firstName + ": " + lastNames);
        }
         */

        System.out.println("FULL Multimap______"+busyRooms+"\n\r");

        /*
        //FIRST PRINTIN EXPERIMENT
        for (String keyz : busyRooms.keySet()) {
            Collection<String> valz = busyRooms.get(keyz);
            System.out.println("Room: "+ keyz);
            Object[] a = valz.toArray();
            for(int j=0;j<a.length;j++){
                System.out.println("Value ["+j+"] of "+keyz+" = "+a[j]);
            }
        }
        */

        //SORT
        /*
        System.out.println("\n\r"+"SORTING______"+busyRooms+"\n\r");
        Collection<String> coll = busyRooms.keySet();
        List<String> list = new ArrayList<>(coll);
        Collections.sort(list);
        for(int j=0;j<coll.size();j++){
            System.out.println("Value ["+j+"] of "+list.get(j));
        }
        */


        //SORTING 2
        System.out.println("\n\r"+"SORTING__2______"+busyRooms+"\n\r");
        Collection<String> coll = busyRooms.keySet();
        List<String> list = new ArrayList<>(coll);
        Collections.sort(list);

        for(int j=0;j<coll.size();j++){
            System.out.println("Room: "+list.get(j));
            Collection<String> valz = busyRooms.get(list.get(j));
            Object[] a = valz.toArray();
            for(int k=0;k<a.length;k++){
                System.out.println("Values: "+a[k]);
            }
        }


    }



    @Test
    public void testChangeOfOccupationTime(){

        Rooms rooms = new Rooms();
        System.out.println(rooms.getRoom(0).getRoomName());
        System.out.println(rooms.getRoom(0).getTimeSlot().getOccupationOfSlot(0));
        System.out.println(rooms.getRoom(0).getTimeSlot().getOccupationOfSlot(1));
        //CHANGE
        rooms.getRoom(0).getTimeSlot().setOccupationOfSlot(0,true);
        rooms.getRoom(0).getTimeSlot().setOccupationOfSlot(1,true);
        System.out.println("AFTER CHANGE\n\r");
        System.out.println(rooms.getRoom(0).getRoomName());
        System.out.println(rooms.getRoom(0).getTimeSlot().getOccupationOfSlot(0));
        System.out.println(rooms.getRoom(0).getTimeSlot().getOccupationOfSlot(1));

    }


    @Test
    public void testRoomsPopulation(){

        String siteUrl = "http://aws.unibz.it/risweb/timetable.aspx?start=08.03.2017&end=08.03.2017&showtype=0";
        Multimap<String, String> busyRooms = FreeRoomsActivity.fetchRooms(siteUrl);

        //create ROOMS
        Rooms rooms = FreeRoomsActivity.populateRooms(busyRooms);


        String[] str = rooms.getListOfUnibzRooms();
        for(int i=0;i<str.length;i++){
            TimeSlot t = rooms.getRoom(i).getTimeSlot();
            System.out.println(str[i]);
            System.out.println(t.getFreeTime());
        }


    }

    @Test
    public void testFreeRooms(){

        String siteUrl = "http://aws.unibz.it/risweb/timetable.aspx?start=08.03.2017&end=08.03.2017&showtype=0";
        Multimap<String, String> busyRooms = FreeRoomsActivity.fetchRooms(siteUrl);

        //create ROOMS
        Rooms rooms = FreeRoomsActivity.populateRooms(busyRooms);

        //compute FREE TIME
        Multimap<String, ArrayList<String>> freeRooms = FreeRoomsActivity.computeFreeTimeSlots(rooms);

        System.out.println(freeRooms.toString());



        //SORTING
        System.out.println("\n\r"+"SORTING__2______"+freeRooms+"\n\r");
        Collection<String> coll = freeRooms.keySet();
        List<String> list = new ArrayList<>(coll);
        Collections.sort(list);

        //iterate through the object freeRooms, to display for each ROOM what are the free time slots
        for(int j=0;j<coll.size();j++){
            System.out.println("Room: "+list.get(j));
            Collection<ArrayList<String>> valz = freeRooms.get(list.get(j));
            Object[] a = valz.toArray();
            for(int k=0;k<a.length;k++){
                System.out.println("Values: "+a[k]);
            }
        }


        //////


    }


}
