package it.meal.unibz.mseunibzmeal;

import android.support.test.espresso.core.deps.guava.collect.Multimap;

import org.junit.Test;
import org.junit.Ignore;


public class RoomsTest {

    @Ignore
    public void testRoomsCreation(){
        Rooms rooms = new Rooms();
        String[] str = rooms.getListOfUnibzRooms();
        for(int i=0;i<str.length;i++){
            System.out.println(str[i]);
        }

    }


}
