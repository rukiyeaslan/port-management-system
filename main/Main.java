
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import ports.Port;
import containers.*;
import ships.Ship;
import java.util.*;

/**
 * 
 * @author rukiyeaslan
 * 
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		int N = in.nextInt();
		in.nextLine();
		
		ArrayList<Port> ports = new ArrayList<Port>();    // creating a list for ports
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ArrayList<Container> containers = new ArrayList<Container>();   //check again
		
		
		ArrayList<BasicContainer> ArrBasic = new ArrayList<BasicContainer>();
		ArrayList<HeavyContainer> ArrHeavy = new ArrayList<HeavyContainer>();
		ArrayList<RefrigeratedContainer> ArrRefr = new ArrayList<RefrigeratedContainer>();
		ArrayList<LiquidContainer> ArrLiquid = new ArrayList<LiquidContainer>();
		
		String[] list = new String[N];
        for (int i=0; i<N;i++) {
    	   String input = in.nextLine();
    	  
    	   list[i] = input;      	
       }
        
        //Creating a port
        int id1 = 0;
        for (int i=0; i<N;i++) {
        	String input = list[i];
        	String[] list2 = input.split(" ");
        	int num = Integer.parseInt(list2[0]);
        	
        	if (num == 3) {
        		Port port = new Port(id1, Double.parseDouble(list2[1]), Double.parseDouble(list2[2]));
        		ports.add(port);
        		id1+=1;
        	}		
        }
        
        
        //creating a container
        int id = 0;
        for (int i=0; i<N;i++) {
        	String input = list[i];
        	String[] list2 = input.split(" ");
        	int num = Integer.parseInt(list2[0]);
        	   
        	
        	if (num == 1) {
        		if (list2.length == 4) {
        			        			
        			if (list2[3].equals("R")) {
        				
        				RefrigeratedContainer refr = new RefrigeratedContainer(id, Integer.parseInt(list2[2]));
        				int portId = Integer.parseInt(list2[1]);
        				ports.get(portId).getContainers().add(refr);
        				containers.add(refr);
        				ArrRefr.add(refr);
        				id+=1;
        				
        			}
        			if (list2[3].equals("L")) {
        				LiquidContainer liquid = new LiquidContainer(id, Integer.parseInt(list2[2]));
        				int portId = Integer.parseInt(list2[1]);
        				ports.get(portId).getContainers().add(liquid);
        				containers.add(liquid);
        				ArrLiquid.add(liquid);
        				id+=1;
        			}
        		}
        		if (list2.length == 3) {
        			
        			if (Integer.parseInt(list2[2]) <= 3000) {
        				BasicContainer basic = new BasicContainer(id, Integer.parseInt(list2[2]));
        				int portId = Integer.parseInt(list2[1]);
        				ports.get(portId).getContainers().add(basic);
        				containers.add(basic);
        				ArrBasic.add(basic);
        				id+=1;
        			}
        			
        			else {
        				HeavyContainer heavy = new HeavyContainer(id, Integer.parseInt(list2[2]));
        				int portId = Integer.parseInt(list2[1]);
        				ports.get(portId).getContainers().add(heavy);
        				containers.add(heavy);
        				ArrHeavy.add(heavy);
        				id+=1;
        			}
        		}
        		
        	}
        	
        }
       
        //creating a ship
        int id2=0;
        for (int i=0; i<N;i++) {
        	String input = list[i];
        	String[] list2 = input.split(" ");
        	int num = Integer.parseInt(list2[0]);
        	
        	if (num == 2) {
        		int portId = Integer.parseInt(list2[1]);
        		Ship ship = new Ship(id2, ports.get(portId), Integer.parseInt(list2[2]),Integer.parseInt(list2[3]),Integer.parseInt(list2[4]), Integer.parseInt(list2[5]), Integer.parseInt(list2[6]), Double.parseDouble(list2[7]));
        		
        		ships.add(ship);
        		id2+=1;
        	}
               	
        }
       
        for (int i=0; i<N;i++) {
        	String input = list[i];
        	String[] list2 = input.split(" ");
        	int num = Integer.parseInt(list2[0]);
        
            if (num == 4) {
            	int contId = Integer.parseInt(list2[2]);
            	int shipId = Integer.parseInt(list2[1]);
            	           	
            	Container cont = containers.get(contId);
            	Ship ship = ships.get(shipId);
            	Port port = ship.getCurrentPort();
            	if (ship.load(containers.get(contId))){   //check port
            		
            	}
            	
            }
            
            if (num == 5) {
            	int contId = Integer.parseInt(list2[2]);
            	int shipId = Integer.parseInt(list2[1]);
            	            	
            	if (ships.get(shipId).unLoad(containers.get(contId))){
            		       		
            	}
            }
            
            
            if (num == 6) {
            	int shipId = Integer.parseInt(list2[1]);
            	int portId = Integer.parseInt(list2[2]);
            	Port prevPort = ships.get(shipId).getCurrentPort();
            	if (ships.get(shipId).sailTo(ports.get(portId))) {
          		
            	}
            }
            
            if (num == 7) {
            	int shipId = Integer.parseInt(list2[1]);
            	double fuelToAdd = Double.parseDouble(list2[2]);
            	
            	ships.get(shipId).reFuel(fuelToAdd);
            }
          
        }
        
      
        for (int i=0; i<ports.size(); i++) {
        	String line1 = "Port " + i + ": (" + String.format("%.2f",ports.get(i).getX()) +", " + String.format("%.2f",ports.get(i).getY()) + ")";
        	String basic = "";
        	String heavy = "";
        	String liquid = "";
        	String  refr = "";
        	for (int j =0; j<ports.get(i).getContainers().size(); j++) {
        		Collections.sort( ports.get(i).getContainers(), Comparator.comparing(Container::getID));
        		Container cont =  ports.get(i).getContainers().get(j);
        		
        		if (ArrBasic.contains(cont)) {
        			basic += (" " + containers.indexOf(cont));
        		}
        		if (ArrHeavy.contains(cont)) {
        			heavy += (" " + containers.indexOf(cont));
        		}
        		if (ArrLiquid.contains(cont)) {
        			liquid += (" " + containers.indexOf(cont));
        		}
        		if (ArrRefr.contains(cont)) {
        			refr += (" " + containers.indexOf(cont));
        		} 
        	}
        	
        	out.println(line1);
        	if (basic != "") {
        		out.println("  BasicContainer:" + basic);
        	}
        	if(heavy !="") {
        		out.println("  HeavyContainer:" +heavy);
        	}
        	if (refr !="") {
        		out.println("  RefrigeratedContainer:" + refr);
        	}
        	if (liquid != "") {
        		out.println("  LiquidContainer:" + liquid);       		
        	}
        	
        	for (int k=0; k<ports.get(i).getCurrent().size();k++) {
        		Collections.sort(ports.get(i).getCurrent() , Comparator.comparing(Ship::getID));
        		Ship ship1= ports.get(i).getCurrent().get(k);
        		String ship = "  Ship " + ship1.getID() + ": " + String.format("%.2f", ship1.getFuel());
        		String basic2 = "";
            	String heavy2 = "";
            	String liquid2 = "";
            	String  refr2 = "";
            	for (int m = 0; m<ship1.getCurrentContainers().size(); m++) {
            		Container cont2 = ship1.getCurrentContainers().get(m);
            		
            		if (ArrBasic.contains(cont2)) {
            			basic2 += (" " + containers.indexOf(cont2));
            		}
            		if (ArrHeavy.contains(cont2)) {
            			heavy2 += (" " + containers.indexOf(cont2));
            		}
            		if (ArrLiquid.contains(cont2)) {
            			liquid2 += (" " + containers.indexOf(cont2));
            		}
            		if (ArrRefr.contains(cont2)) {
            			refr2 += (" " + containers.indexOf(cont2));
            		} 
            
            	}
            	out.println(ship);
            	if (basic2 != "") {
            		out.println("    BasicContainer:" + basic2);
            	}
            	if(heavy2 !="") {
            		out.println("    HeavyContainer:" +heavy2);
            	}
            	if (refr2 !="") {
            		out.println("    RefrigeratedContainer:" + refr2);
            	}
            	if (liquid2  != "") {
            		out.println("    LiquidContainer:" + liquid2);       		
            	}
            	
        	}
        	
        }
                  
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

