package ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        21/12/2023
 */

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Noctali extends EvolutionEvoli {
    private Area owner;

    private DiscreteCoordinates coordinates;
    public Noctali(Area owner, DiscreteCoordinates coordinates) {
        super(owner, coordinates, "noctali");
        this.coordinates = coordinates;
        this.owner = owner;
    }
}
