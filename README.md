# crunch-bof

## description 

The goal : to learn Apache Crunch programming. 
How ? You have to implement the code to pass the tests.

src/test/resources/access_log is the main dataset :
Apache log file described (here)[http://httpd.apache.org/docs/1.3/logs.html]

## build

    mvn clean install -DSkipTests


level0 : read and write data (pipeline methods)

level1 : get http code occurence from logs (user defined fonction)

level2 : get top five requested urls (udf and filter)

level3 : get country list by ip address location (join)

level4 : process inverted index on the Declaration des Droits de l'Homme et du Citoyen

level5 ; deserialize the inverted index into a collection of POJO using Avro format, and distributed search on that collection


org.apache.crunch.impl.mem.OctoMemPipeline is a trick to have output with constant name.
