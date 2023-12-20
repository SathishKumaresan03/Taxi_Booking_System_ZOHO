package Taxi_Application;

import java.util.ArrayList;
import java.util.List;

public class Taxi_Service {
	public List<Taxi_Details> createTaxis(int n) {
		List<Taxi_Details> taxi = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			Taxi_Details t = new Taxi_Details();
			taxi.add(t);
		}
		return taxi;
	}

	public List<Taxi_Details> getFreeTaxis(List<Taxi_Details> taxis, int pickupTime, char pickUpPoint) {
		List<Taxi_Details> freeTaxis = new ArrayList<Taxi_Details>();
		for (Taxi_Details t : taxis) {
			// taxi should be free
			// taxi should have enough time to reach customer before pickuptime
			if (t.freeTime <= pickupTime
					&& (Math.abs(t.currentSpot  - pickUpPoint ) <= pickupTime - t.freeTime))
				freeTaxis.add(t);

		}
		return freeTaxis;
	}

	public void bookTaxi(int customerId, char pickupPoint, char dropPoint, int pickupTime,
			List<Taxi_Details> freeTaxis) {
		// to find nearest
		int min = 999;

		// distance between pickup and drop
		int distanceBetweenpickUpandDrop = 0;

		// this trip earning
		int earning = 0;

		// when taxi will be free next
		int nextfreeTime = 0;

		// where taxi is after trip is over
		char nextSpot = 'Z';

		// booked taxi
		Taxi_Details bookedTaxi = null;

		// all details of current trip as string
		String tripDetail = "";

		for (Taxi_Details t : freeTaxis) {
			int distanceBetweenCustomerAndTaxi = Math.abs(t.currentSpot  - pickupPoint ) * 15;
			if (distanceBetweenCustomerAndTaxi < min) {
				bookedTaxi = t;
				// distance between pickup and drop = (drop - pickup) * 15KM
				distanceBetweenpickUpandDrop = Math.abs(dropPoint -  pickupPoint ) * 15;
				// trip earning = 100 + (distanceBetweenpickUpandDrop-5) * 10
				earning = (distanceBetweenpickUpandDrop - 5) * 10 + 100;

				int dropTime = pickupTime + distanceBetweenpickUpandDrop / 15;

				// when taxi will be free next
				nextfreeTime = dropTime;

				// taxi will be at drop point after trip
				nextSpot = dropPoint;

				// creating trip detail
				tripDetail = customerId + "               " + customerId + "          " + pickupPoint + "      "
						+ dropPoint + "       " + pickupTime + "          " + dropTime + "           " + earning;
				min = distanceBetweenCustomerAndTaxi;
			}
		}
			// setting corresponding details to allotted taxi
			bookedTaxi.setDetails(nextSpot, nextfreeTime, bookedTaxi.totalEarnings + earning, tripDetail);
			// BOOKED SUCCESSFULLY
			System.out.println("Taxi " + bookedTaxi.id + " booked");
		

	}	
	
}
