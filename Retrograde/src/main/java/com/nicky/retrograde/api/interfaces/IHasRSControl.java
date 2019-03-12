package com.nicky.retrograde.api.interfaces;

public interface IHasRSControl
{
	
	
	enum RedstoneControl
	{
		DISABLED,
		LOW,
		HIGH;
		
		
		public boolean isDisabled()
		{
			return this == DISABLED;
		}
		
		public boolean isLow()
		{
			return this == LOW;
		}
		
		public boolean isHigh()
		{
			return this == HIGH;
		}
	}
	
	public boolean shouldRun();
	
	public void setControlMode(RedstoneControl mode);
	
	public RedstoneControl getControlMode();
}
