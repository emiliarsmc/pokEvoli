package ch.epfl.cs107.icmon.gamelogic.actions;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        04/12/2023
 */

public class LogAction implements Action{

    private String message;

    public LogAction(String message){
        this.message = message ;
    }
    @Override
    public void perform() {

        System.out.println(message);

    }
}
