package propertyeditor;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;

public class MyDateEditor extends CustomDateEditor {

	public MyDateEditor() {
		super(new SimpleDateFormat("dd.MM.yyyy"), true);
	}

}
