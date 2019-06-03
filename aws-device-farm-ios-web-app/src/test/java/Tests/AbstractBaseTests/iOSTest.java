package Tests.AbstractBaseTests;

import Tests.pageobject.PlayerLinkPageObject;
import Tests.util.ReadExcelFile;
import Tests.util.Screenshot;
import Tests.util.WriteExcelFile;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class iOSTest extends iOSTestBase {
    private String filelocation = "EDM_9kcontent.xlsx";

    @Test
    public void test9kLink() throws InterruptedException, MalformedURLException {

        Object[][] links = getLinks();
        List<Boolean> resultLinks = new ArrayList<>();
        String eachResult;
        int passNumber=0;
        int failNumber=0;

        //foreach link
        for (int i = 0; i < links.length; i++) {
            boolean flag = false;

            for (int j = 0; j < links[i].length; j++) {
                if (links[i][j] == null) {
                    continue;
                }

                // check play: list link-> driver.get -> check fail/success
                setUpAppium();
                driver.get(links[i][j].toString());
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                //Constructor to init Element of PageFactory
                PlayerLinkPageObject playerLinkPageObject = PageFactory.initElements(driver, PlayerLinkPageObject.class);

                //check if PlayBtn display else not display
                try {
                    if ((playerLinkPageObject.playBtn.isDisplayed())) {
                        playerLinkPageObject.playBtn.click();
                        Assert.assertTrue(playerLinkPageObject.chunkPlayer.isDisplayed());
                        flag = true;

                    }
                } catch (NoSuchElementException ex) {
                    try {
                        Assert.assertFalse(playerLinkPageObject.chunkPlayer.isDisplayed());
                        flag = false;
                    } catch (NoSuchElementException ex2) {
                        flag = false;
                    }
                }
                // test link
                Thread.sleep(10000);
                driver.close();

                // add result and print result for each link
                resultLinks.add(flag);
                if (resultLinks.get(i).toString().equals("true")) {
                    eachResult = "OK";
                    passNumber++;
                } else {
                    eachResult = "FAIL";
                    failNumber++;
                }
                System.out.println("\n\t" + (i + 1) + " " + links[i][j].toString() + ": " + eachResult + "\n\t");
            }
        }
        // Summary Test Result
        System.out.println("\n\tTotal link tested: " + links.length + " link\n\t");
        System.out.println("\n\tNumber of PASSED: " + passNumber + " link\n\t");
        System.out.println("\n\tNumber of FAILED: " + failNumber + " link\n\t");

        // write file from list result at check play: open file-> write file -> close
        WriteExcelFile.writeResultTestLinks(filelocation, resultLinks, 0, 6);
    }

    private Object[][] getLinks() {
        ReadExcelFile.readXLS(filelocation);
        int startRow = 2;
        int endRow = 5;
        int startColumn = 3;
        int endColumn = 3;
        int sheetNumber = 0;
        return ReadExcelFile.getValueRow1ToRow2AndCol1ToCol2(filelocation, sheetNumber, startRow, endRow, startColumn, endColumn);
    }
}
