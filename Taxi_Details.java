package Taxi_Application;

import java.util.ArrayList;
import java.util.List;
/*
The are 6 points(A,B,C,D,E,F) 15 KM apart 60 min travel between each, n taxis all taxis at A starting
100 rs for first 5 KM and then 10 for each of the further KMs, rate from pickup to drop only
pickup time example : 9 hrs, 15 hrs

When a customer books a Taxi, a free taxi at that point is allocated
-If no free taxi is available at that point, a free taxi at the nearest point is allocated.
-If two taxi’s are free at the same point, one with lower earning is allocated
-If no taxi is free at that time, booking is rejected


Input 1:
Customer ID: 1
Pickup Point: A
Drop Point: B
Pickup Time: 9

Output 1:
Taxi can be allotted.
Taxi-1 is allotted

*/
public class Taxi_Details {
	static int taxicount = 0; // taxi number
	int id;
	char currentSpot; // where taxi is now
	int freeTime; // when taxi becomes free
	int totalEarnings; // total earnings of taxi
	List<String> trips; // all details of all trips by this taxi

	public Taxi_Details() {
		currentSpot = 'A';// start point A
		freeTime = 6;// example 6 AM
		totalEarnings = 0;
		trips = new ArrayList<String>();
		taxicount = taxicount + 1; // everytime new taxi is created a new id will be assigned
		id = taxicount;

	}

	public void setDetails(char currentSpot, int freeTime, int totalEarnings, String tripDetail) {
		this.currentSpot = currentSpot;
		this.freeTime = freeTime;
		this.totalEarnings = totalEarnings;
		this.trips.add(tripDetail);
	}

	public void printDetails() {
		// print all trips details
		System.out.println("Taxi - " + this.id + " Total Earnings - " + this.totalEarnings);
		System.out.println("TaxiID    BookingID    CustomerID    From    To    PickupTime    DropTime    Amount");
		for (String trip : trips) {
			System.out.println(id + "          " + trip);
		}
		System.out.println("--------------------------------------------------------------------------------------");
	}

	public void printTaxiDetails() {
		System.out.println("Taxi - " + this.id + " Total Earning - " + this.totalEarnings + " Current Spot - "
				+ this.currentSpot + " Free Time - " + this.freeTime);
	}

}
