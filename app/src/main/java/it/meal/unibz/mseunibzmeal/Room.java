package it.meal.unibz.mseunibzmeal;


/**
 * Represents a room of the UNIBZ campus. A room has a name(indicating its position in the building) and certain available hours
 */
public class Room {

    private String roomName;
    private TimeSlot timeSlot;

    /**
     * Default constructor:
     * @param roomName
     */
    public Room(String roomName){
        this.roomName = roomName;
        this.timeSlot = new TimeSlot();
    }

    public String getRoomName() {
        return roomName;
    }
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void addOccupiedTime(String busyTime){
        this.timeSlot.addBusyTime(busyTime);
    }


    /*
    private static double[] convertTime(String time){
        double[] convertedTime = new double[2];
        //time.replace(":",".");
        String[] parts = time.split("-");
        convertedTime[0] = Double.parseDouble(parts[0]);
        convertedTime[1] = Double.parseDouble(parts[1]);
        return convertedTime;
    }*/

}
