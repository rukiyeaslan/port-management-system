
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import interfaces.IShip;
import ports.Port;
import java.util.*;
import containers.*;
/**
 * 
 * @author rukiyeaslan
 *
 */
public class Ship implements IShip {
	
	private int ID;
	

	private double fuel;
	private Port currentPort;
	
	private ArrayList<Container> current = new ArrayList<Container>();
	

	
	public int currentBasic =0;
	public int currentHeavy =0;
	public int currentRefr = 0;
	public int currentLiquid = 0;
	
	private double fuelConsumptionPerKM;  
	private int totalWeightCapacity;
	private int maxNumberOfAllContainers;
	private int maxNumberOfHeavyContainers;
	private int maxNumberOfRefrigeratedContainers;
	private int maxNumberOfLiquidContainers;
	
	
	/**
	 * 
	 * @param ID
	 * @param p
	 * @param totalWeightCapacity
	 * @param maxNumberOfAllContainers
	 * @param maxNumberOfHeavyContainers
	 * @param maxNumberOfRefrigeratedContainers
	 * @param maxNumberOfLiquidContainers
	 * @param fuelConsumptionPerKM
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM){
		this.ID = ID;
		currentPort = p;
		p.getCurrent().add(this);   //check this part again
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;		
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
	
	}
	/**
	 * 
	 * @return the list of all containers currently in the ship sorted by ID
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort( this.current, Comparator.comparing(Container::getID));
		return current;
	}
	
	/**
	 * 
	 * @return remaining weight capacity of the ship
	 */
	public int remainingWeight() {
		int sum = 0;
		for (Container c : current) {
			sum += c.getWeight();
		}
		return (this.totalWeightCapacity - sum);
	}
	
	
	/**
	 * @param p port to be sailed
	 * @return true if a ship successfully sailed to the destination port
	 */
	@Override
	public boolean sailTo(Port p) {
		
	  double distance = currentPort.getDistance(p);
	  double sum = 0.0;
	  for (Container c : current) {
		 sum += c.consumption();
	  }
	  sum += this.fuelConsumptionPerKM;
	  double requiredFuel = distance*sum;
	  if (requiredFuel > fuel) {
		  return false;
	  }
	  else {
		 		
  		currentPort.getCurrent().remove(this);
  		p.getCurrent().add(this);
  		if (!currentPort.getHistory().contains(this)) {
  			currentPort.getHistory().add(this);
  		}
  		setCurrentPort(p);
		  this.fuel -= requiredFuel;
		  return true;
	  }
	}

	/**
	 * @param newFuel amount of fuel to be added
	 * adds fuel to the ship
	 */
	@Override
	public void reFuel(double newFuel) {
		this.fuel += newFuel;
		
	}

	/**
	 * @param cont container to be loaded
	 * @return true if a container was successfully loaded to a ship
	 */
	@Override
	public boolean load(Container cont) {
		if (currentPort.getContainers().contains(cont)) {
			if (cont.getWeight()<= remainingWeight() && this.current.size() <this.maxNumberOfAllContainers) {  //check total weight
        		
     		   if (cont.getClass() == BasicContainer.class) {
     			   current.add(cont);
     			   currentBasic +=1;
     			   this.currentPort.getContainers().remove(cont);
     			  
     			
     		  }
     		   if (cont.getClass() == HeavyContainer.class) {
     			   if (currentHeavy < maxNumberOfHeavyContainers) {
     				   this.current.add(cont);
     				   currentHeavy +=1;
     				   this.currentPort.getContainers().remove(cont);
     				  
     			   }
     		   }
     		   if (cont.getClass()== LiquidContainer.class) {
     			   if (currentLiquid < maxNumberOfLiquidContainers && currentHeavy < maxNumberOfHeavyContainers) {
     				   this.current.add(cont);
     				   currentHeavy+=1; 
     				   currentLiquid +=1;
     				   this.currentPort.getContainers().remove(cont);
     				  
     			   }
     		   }
     		   if (cont.getClass()== RefrigeratedContainer.class) {
     			   if (currentRefr < maxNumberOfRefrigeratedContainers && currentHeavy< maxNumberOfHeavyContainers) {
     				  this.current.add(cont);
     				   currentHeavy +=1;  
     				   currentRefr+=1;
     				   this.currentPort.getContainers().remove(cont);
     				  
     				   
     			   }
     		   }
     		  return true;
     	    }
			else {
				return false;
			}
				
			
		}
		else {
		return false;}
	}

	/**
	 * @param cont container to be unloaded
	 * @return true if a container was successfully unloaded from a ship
	 */ 
	@Override
	public boolean unLoad(Container cont) {
		if (this.current.contains(cont)) {
			if(cont.getClass() == BasicContainer.class) {
				currentBasic-=1;
			}
			if (cont.getClass() == HeavyContainer.class) {
				currentHeavy -=1;
			}
			if (cont.getClass()== LiquidContainer.class) {
				currentLiquid-=1;
				currentHeavy -=1;
			}
			if (cont.getClass()== RefrigeratedContainer.class) {
				currentRefr-=1;
				currentHeavy -=1;
			}
			this.current.remove(cont);    //unloads the container
    		this.currentPort.getContainers().add(cont); 
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 
	 * @return remaining amount of fuel in the ship
	 */
	public double getFuel() {
		return fuel;
	}

	/**
	 * 
	 * @param new amount of the fuel
	 * Sets fuel to the parameter fuel
	 */
	public void setFuel(double fuel) {
		this.fuel = fuel;
	}

	/**
	 * 
	 * @return currentPort that ship is in
	 */
	public Port getCurrentPort() {
		return currentPort;
	}


    /**
     * 
     * @param current Port that ship is in
     * Sets current port of the ship to the parameter currentPort
     */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}
	
	/**
	 * 
	 * @return total weight capacity of the ship
	 */
	public int getTotalWeightCapacity() {
		return totalWeightCapacity;
	}

	
	/**
	 * 
	 * @return max number of all types of containers that ship can carry
	 */
	public int getMaxNumberOfAllContainers() {
		return maxNumberOfAllContainers;
	}

	
	/**
	 * 
	 * @return max number of heavy containers that ship can carry
	 */
	public int getMaxNumberOfHeavyContainers() {
		return maxNumberOfHeavyContainers;
	}
	
	/**
	 * 
	 * @return max number of refrigerated containers that ship can carry
	 */
	public int getMaxNumberOfRefrigeratedContainers() {
		return maxNumberOfRefrigeratedContainers;
	}

	/**
	 * 
	 * @return max number of liquid containers that ship can carry
	 */
	public int getMaxNumberOfLiquidContainers() {
		return maxNumberOfLiquidContainers;
	}

	/**
	 * 
	 * @return ID of the ship
	 */
	public int getID() {
		return ID;
	}

	/**
	 * 
	 * @return fuel consumption per km
	 */
	public double getFuelConsumptionPerKM() {
		return fuelConsumptionPerKM;
	}
	
	
	
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

