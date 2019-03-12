package com.nicky.retrograde.api.interfaces;

public interface IProcessingTile
{
	public boolean isProcessing();
	
	public boolean canProcess();
	
	public void startProcess();
	
	public void finishProcess();
	
	public void getRecipe();
}
