package com.nicky.retrograde.api.util;

import java.awt.Point;

public class Vector3
{
	private double x;
	private double y;
	private double z;
	
	public Vector3(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Point p)
	{
		this.x = p.getX();
		this.y = p.getY();
		this.z = 0;
	}
	
	public Vector3 add(Vector3 vec)
	{
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}
	
	public Vector3 add(double x, double y, double z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public Vector3 sub(Vector3 vec)
	{
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}
	
	public Vector3 sub(double x, double y, double z)
	{
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	public Vector3 mult(double k)
	{
		this.x *= k;
		this.y *= k;
		this.z *= k;
		return this;
	}
	
	public Vector3 div(double k)
	{
		this.x /= k;
		this.y /= k;
		this.z /= k;
		return this;
	}
	
	public double dot(Vector3 vec)
	{
		return this.x * vec.x + this.y * vec.y + this.z * vec.z;
	}
	
	public Vector3 cross(Vector3 vec)
	{
		double newX = this.y * vec.z - vec.y * this.z;
		double newY = this.x * vec.z - vec.x * this.z;
		double newZ = this.x * vec.y - vec.x * this.y;
		
		return new Vector3(newX, newY, newZ);
	}
	
	public double size()
	{
		return Math.sqrt(this.sizeSquared());
	}
	
	public double sizeSquared()
	{
		return x*x + y*y + z*z;
	}
	
	public double dist(Vector3 vec)
	{
		return Math.sqrt(this.distSquared(vec));
	}
	
	public double distSquared(Vector3 vec)
	{
		double dx = vec.x - this.x;
		double dy = vec.y - this.y;
		double dz = vec.z - this.z;
		
		return dx*dx + dy*dy + dz*dz;
	}
	
	public Vector3 normalize(){
		double size = this.size();
		
		this.x /= size;
		this.y /= size;
		this.z /= size;
		return this;
	}
	
	public Vector3 normalize(double k)
	{
		return this.normalize().mult(k);
	}
	
	public Vector3 rotateZ90()
	{
		this.x = -this.y;
		this.y = this.x;
		return this;
	}
	
	public double getX(){ return this.x; }
	public double getY(){ return this.y; }
	public double getZ(){ return this.z; }
	public void setX(double x){ this.x = x; }
	public void setY(double y){ this.y = y; }
	public void setZ(double z){ this.z = z; }
	
	public Vector3 copy()
	{
		return new Vector3(this.x, this.y, this.z);
	}
}
