package tests;

import org.testng.annotations.*;
import pages.*;

public class LoginTest extends LoginPage {

    //Wrapped methods to do all verification on a single line

    @Test(priority = 1, groups={"login", "smoke"})
    public void VerifyCreationNewAccount() throws Exception {
        verifyCreatingNewAccount();
    }
}
