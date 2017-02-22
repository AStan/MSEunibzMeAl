package it.meal.unibz.mseunibzmeal;

import java.util.ArrayList;

/**
 * Created by AlexSSD1
 */

public class TimeSlot {

    private final String[] timeSlot;
    private final boolean[] occupied;

    public TimeSlot(){
        timeSlot = new String[]{"08:00-08:30","08:30-09:00","09:00-09:30","09:30-10:00","10:00-10:30","10:30-11:00","11:00-11:30","11:30-12:00","12:00-12:30","12:30-13:00","13:00-13:30","13:30-14:00","14:00-14:30","14:30-15:00","15:00-15:30","15:30-16:00","16:00-16:30","16:30-17:00","17:00-17:30","17:30-18:00","18:00-18:30","18:30-19:00","19:00-19:30","19:30-20:00","20:00-20:30"};
        occupied = new boolean[25];
    }

    public String[] getTimeSlots() {
        return timeSlot;
    }

    public boolean[] getOccupations() {
        return occupied;
    }

    public int getTimeSlotSize() {
        return timeSlot.length;
    }

    public int getOccupiedSize() {
        return occupied.length;
    }

    public boolean getOccupationOfSlot(int i){
        return occupied[i];
    }

    public void setOccupationOfSlot(int i, boolean freeorbusy){
        this.occupied[i] = freeorbusy;
    }

    /**
     * Add a busy status to a time slot, from a start time, until the finish time.
     */
    public void addBusyTime(String timeslot){

        //format of the String: xx:xx-yy:yy
        int i = 0;
        int size = getTimeSlotSize();
        String[] splt = timeslot.split("-");
        String start = splt[0];
        String finish = splt[1];

        String[] splt2 = this.timeSlot[i].split("-");
        String current = splt2[0];


        while(!current.equals(start)){
            if(i<size-1){
                i++;
                String[] splt3 = this.timeSlot[i].split("-");
                current=splt3[0];
            }
        }

        this.occupied[i]=true;

        while (!current.equals(finish)){
            if(i<size-1){
                i++;
                String[] splt4 = this.timeSlot[i].split("-");
                current=splt4[1];
                this.occupied[i]=true;
            }

        }
    }

    /**
     * Computes the free time slots
     * @return Returns the free time slots, expressed in the format "xx:xx-yy:yy"
     */
    public ArrayList<String> getFreeTime(){

        int sz = this.getTimeSlotSize();
        ArrayList<String> freetime = new ArrayList<>();

        boolean previousIsOccupied = false;
        boolean flag = false;

        ArrayList<String> ar = new ArrayList<String>();

        for(int i=0;i<sz;i++) {

            switch (i){
                case 0:
                    if(this.occupied[i]==false){
                        flag=true;
                        //previous=false;
                        String[] sp = this.timeSlot[i].split("-");
                        ar.add(sp[0]);
                        break;
                    }
                    else{
                        previousIsOccupied=true;
                        break;
                    }

                    default:
                        //am I counting free time slot? (flag=true)
                        if(flag==false){//No, not counting yet
                            //shall I start counting? Let's check if we have a free time slot
                            if(this.occupied[i]==false){//it's free!
                                //let start counting and add this as FIRST free slot
                                flag=true;
                                previousIsOccupied=true;
                                String[] sp = this.timeSlot[i].split("-");
                                ar.add(sp[0]);
                            }
                            //else continue (1 1)
                            else
                                previousIsOccupied=true;
                                break;
                        }
                        //YES - counting
                        else
                        if(this.occupied[i]==false){
                            //1 0
                            if(previousIsOccupied==true){

                                if (i == sz-1) {
                                    previousIsOccupied=false;
                                    flag = false;
                                    String[] sp = this.timeSlot[i].split("-");
                                    ar.add(sp[0]);
                                    freetime.add(ar.get(0).toString()+"-"+ar.get(1).toString());
                                    ar.clear();
                                    break;
                                }
                                else
                                    previousIsOccupied=false;
                            }
                            else //previous is FREE (=false)
                            {
                                if (i == sz-1) {
                                    previousIsOccupied=false;
                                    flag = false;
                                    String[] sp = this.timeSlot[i].split("-");
                                    ar.add(sp[0]);
                                    freetime.add(ar.get(0).toString()+"-"+ar.get(1).toString());
                                    ar.clear();
                                    break;
                                }
                                else
                                    break;
                            }

                        }

                        else //this.occupied[i]==true -
                        //0 1
                        {
                                // 0 1
                                flag = false;
                                previousIsOccupied=true;
                                String[] sp = this.timeSlot[i].split("-");
                                ar.add(sp[0]);
                                freetime.add(ar.get(0).toString()+"-"+ar.get(1).toString());
                                ar.clear();
                        }
            }
        }

        return freetime;
    }

}
