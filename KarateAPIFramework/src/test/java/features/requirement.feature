Feature: Writing the data into csv fuile
 
 	Background:
 	* url baseUrl1  
  * def ExcelReader = Java.type('Utilities.WriteDataInExcel')
  * def rowNum1 = 2
  * def rowNum2 = 3
  * def scenario1 = ExcelReader.setTheValuesFromExcelFileInFeatureFile(rowNum1)
  * print scenario1
  * print scenario1[0]
  * def scenario2 = ExcelReader.setTheValuesFromExcelFileInFeatureFile(rowNum2)
  * print scenario2
  * print scenario2[0]

  Scenario: get the reponse and pass it to csv
    Given path urlPath+scenario1[0]
    * def responseCode = scenario1[1]
    When method get
    * print responseCode
    Then status 200  
    * print response
    * def responseStatusVariable = responseStatus
    * print responseStatusVariable
    * ExcelReader.setTheStatusIntoExcelFile(responseStatusVariable,rowNum1)    


	Scenario: get the reponse and pass it to csv
    Given path urlPath+scenario2[0]
    * def responseCode = scenario2[1]
    When method get
    * print responseCode
    Then status 200  
    * print response
    * def responseStatusVariable = responseStatus
    * print responseStatusVariable
    * ExcelReader.setTheStatusIntoExcelFile(responseStatusVariable,rowNum2)    
    
    
    