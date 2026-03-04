CRC Card
Class: AircraftTransponder
Responsibilities:
- Record aircraft type and flight data from aircraft
- Broadcast aircraft type and flight data in a high density format
Collaborators:
- GroundStationDatabase
Assumptions: 
- The aircraft transponder broadcasts to the ATC ground station.

CRC Card
Class: GroundStation
Responsibilities:
- Receive data 
- Unpack data
- Store data in an aircraft database
Collaborators:
- AircraftTransponder
Assumptions: 
- The ATC ground station receives data from the aircraft transponder. 

CRC Card
Class: GraphicsDisplay
Responsibilities:
- Update every 10 seconds with stored info
Collaborators:
- GroundStation
Assumptions: 
- The graphics display is built and updated using data stored by the ground station.

CRC Card
Class: SystemAnalyzer
Responsibilities:
- Analyze information 
- Detect dangerous situations
Collaborators:
- GroundStation
Assumptions: 
- The system analyzer uses data stored by the ground station. 

CRC Card
Class: Controller
Responsibilities:
- Monitor the graphics display
- Query the database for details about any plane on screen
Collaborators:
- GroundStation
- GraphicsDisplay
Assumptions: 
- The controller monitors the graphics display and can query using the data from the ground station. 