To run all tests ``mvn clean test``

To run one specific test `` mvn clean test -Dtest={classname}#{testname}
``

To generate report ``mvn allure:report``

Options for test run 
1. ``-Denv``, default value ``LOCAL``
2. ``-Dlocale``, default value ``EN``
3. ``-Dbrowser``, default value ``CHROME``
4. ``-Dheadless``, default value ``TRUE``
5. ``-Dtest={classname}#{testname}``, no default value 
6. ``-Dgroups={groupname1},{groupname2},{groupnamen}``, no default value 