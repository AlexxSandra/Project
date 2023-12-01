Feature: Manage - w3schools

 Scenario: Verify GET REST request response
  Given The following GET API endpoint "https://www.w3schools.com/java/tryjava.asp?filename=demo_helloworld"
  When GET request is sent
  Then the response Status 200 displayed
  And response body contains the following text
  """
  <!DOCTYPE html>
  <html lang="en-US">
  <head>
  <title>W3Schools Tryit Editor</title>
  """

 Scenario: Verify GET REST request response
  Given The following GET API endpoint "https://www.w3schools.com/java/jar"
  When GET request is sent
  Then the response Status 404 displayed


 Scenario: Verify POST REST request response
  Given The following POST API endpoint "https://try.w3schools.com/try_java.php?x=0.9254311243476432"
  When POST request is sent with the following body
  Then the response Status 200 displayed
  And response body contains the following text
  """
  <style>body {    background-color: #000000;    color: #ffffff; font-family: consolas, monospace; }pre { font-size: 100%; font-family: consolas, monospace; }</style><pre>Hello World
  </pre>
  """

 Scenario: Verify POST REST request response
  Given The following POST API endpoint "https://try.w3schools.com/try/java"
  When POST request is sent with the following body
  Then the response Status 404 displayed
  And response body contains the following text
  """
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
<html><head>
<title>404 Not Found</title>
</head><body>
<h1>Not Found</h1>
<p>The requested URL was not found on this server.</p>
<hr>
<address>Apache/2.4.38 (Debian) Server at 35.232.170.255 Port 80</address>
</body></html>
  """