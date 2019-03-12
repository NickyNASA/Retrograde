package com.nicky.retrograde.api.helpers;

public final class MathHelper
{
	
	public static int constrain(int x, int min, int max)
	{
		return Math.max(Math.min(x, max), min);
	}
}
