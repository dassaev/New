Solar Village is a home work assignment for advanced process development with Red Hat JBoss BPM Suite.
It contains following projects:

SolarPermitApp:  "New Order Permitting" business process. This process calls SolarPermitService in every 15 seconds to track the permit approval status, this tracking cycle should be set to longer in real life application.

SolarPermitService: Government REST service simulator project. It provides RESTful CRUD APIs for permit applications. Instead of database, it uses static MAP object to store permit application data. To simulate real life scenario, it randemly postpones Electric and Structural permit approval to 1 to 3 tracking cycle. Permit approval results (APPROVED/DENIED) need to be provided as mock results when the permit application data is submitted at the first time.

SolarPermitDataModel: POJO data model which is used by both of SolarPermitApp and SolarPermitService.

SolarPermitTest: SoapUI project which has required SoapUI testcases to test the SolarPermitApp.

