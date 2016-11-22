package edu.fiu.dbms.hospital.basicentity;

public class Room {
	@Override
	public String toString() {
		return "Room [roomID=" + roomID + ", checkInDate=" + checkInDate + ", dischargeDate=" + dischargeDate
				+ ", roomCostPerDay=" + roomCostPerDay + "]";
	}

	private String roomID;
	private String checkInDate;
	private String dischargeDate;
	private String roomCostPerDay;

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getRoomCostPerDay() {
		return roomCostPerDay;
	}

	public void setRoomCostPerDay(String roomCostPerDay) {
		this.roomCostPerDay = roomCostPerDay;
	}

	public Room(String roomID, String checkInDate, String dischargeDate, String roomCostPerDay) {
		super();
		this.roomID = roomID;
		this.checkInDate = checkInDate;
		this.dischargeDate = dischargeDate;
		this.roomCostPerDay = roomCostPerDay;
	}

}
