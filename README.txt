README.TXT

This project demonstrates a basic flight log database with Hibernate 4/JPA mapping.

It was designed to run on a derby database, but shouldn't be limited to derby.

Derby Instructions:

1. Start a 'network' derby server using derby script startNetworkServer
2. Define tables: this can be done using the derby 'ij' client tool, run 'ij'
	Then define tables: run 'create_flight.sql';
	Then load ref data: run 'flight_details.sql';
	If any of this goes wrong, run 'kill_flight.sql'; to drop tables
	
3. Run the unit tests, eg: select src/test/java, then 'Run'. Or any other method. 

The unit tests just do some basic crud stuff using the Hibernate objects. They will clear up after themselves, unless there is an error, 
in which case they leave data in the database that sbhould be manually cleared.

-- Clinton@tla.com.au 13 Mar 2015