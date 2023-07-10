package net.mcreator.penismod.procedure;

import net.minecraftforge.fml.common.FMLCommonHandler;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.server.MinecraftServer;

import net.mcreator.penismod.ElementsPenismod;

@ElementsPenismod.ModElement.Tag
public class ProcedureSkalaThisEntityKillsAnotherOne extends ElementsPenismod.ModElement {
	public ProcedureSkalaThisEntityKillsAnotherOne(ElementsPenismod instance) {
		super(instance, 21);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		{
			MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
			if (mcserv != null)
				mcserv.getPlayerList().sendMessage(new TextComponentString("\u041E\u0442\u0441\u043E\u0441\u0438)))"));
		}
	}
}
