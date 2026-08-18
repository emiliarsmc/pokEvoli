package ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        21/12/2023
 */

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Nymphali extends EvolutionEvoli {

    private Area owner;

    private DiscreteCoordinates coordinates;
    public Nymphali(Area owner, DiscreteCoordinates coordinates) {
        super(owner, coordinates, "nymphali");
        this.coordinates = coordinates;
        this.owner = owner;
    }
}
