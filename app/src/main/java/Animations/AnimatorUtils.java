package Animations;


/**
 *
 * This class is directly taken from https://github.com/ogaclejapan/ArcLayout/blob/master/demo/src/main/java/com/ogaclejapan/arclayout/demo/AnimatorUtils.java
 * and is used to create animations with an ObjectAnimator which operates on several different properties in parallel
 *
 */

import android.animation.PropertyValuesHolder;

public class AnimatorUtils {

    private static final String ROTATION = "rotation";
    private static final String SCALE_X = "scaleX";
    private static final String SCALE_Y = "scaleY";
    private static final String TRANSLATION_X = "translationX";
    private static final String TRANSLATION_Y = "translationY";

    /**
     * Gets the values required for a rotation
     * @param values - The rotation values
     * @return - The values holder for a rotation
     */
    public static PropertyValuesHolder rotation(float... values) {
        return PropertyValuesHolder.ofFloat(ROTATION, values);
    }

    /**
     * Gets the values required for an x translation
     * @param values - The values by which x is translated
     * @return - The values holder for an x translation
     */
    public static PropertyValuesHolder translationX(float... values) {
        return PropertyValuesHolder.ofFloat(TRANSLATION_X, values);
    }

    /**
     * Gets the values required for an y translation
     * @param values - The values by which y is translated
     * @return - The values holder for an y translation
     */
    public static PropertyValuesHolder translationY(float... values) {
        return PropertyValuesHolder.ofFloat(TRANSLATION_Y, values);
    }

    /**
     * Gets the values required for an x scale
     * @param values - The values by which x is scaled
     * @return - The values holder for an x scale
     */
    public static PropertyValuesHolder scaleX(float... values) {
        return PropertyValuesHolder.ofFloat(SCALE_X, values);
    }

    /**
     * Gets the values required for a y scale
     * @param values - The values by which y is scaled
     * @return - The values holder for a y scale
     */
    public static PropertyValuesHolder scaleY(float... values) {
        return PropertyValuesHolder.ofFloat(SCALE_Y, values);
    }
}
