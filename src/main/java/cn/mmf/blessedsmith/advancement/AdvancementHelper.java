package cn.mmf.blessedsmith.advancement;

import cn.mmf.blessedsmith.BlessedSmith;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class AdvancementHelper {
	private static final AdvancementHelper instance = new AdvancementHelper();
	private AdvancementHelper() {
	}
	public static AdvancementHelper getInstance() {
		return instance;
	}
	public void grantAdvancement(EntityPlayer player, String advancementName) {
		if (!(player instanceof EntityPlayerMP))
			return;

		AdvancementManager manager = player.world.getMinecraftServer().getAdvancementManager();
		Advancement advancement = manager.getAdvancement(new ResourceLocation(BlessedSmith.MOD_ID, advancementName));
		if (advancement == null)
			return;

		((EntityPlayerMP) player).getAdvancements().grantCriterion(advancement, "done");
	}

	public boolean checkAdvancement(EntityPlayer player, String advancementName) {
		return checkAdvancement(player, new ResourceLocation(BlessedSmith.MOD_ID, advancementName));
	}
	public boolean checkAdvancement(EntityPlayer player, ResourceLocation advancement) {
		if (!BlessedSmith.proxy.doesPlayerHaveAdvancement(player, advancement)) {
			return false;
		}
		return true;
	}

}
