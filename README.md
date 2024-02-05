**SET UP** 
1. Install Docker on your machine 
2. Open Terminal 
3. Go to ``./docker`` folder 
4. Run ``docker compose pull``
5. Run ``docker compose up -d``

**RUN TESTS** 

To run all tests ``mvn clean test -DsuiteXmlFile=testng.xml -Denv={env_name} -Dbrowser={browser_name}  -Dlocale={locale_name}``

Execution args:
- ``-Denv``: `local` or `ci`; `local` is for local development, `ci` is for executing in a pipeline
- ``-Dbrowser``: `chrome`, `firefox` or `chromium`; `chrome` is for local development, `chromium` is for executing in a pipeline, `firefox` is for local development and executing in a pipeline. Note that this projects uses ARM images. 
- ``-Dheadless``: `true` or `false`
- ``-Dlocale``: `en`
- ``-Dtest``: run single test; example ``-Dtest={classname}#{testname}``
- ``-Dgroups``: run only specific groups of tests; example ``-Dgroups={groupname1},{groupname2},{groupnamen}``
- ``-DsuiteXmlFile``: `testng.xml`; for parallel test running


**GENERATE TEST REPORT**
1. Run ``mvn allure:report``
2. Go to ``./target/site/``
3. Open ``index.html`` in any browser