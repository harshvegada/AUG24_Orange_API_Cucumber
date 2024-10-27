package servics;

import base.BaseServices;
import constants.FilePaths;
import io.restassured.response.Response;
import utility.PropertyFileReader;

public class EmployeeListServices extends BaseServices {

    PropertyFileReader propertyFileReader = new PropertyFileReader(FilePaths.END_POINT_PROPERTIES);


    public Response getListOfEmployee() {
        setContentTypeAsURLENC();
        return executeGetAPI(propertyFileReader.getValue("GET_LIST_OF_EMPLOYEE"));
    }

    public Response createEmployee(Object payload) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(propertyFileReader.getValue("CREATE_EMPLOYEE"));
    }

    public Response createEmployee() {
        String payload = "";
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(propertyFileReader.getValue("CREATE_EMPLOYEE"));
    }

    public Response updateDetailsEmployee(Object payload, String employeeNumber) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePatchAPI(String.format(propertyFileReader.getValue("UPDATE_EMPLOYEE"), employeeNumber));
    }

    public Response updateDetailsEmployee(String employeeNumber) {
        String payload = "";
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePatchAPI(String.format(propertyFileReader.getValue("UPDATE_EMPLOYEE"), employeeNumber));
    }

    public Response deleteEmpDetails(Object payload, String employeeNumber) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(String.format(propertyFileReader.getValue("DELETE_EMP_DETAILS"), employeeNumber));
    }


}
