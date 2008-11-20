package vidis.ui.model.impl.guielements.popupWindows;

import java.io.File;

import org.apache.log4j.Logger;

import vidis.sim.classloader.modules.impl.AModule;
import vidis.sim.classloader.modules.impl.AModuleFile;
import vidis.sim.classloader.modules.interfaces.IModuleComponent;
import vidis.ui.events.IVidisEvent;
import vidis.ui.events.VidisEvent;
import vidis.ui.model.impl.PercentMarginLayout;
import vidis.ui.model.impl.guielements.PopupWindow;
import vidis.ui.model.impl.guielements.tree.Tree;
import vidis.ui.model.impl.guielements.tree.TreeElement;
import vidis.ui.mvc.api.Dispatcher;
import vidis.util.ResourceManager;

public class OpenMSIMFilePopupWindow_tree extends PopupWindow {
	private static Logger logger = Logger.getLogger(OpenMSIMFilePopupWindow_tree.class);
	private static OpenMSIMFilePopupWindow_tree instance;
	public static OpenMSIMFilePopupWindow_tree getInstance() {
		if( instance == null)
			instance = new OpenMSIMFilePopupWindow_tree("Please pick a module You want to load");
		return instance;
	}
	
	public OpenMSIMFilePopupWindow_tree(String title) {
		super(title);
		Tree<IModuleComponent> tree = new Tree<IModuleComponent>("Modules", null) {
			@Override
			protected void clickedOn(TreeElement<IModuleComponent> element) {
				if( element.getObject() != null) {
					logger.info("CLICKED ON: " + element.getObject().getName() );
					if(element.getObject().isModule()) {
	//					// module directory, expand/close
	//					if(element.isExpanded())
	//						element.collapse();
	//					else
	//						element.expand();
						// refresh display
	//					refresh();
					} else {
						// module file, open
						Dispatcher.forwardEvent(new VidisEvent<IModuleComponent>(IVidisEvent.SimulatorLoad, element.getObject()));
						// close this popup
						close();
					}
				} else {
					// clicked on root element
				}
			}
		};
		for( AModule module : ResourceManager.getModules() ) {
			TreeElement<IModuleComponent> treeModule = tree.createTreeElement(module.getName(), module);
			tree.addChild( treeModule );
			for( AModuleFile moduleFile : module.getModuleFiles()) {
				treeModule.addChild(
						tree.createTreeElement(moduleFile.getName(), (IModuleComponent) moduleFile)
				);
			}
		}
		tree.refresh();
		tree.setLayout( new PercentMarginLayout(0.001,0.001,0.001,0.001,-1,-1));
		tree.getScrollPane().setLayout(new PercentMarginLayout(0.001,0.001,0.001,-0.07,-0.93,-1));
		this.addChild( tree.getScrollPane() );
	}
	
	@Override
	public void setVisible(boolean visible) {
		if ( visible == true ) {
			handleReload();
		}
		super.setVisible(visible);
	}
	
	/**
	 * @dominik FIXME handle reload here
	 * called every time this dialog is shown
	 */
	private void handleReload() {
		
	}
}