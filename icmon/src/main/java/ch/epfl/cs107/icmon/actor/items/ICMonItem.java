package ch.epfl.cs107.icmon.actor.items;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        01/12/2023
 */

import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.Area;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.engine.actor.RPGSprite;
import ch.epfl.cs107.play.engine.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Orientation;
import ch.epfl.cs107.play.window.Canvas;

public abstract class  ICMonItem extends CollectableAreaEntity implements Interactable {


    private final Sprite sprite;

    public ICMonItem (Area owner, DiscreteCoordinates coordinates,String spriteName){
        super(owner, Orientation.DOWN, coordinates);
        this.sprite = new RPGSprite(spriteName , 1f, 1f, this);
    }


    /**
     *
     * @return true if the object is not traversable
     */
    public boolean takeCellSpace() {
        return true;
    }


    /**
     * draws the sprite of the object
     * @param canvas target, not null
     */
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
   }


    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }



    /**
     * agrees to have his interactions managed by an interaction manager of type ICMonInteractionVisitor
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction
     */
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
    }
}
