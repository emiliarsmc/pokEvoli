package ch.epfl.cs107.icmon.area.maps;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        21/12/2023
 */

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.icmon.actor.pokemon.Pokemon;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.EvolutionEvoli;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Mentali;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Noctali;
import ch.epfl.cs107.icmon.actor.pokemon.evolutionEvoli.Voltali;
import ch.epfl.cs107.icmon.area.ICMonArea;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.Background;
import ch.epfl.cs107.play.engine.actor.Foreground;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class PlayerHouse extends ICMonArea {
    @Override
    public String getTitle() {
        return "house";
    }

    @Override
    protected void createArea() {
        registerActor(new Background(this));
        registerActor(new Foreground(this));
        registerActor(new Door("town", new DiscreteCoordinates(20,7),this, new DiscreteCoordinates(3, 1),new DiscreteCoordinates(4,1)));
        registerActor(new Noctali(this, new DiscreteCoordinates(7, 4)));
        registerActor(new Mentali(this, new DiscreteCoordinates(4, 3)));
    }



    @Override
    public DiscreteCoordinates getPlayerSpawnPosition() {
        return new DiscreteCoordinates(5, 15);
    }

    // links
    public class evoliEventState {


    }


}