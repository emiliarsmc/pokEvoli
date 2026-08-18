package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        07/12/2023
 */

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Actor;

public class RegisterInAreaAction implements Action {
    private Area owner;
    private Actor actor ;


    public RegisterInAreaAction(Area owner, Actor actor){
        this.owner =owner;
        this.actor = actor;
    }

    @Override
    public void perform() {
        owner.registerActor(actor);
    }

}
