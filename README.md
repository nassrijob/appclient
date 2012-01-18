App Client Example
=========================

What is it?
-----------

This example shows how to access an EJB from a remote Java client program. It
demonstrates the use of *EJB 3.1* and *JNDI* in *JBoss AS 7*.

It also demonstrates how a Swing application can access EJB 3.1 and JNDI in JBoss AS 7 CR1 or JBoss EAP 6.0 Alpha2.

There are three parts to the example: a server side component, a command line client program and a
rich-client swing application. 

The server component is comprised of a stateless EJB which can calculate a MD5sum from a String or byte array. 
The client programs looks up the stateless beans via JNDI and invokes methods on them.


Directory Structure
-----------

- appclient (main directory of this example
	- server (the source code for the server component)
	- client (the source code for the CLI client)
	- rich-client (the source code for the Swing client)

System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on a JBoss AS 7 or EAP 6.
The following instructions target JBoss AS 7, but they also apply to JBoss EAP 6.

Building and deploying the application
-------------------------

Start JBoss AS 7 (or EAP 6):

        $JBOSS_HOME/bin/standalone.sh

To build both the server side component and a remote client program change into the appclient directory

        mvn clean install

The server side component is packaged as a jar and needs deploying to the AS you just started:

        cd server
        mvn jboss-as:deploy

This maven goal will deploy server-side/target/applclient-demo-server.jar. You can check the AS
console to see information messages regarding the deployment.

To start a command line client that will access the beans you just deployed:

        cd ../client
        mvn exec:exec

You should now se a default text and the MD5 checksum representing it

To start a swing client that will access the beans you just deployed:

        cd ../rich-client
        mvn exec:exec

You should now se a application where you can enter a string and click the button to get the MD5 checksum from the server.


Building and running the java web start example
------------------------
The example assumes that you have a local web server running on localhost using port 80. If you don't have a local web server 
you will have to update jws-client/src/jnlp/template.vm and change the "codebase" property to point to the web server that you plan to use.

To run the Java Web Start example follow the following steps:

1. Go to the jws-client directory

        cd ../jws-client

2. Build the web start by executing the following commmand in the jws-client directory:
	
        mvn install webstart:jnlp

3. The last command should now result in a ZIP archive file in jws-client/target/jws-client.zip, 
this file should be extracted on the web server where you like to deploy the jws-client for example:

        sudo unzip target/jws-client.zip -d /var/www/html/

4. Point your web browser to the web server where you unzip the jws-client.zip, for example:

        firefox http://localhost/launch.jnlp
	


To undeploy the server side component from JBoss AS:

        cd ../server
        mvn jboss-as:undeploy



