
package Taxi_Application;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Taxi_Control {

	private static Taxi_Service taxi_Service = new Taxi_Service();
	
	public static void main(String[] args) {

		List<Taxi_Details> taxis = taxi_Service.createTaxis(5);
		Scanner sc = new Scanner(System.in);
		int id = 1;
		while (true) {
			System.out.println("1 - > Book Taxi\n2 - > Taxi Details");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {   //get details from customers
		        
		        int customerID = id;
		        System.out.println("Enter Pickup point");
		        char pickupPoint = sc.next().charAt(0);
		        System.out.println("Enter Drop point");
		        char dropPoint = sc.next().charAt(0);
		        System.out.println("Enter Pickup time");
		        int pickupTime = sc.nextInt();

		        //check if pickup and drop points are valid
		        if(pickupPoint < 'A' || dropPoint > 'F' || pickupPoint > 'F' || dropPoint < 'A')
		        {
		            System.out.println("Valid pickup and drop are A, B, C, D, E, F. Exitting");
		            return;
		        }
		        // get all free taxis that can reach customer on or before pickup time
		        List<Taxi_Details> freeTaxis = taxi_Service.getFreeTaxis(taxis,pickupTime,pickupPoint);

		        //no free taxi means we cannot allot, exit!
		        if(freeTaxis.size() == 0)
		        {
		            System.out.println("No Taxi can be alloted. Exitting");
		            return;
		        }    

		        //sort taxis based on earnings 
		        Collections.sort(freeTaxis,(a,b)->a.totalEarnings - b.totalEarnings); 
		        // 3,4,2 - > 2,3,4

		        //get free Taxi nearest to us
		        taxi_Service.bookTaxi(id,pickupPoint,dropPoint,pickupTime,freeTaxis);
		        id++;
		        break;
			}
			case 2: {
                //two functions to print details
				for(Taxi_Details t:taxis)
				t.printTaxiDetails();
				for(Taxi_Details t:taxis)
					t.printDetails();
				break;
			}
			default:
				return;
			}
		}
	}
}
