package ch.epfl.cs107.icmon.actor;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        01/12/2023
 */

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;

import java.util.Collections;
import java.util.List;

public abstract class ICMonActor extends MovableAreaEntity {

    public ICMonActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates) {
        super(owner, orientation, coordinates);
        resetMotion();
    }
    @Override
    public boolean takeCellSpace() {
        return false;
    }


    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }


    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    /**
     * allows the player to leave the current area
     */
    public void leaveArea() {
        getOwnerArea().unregisterActor(this);
    }

    /**
     * allows the player to enter a new area
     * @param area
     * @param position
     */
    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        area.setViewCandidate(this);
        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetMotion();
    }

    /**
     * focuses the camera on the main player
     */
    public void centerCamera() {
        getOwnerArea().setViewCandidate(this);
    }

}


