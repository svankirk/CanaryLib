package net.canarymod.api.inventory.helper;

import net.canarymod.api.DyeColor;
import net.canarymod.api.inventory.Item;

/**
 * Leather Armor Helper
 * 
 * @author Jason (darkdiplomat)
 */
public class LeatherArmorHelper extends ItemHelper {
    private LeatherArmorHelper() {} // Class should never be constructed

    /**
     * Checks if the specified {@link Item} is Leather armor
     * 
     * @param leather_armor
     *            the {@link Item} to be checked
     * @return {@code true} if leather armor; {@code false} if not
     */
    public static boolean isLeatherArmor(Item leather_armor) {
        if (leather_armor == null) {
            return false;
        }
        switch (leather_armor.getId()) {
            case 298:
            case 299:
            case 300:
            case 301:
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks if the Leather Armor has color
     * 
     * @param leather_armor
     *            the leather armor to be checked
     * @return {@code true} if colored; {@code false} if not
     */
    public static boolean isColored(Item leather_armor) {
        if (!isLeatherArmor(leather_armor)) {
            return false;
        }
        if (!leather_armor.hasDataTag()) {
            return false;
        }
        if (!leather_armor.getDataTag().containsKey("display")) {
            return false;
        }
        if (!leather_armor.getDataTag().getCompoundTag("display").containsKey("color")) {
            return false;
        }
        return true;
    }

    /**
     * Gets the color of the Leather armor
     * 
     * @param leather_armor
     *            the leather armor to get color of
     * @return the color of the armor or -1 if there is no color
     */
    public static int getColor(Item leather_armor) {
        if (!isLeatherArmor(leather_armor)) {
            return -1;
        }
        if (!leather_armor.hasDataTag()) {
            return -1;
        }
        if (!leather_armor.getDataTag().containsKey("display")) {
            return -1;
        }
        if (!leather_armor.getDataTag().getCompoundTag("display").containsKey("color")) {
            return -1;
        }
        return leather_armor.getDataTag().getCompoundTag("display").getInt("color");
    }

    /**
     * Sets the color of the armor based on an RGB value
     * 
     * @param leather_armor
     *            the leather armor to set color of
     * @param rgb
     *            the RGB color value to set
     */
    public static void setColor(Item leather_armor, int rgb) {
        if (!isLeatherArmor(leather_armor)) {
            return;
        }
        if (!leather_armor.hasDataTag()) {
            leather_armor.setDataTag(TAG.copy());
        }
        if (!leather_armor.getDataTag().containsKey("display")) {
            leather_armor.getDataTag().put("display", NBT_FACTO.newCompoundTag("display"));
        }
        leather_armor.getDataTag().getCompoundTag("display").put("color", Math.max(Math.min(0xFFFFFF, rgb), 0));
    }

    /**
     * Sets the color of the armor with specified red, green, and blue color values
     * 
     * @param leather_armor
     *            the leather armor to set color of
     * @param red
     *            the red color value
     * @param green
     *            the green color value
     * @param blue
     *            the blue color value
     */
    public static void setColor(Item leather_armor, int red, int green, int blue) {
        setColor(leather_armor, DyeColor.rawColorFromRGB(red, green, blue));
    }
}
