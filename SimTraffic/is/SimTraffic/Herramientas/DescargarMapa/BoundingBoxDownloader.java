package is.SimTraffic.Herramientas.DescargarMapa;



import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

import org.openstreetmap.josm.Main;
import org.openstreetmap.josm.data.osm.DataSet;
import org.xml.sax.SAXException;

public class BoundingBoxDownloader extends OsmServerReader {

	/**
     * The boundings of the desired map data.
     */
    private final double lat1;
	private final double lon1;
	private final double lat2;
	private final double lon2;

	public BoundingBoxDownloader(double lat1, double lon1, double lat2, double lon2) {
		this.lat1 = lat1;
		this.lon1 = lon1;
		this.lat2 = lat2;
		this.lon2 = lon2;
    }


	/**
     * Read the data from the osm server address.
     * @return A data set containing all data retrieved from that url
     */
    public DataSet parseOsm() throws SAXException, IOException {
    	try {
    		Main.pleaseWaitDlg.currentAction.setText("Contacting OSM Server...");
    		final InputStream in = getInputStream("map?bbox="+lon1+","+lat1+","+lon2+","+lat2, Main.pleaseWaitDlg);
    		if (in == null)
    			return null;
    		Main.pleaseWaitDlg.currentAction.setText("Downloading OSM data...");
    		final DataSet data = OsmReader.parseDataSet(in, null, Main.pleaseWaitDlg);
    		in.close();
    		activeConnection = null;
    		return data;
    	} catch (IOException e) {
    		if (cancel)
    			return null;
    		throw e;
    	} catch (SAXException e) {
    		throw e;
    	} catch (Exception e) {
    		if (cancel)
    			return null;
    		if (e instanceof RuntimeException)
    			throw (RuntimeException)e;
    		throw new RuntimeException(e);
    	}
    }
}
