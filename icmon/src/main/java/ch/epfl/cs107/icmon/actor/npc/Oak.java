package ch.epfl.cs107.icmon.actor.npc;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        21/12/2023
 */

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

public class Oak extends NPCActor {

    // Constructor
    public Oak(Area owner) {
        super(owner, Orientation.DOWN, new DiscreteCoordinates(1,3), "actors/prof-oak");

    }

    //indicates that the player agrees to have his interactions managed by an interaction manager
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }



}

