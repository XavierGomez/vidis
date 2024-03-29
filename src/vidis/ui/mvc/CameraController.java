/*	VIDIS is a simulation and visualisation framework for distributed systems.
	Copyright (C) 2009 Dominik Psenner, Christoph Caks
	This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
	This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
	You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>. */
package vidis.ui.mvc;


import org.apache.log4j.Logger;

import vidis.ui.events.CameraEvent;
import vidis.ui.events.IVidisEvent;
import vidis.ui.events.mouse.AMouseEvent;
import vidis.ui.mvc.api.AController;
import vidis.ui.mvc.api.Dispatcher;
import vidis.ui.vis.camera.FreeLookCamera;


public class CameraController extends AController{

	private static Logger logger = Logger.getLogger( CameraController.class );
	
	private FreeLookCamera defaultCamera;
	
	public CameraController() {
		logger.debug( "Constructor()" );
		
		registerEvent( IVidisEvent.InitCamera );
		
		registerEvent(	IVidisEvent.ScrollDown,
						IVidisEvent.ScrollUp,
						IVidisEvent.ScrollLeft,
						IVidisEvent.ScrollRight,
						IVidisEvent.SkewDown,
						IVidisEvent.SkewUp,
						IVidisEvent.RotateLeft,
						IVidisEvent.RotateRight,
						IVidisEvent.ZoomIn,
						IVidisEvent.ZoomOut);
		
		registerEvent(	IVidisEvent.MouseMovedEvent_AWT,
						IVidisEvent.MouseMovedEvent_3D1,
						IVidisEvent.MousePressedEvent_3D1,
						IVidisEvent.MouseReleasedEvent_3D1 );
	}
	
	@Override
	public void handleEvent( IVidisEvent event ) {
		logger.debug( "handleEvent("+event+")" );
		switch ( event.getID() ) {
		case IVidisEvent.InitCamera:
			initialize();
			break;
		case IVidisEvent.ZoomIn:
		case IVidisEvent.ZoomOut:
		case IVidisEvent.RotateLeft:
		case IVidisEvent.RotateRight:
		case IVidisEvent.SkewDown:
		case IVidisEvent.SkewUp:
		case IVidisEvent.ScrollDown:
		case IVidisEvent.ScrollLeft:
		case IVidisEvent.ScrollUp:
		case IVidisEvent.ScrollRight:
			forwardEventToOtherHandler( defaultCamera, event );
			break;
		case IVidisEvent.MouseMovedEvent_AWT:
			defaultCamera.fireEvent( event );
			break;
		case IVidisEvent.MouseMovedEvent_3D1:
		case IVidisEvent.MousePressedEvent_3D1:
		case IVidisEvent.MouseReleasedEvent_3D1:
			if ( !(event.getID() == IVidisEvent.MouseMovedEvent_3D1 && ((AMouseEvent)event).mouseEvent.getPoint().equals( defaultCamera.getMouseDownPoint() ))) {
				defaultCamera.fireEvent( event );
			}
			break;
		}
	}
	
	private void initialize() {
		defaultCamera = new FreeLookCamera();
		Dispatcher.forwardEvent( new CameraEvent( IVidisEvent.CameraRegister, defaultCamera ) );
	}
	
}
