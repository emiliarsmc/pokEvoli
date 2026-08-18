package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        05/12/2023
 */


import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.items.ICMonItem;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;

public class CollectItemEvent extends ICMonEvent implements ICMonInteractionVisitor {

    private final ICMonItem item;

    public CollectItemEvent(ICMonPlayer mainPlayer, ICMonItem item, ICMon.ICMonEventManager icMon) {
        super(mainPlayer, icMon);
        this.item = item;
    }


    //The update method of CollectItemEvent consists of completing the event when the ICMonItem is marked as collected.
    @Override
    public void update(float deltaTime) {
        if (item.isCollected()) {
            this.complete();
        }

    }

    @Override
    public void interactWith(ICShopAssistant assistant, boolean isCellInteraction) {
        if (!isCellInteraction) {
            if (assistant.isViewInteractable()) {
                System.out.println("This is an interaction between the player and ICShopAssistant based on events !");
                mainPlayer.setDialog(true);
                mainPlayer.openDialog("collect_item_event_interaction_with_icshopassistant");
            }


        }

    }
}

