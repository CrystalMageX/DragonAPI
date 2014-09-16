/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2014
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import Reika.DragonAPI.Auxiliary.DebugOverlay;
import Reika.DragonAPI.Auxiliary.PlayerModelRenderer;
import Reika.DragonAPI.IO.DirectResourceManager;
import cpw.mods.fml.client.FMLClientHandler;

public class APIProxyClient extends APIProxy {

	public static KeyBinding key_nbt;

	@Override
	public void registerSounds() {
		//MinecraftForge.EVENT_BUS.register(new SoundLoader(ReactorCraft.instance, SoundRegistry.soundList));
	}

	@Override
	public void registerSidedHandlers() {

		//Minecraft mc = Minecraft.getMinecraft();
		//mc.mcResourceManager = new CustomResourceManager((SimpleReloadableResourceManager)mc.mcResourceManager);

		MinecraftForge.EVENT_BUS.register(DebugOverlay.instance);

		//MinecraftForge.EVENT_BUS.register(PlayerModelRenderer.instance);
		PlayerModelRenderer.instance.register();
		//MinecraftForge.EVENT_BUS.register(CustomSoundHandler.instance);

		//key_nbt = new KeyBinding("TileEntity NBT Overlay", Keyboard.KEY_TAB, "DragonAPI");
		//ClientRegistry.registerKeyBinding(key_nbt);
	}

	@Override
	public void registerSidedHandlersMain() {
		SimpleReloadableResourceManager rm = (SimpleReloadableResourceManager)Minecraft.getMinecraft().getResourceManager();
		rm.registerReloadListener(DirectResourceManager.getInstance());
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

}
