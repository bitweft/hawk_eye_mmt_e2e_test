package com.mmt;

import com.mmt.business_layer.HotelBusinessLayer;
import com.mmt.helpers.DriverHelper;
import com.mmt.helpers.RunHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookHotelTest {

    @BeforeMethod
    @Parameters(value = {"run", "browserName", "url"})
    public void setup(String run, String browserName, String url) {
        DriverHelper.createDriver(run, browserName, url);
        RunHelper.initRunData();
    }

    @Test
    public void shouldBeAbleToSelectHotelsAndProceedToCheckout() {
        HotelBusinessLayer hotelBusinessLayer = new HotelBusinessLayer();
        hotelBusinessLayer.selectHotel();
        hotelBusinessLayer.fillDetails();
        hotelBusinessLayer.confirmHotel();
    }

    @AfterMethod
    public void teardown() {
        DriverHelper.getDriver().quit();
    }
}
