/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package Tests.util;

import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

/**
 * Test.
 */
public class Screenshot {

    public static void TakeScreenshot(WebDriver driver, int number){

		// This will store the screenshot under /tmp on your local machine
		String screenshotDirectory = System.getProperty("screenshotDirectory", System.getProperty("java.io.tmpdir", ""));
		String screenshotAbsolutePath = System.currentTimeMillis() + "_link at row number " + number + ".png";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshot.renameTo(new File(screenshotDirectory, screenshotAbsolutePath));
		System.out.println(screenshot);

    }
}
