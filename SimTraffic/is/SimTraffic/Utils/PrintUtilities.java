package is.SimTraffic.Utils;

import is.SimTraffic.Messages;

import java.awt.*;
import javax.swing.*;
import java.awt.print.*;

public class PrintUtilities implements Printable {
  private Component componentToBePrinted;

  public static void printComponent(Component c) {
    new PrintUtilities(c).print();
  }
  
  public PrintUtilities(Component componentToBePrinted) {
    this.componentToBePrinted = componentToBePrinted;
  }
  
  public void print() {
    PrinterJob printJob = PrinterJob.getPrinterJob();
    printJob.setPrintable(this);
    if (printJob.printDialog())
      try {
        printJob.print();
      } catch(PrinterException pe) {
        System.out.println(Messages.getString("PrintUtilities.0") + pe); //$NON-NLS-1$
      }
  }

  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
    if (pageIndex > 0) {
      return(NO_SUCH_PAGE);
    } else {
      Graphics2D g2d = (Graphics2D)g;
      g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
      double pageHeight = pageFormat.getImageableHeight();
	  double pageWidth = pageFormat.getImageableWidth();
	  double mapHeight = componentToBePrinted.getHeight();
	  double mapWidth = componentToBePrinted.getWidth();
      double scaleX =pageWidth / mapWidth;
	  double scaleY = pageHeight / mapHeight;
	  g2d.scale(scaleX,scaleY);	
      pageFormat.setOrientation(PageFormat.LANDSCAPE);
      disableDoubleBuffering(componentToBePrinted);
      componentToBePrinted.paint(g2d);
      enableDoubleBuffering(componentToBePrinted);
      return(PAGE_EXISTS);
    }
  }
    public void drawMapBean(
    		Graphics g, PageFormat pageFormat, double x, double y,
    		double width, double height, boolean proportional)
    	{
    		Graphics2D g2d = (Graphics2D)g;
    		
    		double pageHeight = pageFormat.getImageableHeight();
    		double pageWidth = pageFormat.getImageableWidth();
    		
    		double mapHeight = componentToBePrinted.getHeight();
    		double mapWidth = componentToBePrinted.getWidth();
    		
    		//set the (X,Y) position
    		g2d.translate(x*pageWidth,y*pageHeight);
    		
    		//determine the scale
    		double scaleX = width * pageWidth / mapWidth;
    		double scaleY = height * pageHeight / mapHeight;
    		
    		if(proportional)
    		{
    			//maintain the aspect ratio
    			double scale = scaleX > scaleY ? scaleY : scaleX;
    			
    			//transform the graphics object for drawing
    			g2d.scale(scale, scale);
    			
    		} else {
    			g2d.scale(scaleX, scaleY);
    		}
    		
    		// Do the work now ...
    		disableDoubleBuffering(componentToBePrinted);
    		componentToBePrinted.paint(g2d);
    		enableDoubleBuffering(componentToBePrinted);
    		
    		//revert the transforms
    		g2d.scale(1/scaleX, 1/scaleY);
    		g2d.translate(-x*pageWidth,-y*pageHeight);
    	} 

  public static void disableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(false);
  }

  public static void enableDoubleBuffering(Component c) {
    RepaintManager currentManager = RepaintManager.currentManager(c);
    currentManager.setDoubleBufferingEnabled(true);
  }
}
