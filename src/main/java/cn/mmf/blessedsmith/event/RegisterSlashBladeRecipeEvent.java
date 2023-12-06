package cn.mmf.blessedsmith.event;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class RegisterSlashBladeRecipeEvent extends Event {
	private final FMLPostInitializationEvent event;
	public RegisterSlashBladeRecipeEvent(FMLPostInitializationEvent call_event) {
		event = call_event;
	}
	public FMLPostInitializationEvent getEvent() {
		return event;
	}
}
