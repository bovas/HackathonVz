package org.i3.smartmeter.billing.test;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SMCustomerDataProcessorTest {

	public static void main(String[] args) throws ParseException {
		/*Customer customer1 = new Customer("Shyam", "Sundar", 12345678L, "Chennai", 625708L);
		Customer customer2 = new Customer("Bovas", "Victor", 12345679L, "Chennai", 625708L);
		Customer customer3 = new Customer("Kabilan", "KK", 12345672L, "Chennai", 625708L);
		Customer customer4 = new Customer("Shyam", "Sundar1", 12345673L, "Chennai", 625708L);
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		customerDao.saveCustomer(customer1);
		customerDao.saveCustomer(customer2);
		customerDao.saveCustomer(customer3);
		customerDao.saveCustomer(customer4);*/
		Format formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");
		String str = "01-Jun-2015 23:59:00 PM";
		System.out.println(DateFormatter.getDate(str,formatter));
	}

}
