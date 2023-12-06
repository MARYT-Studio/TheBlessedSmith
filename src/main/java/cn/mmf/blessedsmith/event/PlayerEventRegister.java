package cn.mmf.blessedsmith.event;

import cn.mmf.blessedsmith.BlessedSmithConfig;
import cn.mmf.blessedsmith.advancement.AdvancementHelper;
import cn.mmf.blessedsmith.util.IMultiModeBlade;
import mods.flammpfeil.slashblade.ItemSlashBladeNamed;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PlayerEventRegister {
	@SubscribeEvent
	public static void onDoSlashBladeAttack(UseSlashBladeEvent.doAttackEvent event) {
		ItemStack blade = event.getBlade();
		NBTTagCompound nbt = ItemSlashBlade.getItemTagCompound(blade);
		if(ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("slashblade.named.thousand")) {
			if(((IMultiModeBlade)blade.getItem()).getMode(blade)!=1)
			event.setCanceled(true);
		}
		if (!BlessedSmithConfig.slashblade_broken_blade_attack_enable) {
			if(ItemSlashBlade.IsBroken.get(nbt)) {
				event.setCanceled(true);
			}
		}
		if (!BlessedSmithConfig.advanced_mode)
			return ;
		
		if (BlessedSmithConfig.slashblade_action_cooldown_enable && BlessedSmithConfig.slashblade_action_cooldown > 0) {
			int timer = event.getComboSeq().comboResetTicks;
			if(timer < 10) timer -=2;
			else if(timer > 20)timer +=2;
			event.getPlayer().getCooldownTracker().setCooldown(blade.getItem(), 
					(int)(timer * BlessedSmithConfig.slashblade_action_cooldown));
		}
	}
	@SubscribeEvent
	public static void onDoSA(UseSlashBladeEvent.doSpacialAttackEvent event) {
		if (!BlessedSmithConfig.advanced_mode)
			return ;
		ItemStack blade = event.getBlade();
		NBTTagCompound nbt = ItemSlashBlade.getItemTagCompound(blade);
		if(BlessedSmithConfig.sa_lock_enable) {
			if(!AdvancementHelper.getInstance().checkAdvancement(event.getPlayer(), "bewitched_blade"))
				event.setCanceled(true);
		}
		if(ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("slashblade.named.thousand")) {
			if(((IMultiModeBlade)blade.getItem()).getMode(blade)!=1)
			event.setCanceled(true);
		}
	}
	@SubscribeEvent
	public static void onDoRange(UseSlashBladeEvent.doRangeAttackEvent event) {
		if (!BlessedSmithConfig.advanced_mode)
			return ;
		ItemStack blade = event.getBlade();
		NBTTagCompound nbt = ItemSlashBlade.getItemTagCompound(blade);
		if(BlessedSmithConfig.sb_lock_enable) {
			if(!AdvancementHelper.getInstance().checkAdvancement(event.getPlayer(), "sharpness"))
				event.setCanceled(true);
		}
		if(ItemSlashBladeNamed.CurrentItemName.get(nbt).equalsIgnoreCase("slashblade.named.thousand")) {
			if(((IMultiModeBlade)blade.getItem()).getMode(blade)!=1)
			event.setCanceled(true);
		}
	}
	
}
