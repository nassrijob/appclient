package se.redhat.demo.appclient.server.beans;


import javax.ejb.Remote;

@Remote
public interface Md5sumCalculator {

	public String md5sum(String str);

	public String md5sum(byte[] data);


}
