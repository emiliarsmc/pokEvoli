package ch.epfl.cs107.icmon.handler;

import ch.epfl.cs107.icmon.actor.Door;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.Sign;
import ch.epfl.cs107.icmon.actor.items.ICBall;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.Oak;
import ch.epfl.cs107.icmon.area.ICMonBehavior;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;

public interface ICMonInteractionVisitor extends AreaInteractionVisitor {

    // must provide a default definition of interaction methods of any ICMon game Interactor

    @Override
    default void interactWith(Interactable other, boolean isCellInteraction) {
    }

    /**
     * lets the main player interact with an ICMonPlayer object
     * @param other
     * @param isCellInteraction
     */
    default void interactWith(ICMonPlayer other, boolean isCellInteraction) {

    }

    /**
     * lets the main player interact with a cell
     * @param other
     * @param isCellInteraction
     */
    default void interactWith(ICMonBehavior.ICMonCell other, boolean isCellInteraction) {
    }

    /**
     * lets the main player interact with an icBall
     * @param other
     * @param isCellInteraction
     */
    default void interactWith(ICBall other, boolean isCellInteraction) {

    }

    /**
     * lets the main player interact with an assistant
     * @param other
     * @param isCellInteraction
     */
    default void interactWith(ICShopAssistant other, boolean isCellInteraction) {
    }

    /**
     * lets the main player interact with a door
     * @param other
     * @param isCellInteraction
     */
    default void interactWith(Door other, boolean isCellInteraction) {

    }

    /**
     * lets the main player interact with a sign
     * @param other
     * @param isCellInteraction
     */
    default void interactWith(Sign other, boolean isCellInteraction) {

    }

    /**
     * lets the main player interact with professor oak
     * @param other
     * @param isCellInteraction
     */
    default void interactWith(Oak other, boolean isCellInteraction) {

    }

}
