package ch.epfl.cs107.icmon.actor.npc;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        07/12/2023
 */

import ch.epfl.cs107.icmon.actor.ICMonActor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class NPCActor extends ICMonActor {

    private final Sprite sprite;


    public NPCActor(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
        super(owner, orientation, coordinates);
        this.sprite = new RPGSprite(spriteName, 1, 1.3125f, this , new RegionOfInterest(0, 0, 16,
                21));
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }




   @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

}
