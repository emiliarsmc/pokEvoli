package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        17/12/2023
 */

import ch.epfl.cs107.icmon.gamelogic.events.ICMonEvent;

public class StartEventAction implements Action {


    private ICMonEvent event;


    public StartEventAction(ICMonEvent event){
        this.event = event;
    }



    @Override
    public void perform() {

        event.start();

    }
}
