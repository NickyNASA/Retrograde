package com.nicky.retrograde.api.enums;

import net.minecraft.util.IStringSerializable;

public enum MetalVariant implements IStringSerializable
{
	COPPER("copper", 0),
	TIN("tin", 1),
	ALUMINUM("aluminum", 2),
	STEEL("steel", 3),
	TITANIUM("titanium", 4);
	
	private int id;
	private String name;
	
	private MetalVariant(String name, int id)
	{
		this.name = name;
		this.id = id;
	}
	
	@Override
	public String getName()
	{
		return this.name;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	@Override
	public String toString()
	{
		return this.getName();
	}
}
