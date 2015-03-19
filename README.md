# crunch-bof

mvn clean install -DSkipTests

src/test/resources/access_log is the main dataset :
Apache log file described here : http://httpd.apache.org/docs/1.3/logs.html

level0 : read and write data (pipeline methods)

level1 : get http code occurence from logs (user defined fonction)

level2 : get top five requested urls (udf and filter)

level3 : get country list by ip address location (join)


org.apache.crunch.impl.mem.OctoMemPipeline is a trick to have output with constant name.
