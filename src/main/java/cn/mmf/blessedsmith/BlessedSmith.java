package cn.mmf.blessedsmith;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = BlessedSmith.MOD_NAME, version = Tags.VERSION,
dependencies="required-after:slashblade@[mc1.12-r40,);required-after:mm_lib;after:sakura;after:thaumcraft;after:slashblade_addon")
public class BlessedSmith {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = "The Blessed Smith";
    public static final String VERSION = Tags.VERSION;

    public static Logger LOGGER;

	@SidedProxy(clientSide = "cn.mmf.blessedsmith.ClientProxy",serverSide = "cn.mmf.blessedsmith.CommonProxy")
	public static CommonProxy proxy; 
 
   
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		LOGGER = event.getModLog();
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
}
