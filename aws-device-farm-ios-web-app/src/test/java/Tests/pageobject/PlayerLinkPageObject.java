package Tests.pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlayerLinkPageObject {

        @FindBy(xpath = "//*[@id=\'videojs-player\']/button[2]")
        public WebElement playBtn;

        @FindBy (xpath = "//div[@class='vjs-bottom']")
        public WebElement chunkPlayer;
}
