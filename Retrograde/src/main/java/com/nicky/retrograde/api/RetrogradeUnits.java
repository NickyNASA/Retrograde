package com.nicky.retrograde.api;

public class RetrogradeUnits
{
	
	
	public enum Temperature
	{
		KELVIN("Kelvin", "K", 0, 1),
		CELSIUS("Celsius", "°C", 273.15, 1),
		FAHRENHEIT("Fahrenheit", "°F", 459.67, 9D/5D);
		
		public String name;
		public String symbol;
		public double start;
		public double size;
		
		Temperature(String name, String symbol, double base, double size)
		{
			this.name = name;
			this.symbol = symbol;
			this.start = base;
			this.size = size;
		}
		
		public double fromKelvin(double temp)
		{
			return (temp * this.size) - this.start;
		}
		
		public double toKelvin(double temp)
		{
			return (temp + this.start) / this.size;
		}
	}
}
