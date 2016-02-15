README

=== shapefile2graph ===

Description
-----------------
The name of this project is shapefile2graph, as the name suggests, it transforms shape file data to graph data structure for convenience of further utility and analysis.

Geotools API was used to read and manage shape file data, both node data and road data will be stored into a hashmap and an arraylist respectively, 
then the graph will be built based on those node and road information extracted from node and road shapefile. 

When the program is executed, two file chosen wizards will show up. User should firstly choose the node shapefile and then the road shapefile, then the graph will automatic created.
User can also view all vertex and edges in the graph and calculate shortest path in this program.
 
Installation
-----------------
shapefile2graph is a maven project, all the repository and dependency information are described in pom.xml file

Contact
-----------------
name:	Kewei Zhang
email:  keweizhang411@gmail.com

Copyright
-----------------

Copyright © 2007 Free Software Foundation, Inc. <http://fsf.org/>

Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not allowed.