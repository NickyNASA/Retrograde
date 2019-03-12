package com.nicky.retrograde.api.helpers;

import java.awt.Point;

import com.nicky.retrograde.api.util.Vector3;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public final class GuiHelper
{
	public static final int GUI_MEDIUM_X = 0;
	public static final int GUI_MEDIUM_Y = 14;
	
	public static final int BLUEPRINT_TABLE_X = 0;
	public static final int BLUEPRINT_TABLE_Y = 38;
	
	public static final int CIRCUIT_ASSEMBLER_X = 0;
	public static final int CIRCUIT_ASSEMBLER_Y = 38;
	
	public static final int CIRCUIT_ASSEMBLER_ADVANCED_X = 0;
	public static final int CIRCUIT_ASSEMBLER_ADVANCED_Y = 38;
	
	public static final int PART_ASSEMBLER_X = 0;
	public static final int PART_ASSEMBLER_Y = 0;
	
	//public static final int BLUEPRINT_TABLE_X = 0;
	
	public static void drawLine(Point p1, Point p2)
	{
		Vector3 start = new Vector3(p1);
		Vector3 end = new Vector3(p2);
		
		Vector3 diff = end.copy().sub(start);
		Vector3 dx = diff.copy().rotateZ90().normalize(5);
		
		Vector3 corner = start.copy().add(dx);
		
		Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.color(1.0F, 0.0F, 0.0F);
        buffer.begin(7, DefaultVertexFormats.POSITION);
        buffer.pos(start.getX() + 5, start.getY(), 0.0D).endVertex();
        buffer.pos(start.getX() - 5, start.getY(), 0.0D).endVertex();
        buffer.pos(end.getX() - 5, end.getY(), 0.0D).endVertex();
        buffer.pos(end.getX() + 5, end.getY(), 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
		
	}
}
