package cn.mmf.blessedsmith;

import cn.mmf.blessedsmith.blades.BladeLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public final class BlessedSmithCreativeTabs extends CreativeTabs {

	public BlessedSmithCreativeTabs() {
		super(BlessedSmith.MOD_ID);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BladeLoader.blade);
	}

}
