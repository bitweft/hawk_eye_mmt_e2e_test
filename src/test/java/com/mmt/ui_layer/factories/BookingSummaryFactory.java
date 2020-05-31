package com.mmt.ui_layer.factories;

import com.mmt.enums.CommonRunVariables;
import com.mmt.enums.ServiceOption;
import com.mmt.helpers.RunHelper;
import com.mmt.ui_layer.components.booking_summary.BookingSummary;
import com.mmt.ui_layer.components.booking_summary.HotelBookingSummary;

public class BookingSummaryFactory {
    public static BookingSummary getInstance() {
        ServiceOption option = (ServiceOption) RunHelper.getRunData(CommonRunVariables.SERVICE_OPTION);
        return switch (option) {
            case HOTELS -> new HotelBookingSummary();
            case DEFAULT -> throw new RuntimeException("Not Implemented");
        };
    }
}
