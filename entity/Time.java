package entity;


import main.GameData;
import main.Updatable;

/**
 * Time
 */
public class Time implements Updatable{

    public int day;
    public int hour; 
    public int minute;

    

    long lasttime = System.currentTimeMillis() / GameData.timeFactor;
    long now;
    long delta;
    public Time() {
        GameData.updateManager.addUpdatable(this);
    }


    public void setTimeFactor(int newFactor) {
        lasttime = lasttime * GameData.timeFactor;
        lasttime = lasttime / newFactor;
        GameData.timeFactor = newFactor;
    }
    
    @Override
    public void run() {

            now = System.currentTimeMillis()/GameData.timeFactor;
            delta = now - lasttime;
            minute += delta;
            if(minute>59)
            {
                minute = 0; 
                hour++;
            }
            if(hour>23)
            {
                hour = 0; 
                day++;
                
            }
                
            lasttime = now;

        }
        
    @Override
    public boolean hasFullyUpdated() {
        return true;
    }
    @Override
    public void reset() {
        now = System.currentTimeMillis()/GameData.timeFactor;
        delta = now - lasttime;
        minute += delta;
        if(minute>59)
        {
            minute = 0; 
            hour++;
        }
        if(hour>23)
        {
            hour = 0; 
            day++;
            
        }
            
        lasttime = now;
        
    }
    public long getTotalMinutes() {
        return day * 24 * 60 + hour * 60 + minute;
    }
    public long getTotalHours() {
        return day * 24 + hour;
    }


}
