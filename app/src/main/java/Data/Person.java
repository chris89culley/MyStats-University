package Data;

/**
 * Created by chris on 26/04/17.
 *
 * This class creates a staff person with an image and description stored
 */

public class Person {

    public int imageResId;
    public int titleResId;

    public Person(int imageResId, int titleResId){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
    }
}
