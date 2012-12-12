package Tux2.TuxTwoLib;

import java.awt.Color;

import net.minecraft.server.NBTTagCompound;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * Class, that allows setting and getting color of the leather armor.
 */
public class Armor {

	/**
	 * Sets the color.
	 *
	 * @param item
	 * item to color
	 * @param color
	 * color
	 * @return colored item or null if item is not applicable or color is not
	 * valid
	 */
	public static ItemStack setColor(ItemStack item, int color) {
		if (!isApplicable(item) || !isValidColor(color)) return null;
		CraftItemStack craftStack = null;
		net.minecraft.server.ItemStack itemStack = null;
		if (item instanceof CraftItemStack) {
			craftStack = (CraftItemStack) item;
			itemStack = craftStack.getHandle();
		}
		else if (item instanceof ItemStack) {
			craftStack = new CraftItemStack(item);
			itemStack = craftStack.getHandle();
		}
		NBTTagCompound tag = itemStack.tag;
		if (tag == null) {
			tag = new NBTTagCompound();
			tag.setCompound("display", new NBTTagCompound());
			itemStack.tag = tag;
		}

		tag = itemStack.tag.getCompound("display");
		tag.setInt("color", color);
		itemStack.tag.setCompound("display", tag);
		return craftStack;
	}

	/**
	 * Checks if color is valid.
	 *
	 * @param color
	 * color to check
	 * @return true, if is valid
	 */
	public static boolean isValidColor(int color) {
		return color <= 0xFFFFFF;
	}

	/**
	 * Checks if item is applicable.
	 *
	 * @param item
	 * the item to check
	 * @return true, if is applicable
	 */
	public static boolean isApplicable(ItemStack item) {
		switch (item.getType()) {
		case LEATHER_BOOTS:
		case LEATHER_CHESTPLATE:
		case LEATHER_HELMET:
		case LEATHER_LEGGINGS:
			return true;
		default:
			return false;
		}
	}

	/**
	 * Sets the color.
	 *
	 * @param item
	 * item to color
	 * @param color
	 * color
	 * @return colored item
	 */
	public static ItemStack setColor(ItemStack item, Color color) {
		return setColor(item, color.getRGB());
	}

	/**
	 * Sets the color.
	 *
	 * @param item
	 * item to color
	 * @param color
	 * color
	 * @return colored item
	 */
	public static ItemStack setColor(ItemStack item, ArmorColor color) {
		return setColor(item, color.getColor());
	}

	/**
	 * Sets the color.
	 *
	 * @param item
	 * item to color
	 * @param colorStr
	 * color
	 * @return colored item
	 */
	public static ItemStack setColor(ItemStack item, String colorStr) {
		int color = Integer.decode(colorStr);
		return setColor(item, color);
	}

	/**
	 * Sets the color.
	 *
	 * @param item
	 * item to color
	 * @param colorR
	 * amount of red
	 * @param colorG
	 * amount of green
	 * @param colorB
	 * amount of blue
	 * @return colored item
	 */
	public static ItemStack setColor(ItemStack item, int colorR, int colorG,
			int colorB) {
		int color = Integer
				.decode(ColorConverter.toHex(colorR, colorG, colorB));
		return setColor(item, color);
	}

	/**
	 * Gets the color.
	 *
	 * @param item
	 * colored item
	 * @return color
	 */
	public static int getColor(ItemStack item) {
		if (!isApplicable(item)) return -1;
		CraftItemStack craftStack = null;
		net.minecraft.server.ItemStack itemStack = null;
		if (item instanceof CraftItemStack) {
			craftStack = (CraftItemStack) item;
			itemStack = craftStack.getHandle();
		}
		else if (item instanceof ItemStack) {
			craftStack = new CraftItemStack(item);
			itemStack = craftStack.getHandle();
		}
		NBTTagCompound tag = itemStack.tag;
		if (tag == null) {
			tag = new NBTTagCompound();
			tag.setCompound("display", new NBTTagCompound());
			itemStack.tag = tag;
			return -1;
		}

		tag = itemStack.tag.getCompound("display");
		return tag.getInt("color");
	}
}
