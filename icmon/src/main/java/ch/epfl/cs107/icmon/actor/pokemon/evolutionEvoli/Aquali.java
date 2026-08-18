package ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        21/12/2023
 */

import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Aquali extends EvolutionEvoli {
    private Area owner;

    private DiscreteCoordinates coordinates;
    public Aquali(Area owner, DiscreteCoordinates coordinates) {
        super(owner, coordinates, "aquali");
        this.coordinates = coordinates;
        this.owner = owner;
    }
}
