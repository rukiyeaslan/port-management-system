
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import interfaces.IPort;
import java.util.ArrayList;
import containers.*;
import ships.Ship;
import java.lang.Math;

/**
 * 
 * @author rukiyeaslan
 *
 */
public class Port implements IPort {
	
	private int ID;
	private double X;
	private double Y;
	private  ArrayList<Container> containers = new ArrayList<Container>();
	private ArrayList<Ship> history = new ArrayList<Ship>();
	private ArrayList<Ship> current = new ArrayList<Ship>();
	
	/**
	 * 
	 * @param ID
	 * @param X
	 * @param Y
	 */
	public Port(int ID, double X, double Y) {
	   this.ID=ID;
	   this.X=X;
	   this.Y=Y;	   
	}
	
	/**
	 * @param s incoming ship
	 * Adds this ship to current ArrayList.
	 */
	@Override
	public void incomingShip(Ship s) {
		if (current.contains(s) == false) {
			current.add(s);
		}
	}
	
	/**
	 * @param s outgoing ship
	 * Adds this ship to history ArrayList.
	 */
	@Override
	public void outgoingShip(Ship s) {
		if (history.contains(s) == false) {
			history.add(s);
		}
	}
	
	/**
	 * 
	 * @param other port to calculate distance
	 * @return the distance between the object itself and another Port
	 */
	
	public double getDistance(Port other) {
		double x = (this.X - other.getX()) * (this.X - other.getX());
		double y = (this.Y - other.getY()) * (this.Y - other.getY());

		return Math.sqrt(x+y);
		
	}

	/**
	 * 
	 * @return X
	 */
	public double getX() {
		return X;
	}
 
	
	/**
	 * 
	 * @return Y
	 */
	public double getY() {
		return Y;
	}


	/**
	 * 
	 * @return ArrayList containers
	 */
	public ArrayList<Container> getContainers() {
		return containers;
	}

	/**
	 * 
	 * @param containers
	 * Sets ArrayList containers to the parameter containers
	 */
	public void setContainers(ArrayList<Container> containers) {
		this.containers = containers;
	}

	/**
	 * 
	 * @return ArrayList history
	 */
	public ArrayList<Ship> getHistory() {
		return history;
	}

	/**
	 * 
	 * @param history
	 * Sets ArrayList history to the parameter history
	 */
	public void setHistory(ArrayList<Ship> history) {
		this.history = history;
	}
	
	/**
	 * 
	 * @return ArrayList current
	 */
	public ArrayList<Ship> getCurrent() {
		return current;
	}

	/**
	 * 
	 * @param current
	 * Sets ArrayList current to the parameter current
	 */
	public void setCurrent(ArrayList<Ship> current) {
		this.current = current;
	}
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

