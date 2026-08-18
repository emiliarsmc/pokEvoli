package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        30/11/2023
 */

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.Sign;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Aquali;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Phylalli;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;


public final class Town extends ICMonArea {

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 15);
    }



    // create a map and register all the actors that are present there
    @Override
    protected void createArea() {
        registerActor(new Door("lab", new DiscreteCoordinates(6, 2), this, new DiscreteCoordinates(15, 24)));
        registerActor(new Door("arena", new DiscreteCoordinates(4, 2), this, new DiscreteCoordinates(20, 16)));
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door("evoliHouse", new DiscreteCoordinates(3, 2), this, new DiscreteCoordinates(7, 27)));
        registerActor(new Sign(this, new DiscreteCoordinates(8,23)));
        registerActor(new Door("shop", new DiscreteCoordinates(3, 2), this, new DiscreteCoordinates(25, 20)));
        registerActor(new Door("house", new DiscreteCoordinates(3, 2), this, new DiscreteCoordinates(20, 8)));
        registerActor(new Sign(this, new DiscreteCoordinates(17,22)));
        registerActor(new Sign(this, new DiscreteCoordinates(17,16)));
        registerActor(new Aquali(this, new DiscreteCoordinates(24,30)));
        registerActor(new Phylalli(this, new DiscreteCoordinates(25,5)));
    }


    @Override
    public String getTitle() {
        return "town";
    }
}
