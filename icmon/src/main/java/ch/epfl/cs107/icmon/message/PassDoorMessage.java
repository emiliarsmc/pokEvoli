package ch.epfl.cs107.icmon.message;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        19/12/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;

public class PassDoorMessage extends GamePlayMessage{
    private ICMon.ICMonGameState icMonGameState;
    private Door door;


    public PassDoorMessage(Door door, ICMon.ICMonGameState icMonGameState) {
        this.door = door;
        this.icMonGameState = icMonGameState;
    }



    // actions allowing the player to pass through a door.
    public void process(){
        icMonGameState.switchArea(door.getCurrentCells(), door.getDestination(), door.getCoordDestination());
    }

}

