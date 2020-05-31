package com.mmt.ui_layer.components.booking_summary;

import org.openqa.selenium.By;

public class HotelBookingSummary extends BookingSummary {
    private By hotelName = By.className("hotel_name");
    private By roomName = By.className("room_heading");

    public String getHotelName() {
        return findElement(hotelName).getText();
    }

    public String getRoomName() {
        return findElement(roomName).getText();
    }
}
