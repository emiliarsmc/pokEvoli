package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        17/12/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class RegisterEventAction implements Action {
    private ICMonEvent event;
    private ICMon.ICMonEventManager icMon;

    public RegisterEventAction(ICMonEvent event, ICMon.ICMonEventManager icMon){
        this.event = event;
        this.icMon = icMon;
    }


    @Override
    public void perform() {

        icMon.registerEvent(event);

    }
}
