package application.custom_properties;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;

public final class CustomTreeItem extends TreeItem {
	public int id;
	
	

	public CustomTreeItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomTreeItem(Object arg0, Node arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public CustomTreeItem(Object arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
