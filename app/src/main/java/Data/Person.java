package Data;

/**
 *
 * This class creates a staff person with an image and description stored
 */

public class Person {

    private int imageResId;
    private int titleResId;

    /**
     * Gets the image id relating to the person
     * @return - The image id
     */
    public int getImageResId() {
        return imageResId;
    }

    /**
     * Gets the title id relating to the person
     * @return - The title id
     */
    public int getTitleResId() {
        return titleResId;
    }

    /**
     * Sets up the person with an image and title
     * @param imageResId - The image relating to the person
     * @param titleResId - The title relating to the person
     */
    public Person(int imageResId, int titleResId){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
    }
}
