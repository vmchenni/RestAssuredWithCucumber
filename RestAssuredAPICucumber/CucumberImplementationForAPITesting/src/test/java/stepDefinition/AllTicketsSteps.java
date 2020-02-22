//package stepDefinition;
//
//import cucumber.api.PendingException;
//import cucumber.api.java.en.And;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.When;
//import utility.Util;
//
//public class AllTicketsSteps {
//    public static String sTicketTumber=null;
//    @Given("^User enters ticket id$")
//    public void userEntersTicketId() throws Throwable {
//
//       LoginSteps.pgCreateNewTicket.fnCreateNewTicket();
//       sTicketTumber=LoginSteps.pgAllTickets.fnGetTicketNumber();
//
//        LoginSteps.pgAllTickets.fnEnterTicketNumber(sTicketTumber);
//
//    }
//
//    @And("^Clicks on Apply button$")
//    public void clicksOnApplyButton() throws Throwable {
//
//
//        LoginSteps.pgAllTickets.fnClickApplyButton();
//    }
//
//    @When("^User clicks on a row with ticket number$")
//    public void userClicksOnARowWithTicketNumber() throws Throwable {
//
//        String columnName="Ticket Number";
//        String sTicketNumber=LoginSteps.pgAllTickets.fnGetTicketNumber();
//        LoginSteps.pgAllTickets.fnClickOnRowNumber(columnName,sTicketNumber);
//
//    }
//
//
//}
