package ch.epfl.cs107.icmon.actor.npc;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        07/12/2023
 */

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public class ICShopAssistant extends NPCActor {

    // Constructor
    public ICShopAssistant(Area owner) {
        super(owner, Orientation.DOWN, new DiscreteCoordinates(8,8), "actors/assistant");

    }


    //indicates that the player agrees to have his interactions managed by an interaction manager
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
    ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

}

