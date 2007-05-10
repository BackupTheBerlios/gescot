package is.SimTraffic.Vista;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Ayuda extends JFrame implements HyperlinkListener, 
                                               ActionListener {
  public static void main(String[] args) {
    if (args.length == 0)
      new Ayuda("http://www.apl.jhu.edu/~hall/");
    else
      new Ayuda(args[0]);
  }

  private JIconButton homeButton;
  private JTextField urlField;
  private JEditorPane htmlPane;
  private String initialURL;

  public Ayuda(String initialURL) {
    super("Simple Swing Ayuda");
    this.initialURL = initialURL;
    
    JPanel topPanel = new JPanel();
    topPanel.setBackground(Color.lightGray);
    homeButton = new JIconButton("home.gif");
    homeButton.addActionListener(this);
    JLabel urlLabel = new JLabel("URL:");
    urlField = new JTextField(30);
    urlField.setText(initialURL);
    urlField.addActionListener(this);
    topPanel.add(homeButton);
    topPanel.add(urlLabel);
    topPanel.add(urlField);
    //getContentPane().add(topPanel, BorderLayout.NORTH);

    try {
        htmlPane = new JEditorPane(initialURL);
        htmlPane.setEditable(false);
        htmlPane.addHyperlinkListener(this);
        JScrollPane scrollPane = new JScrollPane(htmlPane);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    } catch(IOException ioe) {
       warnUser("Can't build HTML pane for " + initialURL 
                + ": " + ioe);
    }

    Dimension screenSize = getToolkit().getScreenSize();
    int width = screenSize.width * 8 / 10;
    int height = screenSize.height * 8 / 10;
    setBounds(width/8, height/8, width, height);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event) {
    String url;
    if (event.getSource() == urlField) 
      url = urlField.getText();
    else  // Clicked "home" button instead of entering URL
      url = initialURL;
    try {
      htmlPane.setPage(new URL(url));
      urlField.setText(url);
    } catch(IOException ioe) {
      warnUser("Can't follow link to " + url + ": " + ioe);
    }
  }

  public void hyperlinkUpdate(HyperlinkEvent event) {
    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      try {
        htmlPane.setPage(event.getURL());
        urlField.setText(event.getURL().toExternalForm());
      } catch(IOException ioe) {
        warnUser("Can't follow link to " 
                 + event.getURL().toExternalForm() + ": " + ioe);
      }
    }
  }

  private void warnUser(String message) {
    JOptionPane.showMessageDialog(this, message, "Error", 
                                  JOptionPane.ERROR_MESSAGE);
  }

  public class JIconButton extends JButton {
	  public JIconButton(String file) {
	    super(new ImageIcon(file));
	    setContentAreaFilled(false);
	    setBorderPainted(false);
	    setFocusPainted(false);
	  }
	}


}
