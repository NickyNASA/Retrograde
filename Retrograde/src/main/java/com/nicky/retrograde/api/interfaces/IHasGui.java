package com.nicky.retrograde.api.interfaces;

public interface IHasGui
{
	/**
	 * Gets the field from the tileentity. Used for syncing data to the GUI. <br>
	 * 0 - Process time <br>
	 * 1 - Process max <br>
	 * 2 - Energy stored <br>
	 * 3 - Max energy stored
	 * 
	 * @param id - The id of the field
	 * @return
	 */
	public int getField(int id);
	
	public int setField(int id, int value);
}
