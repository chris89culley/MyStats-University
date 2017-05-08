package Data;

/**
 * This class creates a tip entry holding a title, description and icon
 */

public class TipEntry {

    private int tipTitleId;
    private int tipDescriptionId;
    private int tipIconId;


    /**
     * Creates a tip entry with image, title and description
     * @param titleId - The tip title id
     * @param descriptionId - The tip description id
     * @param iconId - The tip image id
     */
    public TipEntry(int titleId, int descriptionId, int iconId){
        this.tipTitleId = titleId;
        this.tipDescriptionId = descriptionId;
        this.tipIconId = iconId;
    }

    /**
     * Gets the tip title id
     * @return - tip title id
     */
    public int getTipTitleId(){
        return tipTitleId;
    }

    /**
     * Gets the tip description id
     * @return - The tip description id
     */
    public int getTipDescriptionId(){
        return tipDescriptionId;
    }

    /**
     * Gets the tip icon id
     * @return - The tip icon id
     */
    public int getTipIconId(){
        return tipIconId;
    }
}
