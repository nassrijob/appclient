package se.redhat.demo.appclient;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import se.redhat.demo.appclient.server.beans.Md5sumCalculator;

@SuppressWarnings("serial")
public class RichClientMain extends JFrame {
	
	
	final JLabel output = new JLabel(" ");
	final JTextField input = new JTextField();

	public RichClientMain() throws HeadlessException {
		super("Rich Application Client - Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel main = new JPanel(new BorderLayout());
		JLabel label1 = new JLabel("Text to calculate MD5Sum for");
		main.add( label1,BorderLayout.NORTH );
		
		JPanel textPanel = new JPanel(new BorderLayout());
		textPanel.add(input,BorderLayout.NORTH);
		
		textPanel.add(output,BorderLayout.SOUTH);
		
		main.add(textPanel,BorderLayout.CENTER);
		JButton button = new JButton("Click to calculate MD5 sum");
		main.add(button,BorderLayout.SOUTH);
		main.setBorder( new EmptyBorder(8,8,8,8) );
		button.addActionListener(new ActionListener () {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				output.setText(calculateMd5Sum(input.getText()));
			}
		});
		getContentPane().add(main);
		pack();
		setLocationRelativeTo(null);
	}
	
	private String calculateMd5Sum(String strInput) {
		try {
			final Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);				
			final  Md5sumCalculator md5tool = (Md5sumCalculator) context.lookup("ejb:/appclient-demo-server/Md5sumCalculatorBean!se.redhat.demo.appclient.server.beans.Md5sumCalculator");
			if(strInput!=null && strInput.length() > 0) {
				return md5tool.md5sum(strInput);
			}
		} catch(NamingException e) {
			e.printStackTrace();
			return "Naming Exception occured";
		}
		return "Not implemented yet";
	}
	

	public static void main(String[] args) {
		RichClientMain app = new RichClientMain();
		app.setVisible(true);
	}
}