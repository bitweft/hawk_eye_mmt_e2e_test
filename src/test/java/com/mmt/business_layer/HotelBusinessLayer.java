package com.mmt.business_layer;

import com.mmt.enums.CommonRunVariables;
import com.mmt.enums.HotelDetail;
import com.mmt.enums.ServiceOption;
import com.mmt.helpers.RunHelper;
import com.mmt.ui_layer.components.booking_summary.HotelBookingSummary;
import com.mmt.ui_layer.factories.ReviewBookingPageFactory;
import com.mmt.ui_layer.pages.HomePage;
import com.mmt.ui_layer.pages.PaymentsPage;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class HotelBusinessLayer {

    public HotelBusinessLayer() {
        RunHelper.addRunData(CommonRunVariables.SERVICE_OPTION, ServiceOption.HOTELS);
    }

    public void selectHotel() {
        new HomePage()
                .selectService(ServiceOption.HOTELS)
                .populateSearchDetails()
                .search()
                .applyFilters()
                .selectHotelAtPosition(5)
                .selectRoom();
    }

    public void fillDetails() {
        int numberOfSpecialRequestsToAdd = 2;
        ReviewBookingPageFactory.getInstance()
                .addTravellerDetails()
                .addSpecialRequests(numberOfSpecialRequestsToAdd)
                .removeDonations();
    }

    public void confirmHotel() {
        PaymentsPage paymentsPage = ReviewBookingPageFactory.getInstance().proceedToPay();

        var bookingSummary = (HotelBookingSummary) paymentsPage.getBookingSummary();

        String actualHotelName = bookingSummary.getHotelName().toLowerCase();
        String expectedHotelName = ((String) RunHelper.getRunData(HotelDetail.HOTEL_NAME)).toLowerCase();
        assertEquals("Hotel name is incorrect", expectedHotelName, actualHotelName);

        String actualRoomName = bookingSummary.getRoomName();
        String expectedRoomName = (String) RunHelper.getRunData(HotelDetail.ROOM_NAME);
        assertTrue(actualRoomName.toLowerCase().contains(expectedRoomName.toLowerCase()));
    }
}
