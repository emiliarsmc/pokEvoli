package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        20/12/2023
 */

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class EvoliHouse extends ICMonArea {
    public String getTitle() {
        return "evoliHouse";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door("town", new DiscreteCoordinates(7, 26),this, new DiscreteCoordinates(3, 1),new DiscreteCoordinates(4,1)));
    }

    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 15);
    }
}

