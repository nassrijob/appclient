package se.redhat.demo.appclient.server.beans;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import se.redhat.demo.appclient.server.beans.Md5sumCalculator;

@Stateless
public class Md5sumCalculatorBean implements Md5sumCalculator {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private String md5sum(InputStream is) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] buff = new byte[8192];
			int read = 0;
			try {
				while( (read = is.read(buff)) > 0) {
					digest.update(buff,0, read);
				}
				byte[] md5sum = digest.digest();
				BigInteger bigInt = new BigInteger(1, md5sum);
				return bigInt.toString(16);
				
			} catch (IOException e) {
				log.log(Level.WARNING, "IOException when trying to digest the data", e);
				return "There was an IOException when trying to digest the data";
			}
		} catch (NoSuchAlgorithmException e) {
			log.log(Level.WARNING, "MD5 Algorithm is not supported by the server, see stack trace for more info", e);
			return "MD5 Algorithm is not supported by the server, please check the sever logs for more details";
		}	
	}
	
	@Override
	public String md5sum(byte[] data) {
		ByteArrayInputStream is = new ByteArrayInputStream(data);
		return md5sum(is);
	}
	
	@Override
	public String md5sum(String str) {
		return md5sum(str.getBytes());
	}

	
	
	
}
