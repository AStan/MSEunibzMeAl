package it.meal.unibz.mseunibzmeal;

import android.support.test.espresso.core.deps.guava.collect.ArrayListMultimap;
import android.support.test.espresso.core.deps.guava.collect.Multimap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Represents all the possibly available rooms at the UNIBZ campus.
 */
public class Rooms {

    private static final String[] unibzRooms = {"E211","E212","E220","E221","E223","E231","E311","E312","E320","E321","E322","E323","E331","E411","E412","E420","E421","E423","E431","E511","E520","E521","E522","E523","E531"};
    private ArrayList<Room> rooms;

    /**
     * Creates a default list of Rooms
     */
    public Rooms(){

        int nrRooms = unibzRooms.length;
        rooms = new ArrayList<>();

        for(int i=0;i<nrRooms;i++){
            Room room = new Room(unibzRooms[i]);
            rooms.add(room);
        }
    }

    public String[] getListOfUnibzRooms() {
        return unibzRooms;
    }

    public Room getRoom(int i){
        Room room = this.rooms.get(i);
        return room;

    }

    public int getRoomsLength() {
        return rooms.size();
    }


    /*
    public Multimap<String, String> fetchTimetable(String URL){
        Multimap<String,String> finalList = ArrayListMultimap.create();
    }*/
}
