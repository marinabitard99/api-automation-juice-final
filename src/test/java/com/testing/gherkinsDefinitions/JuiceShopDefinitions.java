package com.testing.gherkinsDefinitions;

import com.testing.serenitySteps.JuiceShopSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;

import java.io.IOException;

public class JuiceShopDefinitions {

  @When("^the user creates an account using the following data:$")
  public void the_user_creates_an_account_using_the_following_data(DataTable dataTable) throws IOException {
    JuiceShopSteps.createAnAccount(dataTable);
  }

  @When("^the user logs in using the following data:$")
  public void theUserLogsInUsingTheFollowingData(DataTable dataTable) throws IOException {
    JuiceShopSteps.logInAnAccount(dataTable);
  }

  @When("^the user changes password using the following data:$")
  public void theUserChangesPasswordUsingTheFollowingData(DataTable dataTable) throws IOException {
    JuiceShopSteps.changePassword(dataTable);
  }

  @When("^the user requests to reset password using the following data:$")
  public void theUserRequestsToResetPasswordUsingTheFollowingData(DataTable dataTable) throws IOException {
    JuiceShopSteps.resetPassword(dataTable);
  }

  @When("^the user adds an item to basket using the following data:$")
  public void theUserAddsAnItemToBasketUsingTheFollowingData(DataTable dataTable) throws IOException {
    JuiceShopSteps.addItemToBasket(dataTable);
  }

  @When("^the user requests the basket content$")
  public void theUserRequestsTheBasketContentAndObservesTheFollowingData() {
    JuiceShopSteps.getBasketContent();
  }

  @When("^the user purchases the items using the following data:$")
  public void theUserPurchasesTheItemsUsingTheFollowingData(DataTable dataTable) throws IOException {
    JuiceShopSteps.purchaseTheItems(dataTable);
  }
}
