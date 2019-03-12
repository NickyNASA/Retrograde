package com.nicky.retrograde.api.helpers;

import com.nicky.retrograde.common.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public final class RenderHelper
{
	
	public static ResourceLocation makeGuiTextureLocation(String name)
	{
		return new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + name + ".png");
	}
	
	public static TextureManager engine()
	{
		return Minecraft.getMinecraft().getTextureManager();
	}
	
	public static void bindTexture(ResourceLocation texture)
	{
		engine().bindTexture(texture);
	}
}
