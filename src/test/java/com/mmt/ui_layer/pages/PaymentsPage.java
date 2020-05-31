package com.mmt.ui_layer.pages;

import com.mmt.ui_layer.components.booking_summary.BookingSummary;
import com.mmt.ui_layer.factories.BookingSummaryFactory;

public class PaymentsPage {
    BookingSummary bookingSummary;
    public PaymentsPage() {
        this.bookingSummary = BookingSummaryFactory.getInstance();
    }

    public BookingSummary getBookingSummary() {
        return bookingSummary;
    }
}
