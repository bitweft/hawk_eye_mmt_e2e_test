package com.mmt.ui_layer.base;

import com.mmt.ui_layer.pages.PaymentsPage;

public abstract class ReviewBookingPage extends UILayer {
    public abstract ReviewBookingPage addTravellerDetails();

    public abstract ReviewBookingPage addSpecialRequests(int numberOfSpecialRequests);

    public abstract ReviewBookingPage removeDonations();

    public abstract PaymentsPage proceedToPay();
}
