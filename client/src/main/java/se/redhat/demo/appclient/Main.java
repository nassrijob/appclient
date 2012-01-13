package se.redhat.demo.appclient;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import se.redhat.demo.appclient.server.beans.Md5sumCalculator;

public class Main {

	public static void main(String[] args) throws NamingException {
		final Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);				
		final  Md5sumCalculator md5tool = (Md5sumCalculator) context.lookup("ejb:/appclient-demo-server/Md5sumCalculatorBean!se.redhat.demo.appclient.server.beans.Md5sumCalculator");
		String input = "Default";
		if(args.length > 0) {
			input = args[0];
		}
		System.out.println("Md5Sum of  " + input + " is " + md5tool.md5sum(input));
	}
}