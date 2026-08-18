package ch.epfl.cs107.icmon.actor;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        19/12/2023
 */

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Door extends AreaEntity {
    private String destination; //the name of the area to which it wants transit
    private DiscreteCoordinates coordDestination ; //arrival coordinates in the destination area

    private DiscreteCoordinates mainCoords;   // main cell

    private ArrayList<DiscreteCoordinates> otherPositions = new ArrayList();


    // Constructor
    public Door(String destination, DiscreteCoordinates coordDestination, Area owner, DiscreteCoordinates mainCoords){
        super(owner, Orientation.UP, coordDestination );
        this.destination = destination;
        this.coordDestination = coordDestination;
        this.otherPositions.add(mainCoords);
        this.mainCoords = mainCoords;
    }



    // Constructor
    public Door(String destination, DiscreteCoordinates coordArrive, Area owner, DiscreteCoordinates mainCoords, DiscreteCoordinates... otherPositions){
        this(destination, coordArrive, owner, mainCoords);
        this.otherPositions.addAll(Arrays.asList(otherPositions));

    }

    /**
     *
     * @return destination of the player
     */
    public String getDestination() {
        return destination;
    }


    /**
     *
     * @return destination coordinates
     */
    public DiscreteCoordinates getCoordDestination() {
        return coordDestination;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return otherPositions;
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
        return false;
    }



    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
