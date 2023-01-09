package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private int roomNumber;
	private Date checkIn, checkOut;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

	public Reservation() {

	}

	public Reservation(int roomNumber, Date checkin, Date checkout) {
		this.roomNumber = roomNumber;
		this.checkIn = checkin;
		this.checkOut = checkout;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkIn;
	}

	public Date getCheckout() {
		return checkOut;
	}

	public long duration() {

		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

	}

	public String updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			return "Error in reservation: Reservation dates for update must be future dates.";
		}
		if (!checkOut.after(checkIn)) {
			return "Error in reservation: Check-out date for update must be after check-in date.";
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
		
	}

	@Override
	public String toString() {

		return "Room " + roomNumber + ", check-in: " + sdf.format(checkIn) + ", check-ou: " + sdf.format(checkOut)
				+ ", " + duration() + " nights.";
	}

}
