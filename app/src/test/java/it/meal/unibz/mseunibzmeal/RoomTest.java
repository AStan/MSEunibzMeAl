package it.meal.unibz.mseunibzmeal;

import android.support.test.espresso.core.deps.guava.collect.ArrayListMultimap;
import android.support.test.espresso.core.deps.guava.collect.Multimap;

import org.junit.Test;
import org.junit.Ignore;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by AlexSSD1
 */
/*
public class RoomTest {

    @Ignore

    public void runRooms(){

        Room room1 = new Room("E221");
        Room room2 = new Room("E331");
        Room room3 = new Room("E420");

        //fake busy rooms from URL
        Multimap<String, String> finalList = ArrayListMultimap.create();
        finalList.put("E221","10.30-12:30");//1st
        finalList.put("E002","10.30-12:30");
        finalList.put("E221","14.00-16:00");//2nd
        finalList.put("E003","08.30-10:30");
        finalList.put("E004","16.00-18:00");
        finalList.put("E221","18.00-20:00");//3rd

        //create the ROOMS that will be used to provide overall information
        Rooms actualRooms = new Rooms();

        int sizeFinalList = finalList.size();
        String[] actualUnibzRooms = actualRooms.getListOfUnibzRooms();
        int sizeOfUnibzRooms = actualUnibzRooms.length;




        //CHANGE THE BUSY TIME
        room1.addBusyTime(finalList);


        // Get a set of the entries
        Set set = room1.getEntrySet();
        Iterator i = set.iterator();

        while (i.hasNext()){
            Map.Entry me = (Map.Entry)i.next();
            System.out.print("KEY: "+me.getKey() + ": VALUE: ");
            System.out.println(me.getValue());
        }
        System.out.println();


        Set set2 = room2.getEntrySet();


    }

}*/
