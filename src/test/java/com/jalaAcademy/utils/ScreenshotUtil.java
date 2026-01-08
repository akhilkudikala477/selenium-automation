package com.jalaAcademy.utils;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class ScreenshotUtil {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static void
    attachScreenshotToAllure(byte[] screenshot) {
    	Allure.addAttachment(
                "Failure Screenshot",
                new ByteArrayInputStream(screenshot)
        );    }

    public static void captureScreenshot(WebDriver driver) {

        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;

        // Save locally (optional)
        try {
            FileUtils.copyFile(
                ts.getScreenshotAs(OutputType.FILE),
                new File("./Screenshot/Screenshot_" + timeStamp + ".png")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Attach to Allure
        attachScreenshotToAllure(ts.getScreenshotAs(OutputType.BYTES));
    }
}

