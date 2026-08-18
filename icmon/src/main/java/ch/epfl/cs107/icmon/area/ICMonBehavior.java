package ch.epfl.cs107.icmon.area;
/*
 *	Author:      Amandine DEJARDIN
 *	Date:        30/11/2023
 */

import ch.epfl.cs107.icmon.actor.Sign;
import ch.epfl.cs107.icmon.handler.ICMonInteractionVisitor;
import ch.epfl.cs107.play.areagame.actor.Interactable;
import ch.epfl.cs107.play.areagame.area.AreaBehavior;
import ch.epfl.cs107.play.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class ICMonBehavior extends AreaBehavior {
    public ICMonBehavior(Window window, String name) {
        super(window, name);
        int height = getHeight();
        int width = getWidth();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ICMonCellType color = ICMonCellType.toType(getRGB(height - 1 - y, x));
                setCell(x, y, new ICMonCell(x,y,color));
            }
        }
    }

    /**
     * possibilities of movement
     */
    public enum AllowedWalkingType {
        NONE, // None
        SURF, // Only with surf
        FEET, // Only with feet
        ALL // All previous;
    }

    /**
     * types of cells in the game
     */
    public enum ICMonCellType {
        //https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
        NULL(0, AllowedWalkingType.NONE),
        WALL(-16777216, AllowedWalkingType.NONE),
        BUILDING(-8750470, AllowedWalkingType.NONE),
        INTERACT(-256, AllowedWalkingType.NONE),
        DOOR(-195580, AllowedWalkingType.ALL),
        INDOOR_WALKABLE(-1, AllowedWalkingType.FEET),
        OUTDOOR_WALKABLE(-14112955, AllowedWalkingType.FEET),
        WATER(-16776961, AllowedWalkingType.SURF),
        GRASS(-16743680, AllowedWalkingType.FEET);


        private final int type;
        private final AllowedWalkingType isWalkable;

        ICMonCellType(int type, AllowedWalkingType isWalkable) {
            this.type = type;
            this.isWalkable = isWalkable;
        }

        /**
         * Converts an integer representation to an ICMonCellType
         * checks if the type of the current ICMonCellType matches the specified type
         * returns the corresponding ICMonCellType, or ICMonCellType.NULL if no match is found
         * @param type
         * @return
         */
        public static ICMonCellType toType(int type) {
            for (ICMonCellType ict : ICMonCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            System.out.println(type);
            return NULL;
        }

        public AllowedWalkingType getIsWalkable() {
            return isWalkable;
        }
    }

    public class ICMonCell extends AreaBehavior.Cell {
        /// Type of the cell following the enum
        private final ICMonCellType type;

        public ICMonCell(int x, int y, ICMonCellType type) {
            super(x, y);
            this.type = type;
        }

        /**
         * @return type
         */
        public ICMonCellType getType() {
            return type;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }


        // Check if the entity's walking type is ALLOWED (not NONE)
        @Override
        protected boolean canEnter(Interactable entity) {
            if (type.isWalkable == AllowedWalkingType.NONE){
                if(entity instanceof Sign){
                    return true;

                }
                else{
                    return false;
                }
            }
            if (entity.takeCellSpace()) {
                for(Interactable interactable : entities){
                    if(interactable.takeCellSpace()){
                        return false;
                    }
                }
            }
            return true;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
            ((ICMonInteractionVisitor) v).interactWith(this , isCellInteraction);
        }

    }
}
