package controller;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import model.Customer;
import model.CustomerRentModel;

public class CustomerRentController {
	CustomerRentModel model;

	public CustomerRentController() {
		this.model = new CustomerRentModel();
	}
	
	public void moveRightC(DefaultListModel<String> model1,DefaultListModel<String> model2,JList<String> inStore,JList<String> ToRent){
		model.moveRight(model1, model2, inStore, ToRent);
	}
	
	public void moveLeft(DefaultListModel<String> model1,DefaultListModel<String> model2,JList<String> inStore,JList<String> ToRent){
		model.moveLeft(model1, model2, inStore, ToRent);
	}
	
	public void rentFuncC(JList<String> inStore,JList<String> ToRent,JPanel contentPane,Customer cust,JDateChooser dateChooserFrom,JDateChooser dateChooserUntill) throws Exception {
		model.rentFunc(inStore, ToRent, contentPane, cust, dateChooserFrom, dateChooserUntill);
	}
}
