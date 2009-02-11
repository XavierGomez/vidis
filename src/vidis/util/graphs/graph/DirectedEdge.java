/*	VIDIS is a simulation and visualisation framework for distributed systems.
	Copyright (C) 2009 Dominik Psenner, Christoph Caks
	This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
	This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
	You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>. */
package vidis.util.graphs.graph;

/**
 * Represents a directed edge in a graph.
 * 
 * @author Jesus M. Salvo Jr., Ralf Vandenhouten
 */
public class DirectedEdge extends Edge {

  /**
    * Creates an DirectedEdgeImpl object whose origin and destination vertices
    * are specified by the method parameters.
    *
    * @see		Vertex
    */
  public DirectedEdge( Vertex sourceVertex, Vertex sinkVertex ){
    super( sourceVertex, sinkVertex );
  }

  /**
   * Returns the source Vertex of the edge.
   *
   * @return  The source Vertex.
   */
  public Vertex getSource() {
    return this.vertexA;
  }

  /**
   * Returns the sink Vertex of the edge.
   *
   * @return  The sink Vertex.
   */
  public Vertex getSink() {
    return this.vertexB;
  }

  /**
    * Returns a String representation of the Edge.
    * By default, the format is:
    * fromVertex.toString() + "->" + toVertex.toString()
    *
    * @return	The String representation of the Edge
    * @see		Vertex
    */
  public String toString(){
    return this.vertexA.toString() + "->" + this.vertexB.toString();
  }

  /**
    * Creates a clone of this Edge. This calls the Edge constructor,
    * thereby creating a new instance of Edge. However, the vertices
    * in both endpoints of the Edge are not cloned.
    *
    * @return  A clone of an instance of Edge.
    */
  protected Object clone(){
    Edge edge = new DirectedEdge( vertexA, vertexB );
    edge.setString(this.str);
    return edge;
  }

}

