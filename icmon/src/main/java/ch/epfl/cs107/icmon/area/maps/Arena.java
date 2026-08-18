package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        19/12/2023
 */

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.pokemon.Bulbizarre;
import ch.epfl.cs107.icmon.actor.pokemon.Latios;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Givrali;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Pyroli;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Voltali;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Arena extends ICMonArea {

    @Override
    public String getTitle() {
        return "arena";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door("town", new DiscreteCoordinates(20, 15),this, new DiscreteCoordinates(4, 1),new DiscreteCoordinates(5,1)));
        registerActor(new Bulbizarre(this, new DiscreteCoordinates(6,6)));
        registerActor(new Pyroli(this, new DiscreteCoordinates(0, 3)));
        registerActor(new Givrali(this, new DiscreteCoordinates(8, 3)));
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 15);
    }
}
