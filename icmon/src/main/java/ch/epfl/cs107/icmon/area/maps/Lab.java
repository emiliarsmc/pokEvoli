package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        19/12/2023
 */

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Nymphali;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Voltali;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Lab extends ICMonArea {

    @Override
    public String getTitle() {
        return "lab";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door("town", new DiscreteCoordinates(15, 23),this, new DiscreteCoordinates(6, 1),new DiscreteCoordinates(7,1)));
        registerActor(new Voltali(this, new DiscreteCoordinates(13, 6)));
        registerActor(new Nymphali(this, new DiscreteCoordinates(0, 2)));
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 15);
    }
}
