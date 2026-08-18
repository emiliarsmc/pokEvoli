package ch.epfl.cs107.icmon.gamelogic.events;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        05/12/2023
 */

import ch.epfl.cs107.icmon.ICMon;
import ch.epfl.cs107.icmon.actor.ICMonPlayer;
import ch.epfl.cs107.icmon.actor.npc.ICShopAssistant;
import ch.epfl.cs107.icmon.gamelogic.actions.Action;
import ch.epfl.cs107.icmon.gamelogic.actions.RegisterEventAction;
import ch.epfl.cs107.icmon.gamelogic.actions.UnRegisterEventAction;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.engine.Updatable;

import java.util.ArrayList;

public abstract class ICMonEvent implements Updatable, ICMonInteractionVisitor {
    @Override
    public abstract void update(float deltaTime);

    protected final ICMonPlayer mainPlayer ;


    /**
     * lists of started, completed, suspended and resumed events
     */
    ArrayList <Action> ListEventStarted = new ArrayList<>();
    ArrayList <Action> ListEventCompleted = new ArrayList<>();
    ArrayList <Action> ListEventSuspended = new ArrayList<>();
    ArrayList <Action> ListEventResumed = new ArrayList<>();

    public ICMonEvent(ICMonPlayer mainPlayer, ICMon.ICMonEventManager icMon){
        this.mainPlayer = mainPlayer;
        onStart(new RegisterEventAction(this, icMon));
        onComplete(new UnRegisterEventAction(this, icMon));
    }

    boolean started = false;
    boolean completed = false;
    boolean suspended = false;

    /**
     * starts all the events from the ListEventStarted list
     */
    public void start(){
        if(!started){
            for(Action action: ListEventStarted) {
                action.perform();
            }
            started = true;
        }
    }

    /**
     * completes all the events from the ListEventComplete list
     */
    public void complete(){
        if(!completed && started){
            for(Action action: ListEventCompleted) {
                action.perform();
            }
            completed = true;
        }
    }

    /**
     * suspends all the events from the ListEventSuspend list
     */
    public void suspend(){
        if(!completed && !suspended && started){
            for(Action action: ListEventSuspended) {
                action.perform();
            }
            suspended = true;
        }
    }

    /**
     * resumes all the events from the ListEventResumed list
     */
    public void resume(){
        if(!completed && suspended && started){
            for(Action action: ListEventResumed) {
                action.perform();
            }
            suspended = false;
        }
    }


    /**
     * add the started/completed events to the started/completed events list
     * @param action
     */
    public void onStart(Action action){
        ListEventStarted.add(action);
    }
    public void onComplete(Action action){
        ListEventCompleted.add(action);
    }
    public void OnSuspension(Action action){
        ListEventSuspended.add(action);
    }
    public void onResume(Action action){ListEventResumed.add(action);}

    /**
     * @param assistant
     * @param isCellInteraction
     */
    public abstract void interactWith(ICShopAssistant assistant, boolean isCellInteraction);
}
