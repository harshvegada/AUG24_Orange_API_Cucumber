Feature: Employee Creation, Updation, Deletion & Get


  Background:
    Given user generate toke for "admin" profile

  @employeeCURD
  Scenario Outline: Employee CURD Operation
    And user creating employee details in system
    """
    [
    {
        "endpoint": "employees",
        "method": "POST",
        "data": {
            "firstName": "<firstName>",
            "middleName": "<middleName>",
            "lastName": "<lastName>",
            "locationId": "3",
            "joinedDate": "2024-10-27",
            "autoGenerateEmployeeId": true,
            "employeeId": null
        },
        "params": {}
    },
    {
        "endpoint": "employees/<EMPNUMBER>",
        "method": "PATCH",
        "data": {
            "firstName": "<firstName>",
            "middleName": "<middleName>",
            "lastName": "<lastName>",
            "emp_gender": "1",
            "emp_marital_status": "",
            "nation_code": "82",
            "licenseNo": "5434323423",
            "ssn": "324364567",
            "emp_birthday": null,
            "emp_dri_lice_exp_date": "2024-10-31"
        },
        "params": {}
    },
    {
        "endpoint": "employees/<EMPNUMBER>/job",
        "method": "PATCH",
        "data": {
            "joined_date": "2024-10-27",
            "probation_end_date": "2024-10-31",
            "date_of_permanency": "2024-10-31",
            "job_title_id": "90",
            "employment_status_id": "3",
            "job_category_id": "",
            "subunit_id": "3",
            "location_id": "3",
            "work_schedule_id": "24",
            "cost_centre_id": "10",
            "has_contract_details": "1",
            "contract_start_date": null,
            "contract_end_date": null,
            "comment": "",
            "effective_date": "2024-10-27",
            "event_id": "1"
        },
        "params": {}
    },
    {
        "endpoint": "employeeOnboarding/<EMPNUMBER>/create",
        "method": "POST",
        "data": {
            "data": {
                "eventTemplate": "1",
                "dueDate": "2024-11-11",
                "owners": [
                    "29",
                    "38"
                ]
            }
        },
        "params": {}
      }
    ]
    """
    And user updated employee details
    """
    {
    "firstName": "<firstName>",
    "middleName": "<middleName>",
    "lastName": "<lastName>",
    "otherId": "",
    "emp_gender": "1",
    "emp_marital_status": "Married",
    "nation_code": "82",
    "licenseNo": "<licenseNo>",
    "employeeId": "<employeeId>",
    "ssn": "324364567",
    "emp_birthday": "1986-10-13",
    "emp_dri_lice_exp_date": "2024-10-31"
    }
    """
    And user deleted employee details
    """
    {
    "reason": "3",
    "date": "2024-10-27",
    "time": "10:35",
    "timezone": "Etc/GMT+5",
    "note": "wereter",
    "flaged": "0",
    "terminateNow": "1"
    }
    """


    Examples:
      | firstName | lastName | middleName | licenseNo   |
      | Megha     | Dubey    | M          | 24235346456 |
      | Bhusan    | Shende   | B          | 2343445646  |
      | Nikita    | Naik     | N          | 23436       |