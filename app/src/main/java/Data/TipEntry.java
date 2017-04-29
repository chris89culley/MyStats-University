package Data;

/**
 * Created by chris on 29/04/17.
 *
 * This class creates a tip entry holding a title, description and icon
 */

public class TipEntry {

    private int tipTitleId;
    private int tipDescriptionId;
    private int tipIconId;


    TipEntry(int titleId, int descriptionId, int iconId){
        this.tipTitleId = titleId;
        this.tipDescriptionId = descriptionId;
        this.tipIconId = iconId;
    }

    public int getTipTitleId{return tipTitleId;}
    public int getTipDescriptionId{return tipDescriptionId;}
    public int getTipIconId{return tipIconId;}
}
