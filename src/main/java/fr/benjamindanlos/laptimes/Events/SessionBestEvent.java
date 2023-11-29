package fr.benjamindanlos.laptimes.Events;

import fr.benjamindanlos.laptimes.Utils.Tools;

public class SessionBestEvent extends LaptimeEvent{
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.driver)
            .append(" just recorded the session's best lap of ")
            .append(Tools.laptimeToString(this.laptime))
            .append(" on game and track ")
            .append(this.game)
            .append("#")
            .append(this.track)
            .append(" with car ")
            .append(this.car);
        
        return sb.toString();
    }

    @Override
    public Type type(){
        return Type.SessionBest;
    }
}
