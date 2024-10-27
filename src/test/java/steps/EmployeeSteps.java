package steps;

import base.CommonServices;
import constants.FilePaths;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.junit.Assert;
import servics.EmployeeListServices;
import utility.PropertyFileReader;

public class EmployeeSteps {

    EmployeeListServices employeeListServices = new EmployeeListServices();
    String employeeNumber = "";
    String employeeId = "";
    PropertyFileReader propertyFileReader = new PropertyFileReader(FilePaths.PROFILE_PROPERTIES);

    @Given("user creating employee details in system")
    public void user_creating_employee_details_in_system(String payload) {
        Response response = employeeListServices.createEmployee(payload);
        Assert.assertEquals(response.statusCode(), 200);
        employeeNumber = response.jsonPath().getString("data.empNumber");
        employeeId = response.jsonPath().getString("data.employeeId");
    }

    @Given("user updated employee details")
    public void user_updated_employee_details(String payload) {
        payload = payload.replace("<employeeId>", employeeId);
        Response response = employeeListServices.updateDetailsEmployee(payload, employeeNumber);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Given("user deleted employee details")
    public void user_deleted_employee_details(String payload) {
        Response response = employeeListServices.deleteEmpDetails(payload, employeeNumber);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Given("user generate toke for {string} profile")
    public void userGenerateTokeForProfile(String profile) {
        String username =propertyFileReader.getValue(profile+".username");
        String password =propertyFileReader.getValue(profile+".password");
        CommonServices.generateTokenFor(username, password);
    }
}
