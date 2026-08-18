package ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        21/12/2023
 */

import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


// type that represent all the evolution of evoli
public class EvolutionEvoli extends Pokemon {
    private Area owner;

    private DiscreteCoordinates coordinates;
    public EvolutionEvoli(Area owner, DiscreteCoordinates coordinates, String spriteName) {
        super(owner, coordinates, spriteName, 10, 1);
        this.coordinates = coordinates;
        this.owner = owner;
    }
}
