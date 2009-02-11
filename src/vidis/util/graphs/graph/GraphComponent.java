package vidis.util.graphs.graph;

import java.io.Serializable;

/**
 * An interface defining either a <tt>Vertex</tt> or an <tt>Edge</tt> in
 * a <tt>Graph</tt>.
 * 
 * @author Jesus M. Salvo Jr., Ralf Vandenhouten
 */

public interface GraphComponent extends Serializable {
   public void setString(String text);
   public String toString();
}
