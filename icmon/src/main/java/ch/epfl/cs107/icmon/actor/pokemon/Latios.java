package ch.epfl.cs107.icmon.actor.pokemon;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        19/12/2023
 */

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Latios extends Pokemon{

    private Area owner;

    private DiscreteCoordinates coordinates;

    public Latios(Area owner, DiscreteCoordinates coordinates) {
        super(owner, coordinates, "bulbizarre", 10, 1);
        this.owner = owner;
        this.coordinates = coordinates;

    }
}
