package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        15/12/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.actor.npc.NPCActor;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;


public class EndOfTheGameEvent extends ICMonEvent implements ICMonInteractionVisitor {
    private final NPCActor npc;

    public EndOfTheGameEvent(ICMonPlayer mainPlayer, NPCActor npc, ICMon.ICMonEventManager icMon) {
        super(mainPlayer, icMon);
        this.npc = npc;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void interactWith(ICShopAssistant assistant , boolean isCellInteraction){
        if(!isCellInteraction){
            if (assistant.isViewInteractable()){
                mainPlayer.openDialog("end_of_game_event_interaction_with_icshopassistant");
                mainPlayer.setDialog(true);
            }
        }

    }
}
