package BaseForm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import WizzAirPages.TimeTableAFunc;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Button;

public class form1 {

	private JFrame frame;
	private JTextField txtFrom;
	private JTextField textField;
	private JButton btnNewButton;
	private Map<String, String> hm = new HashMap<>();
	private JTextField maxPrice;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					form1 window = new form1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public form1() {
		System.out.println("kkk");
		initialize();
		hm.put("max","0");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("from");
		lblNewLabel.setBounds(46, 51, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("to");
		lblNewLabel_1.setBounds(46, 91, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtFrom = new JTextField();
		txtFrom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				hm.put("origin", txtFrom.getText());
				
			}
		});
		txtFrom.setToolTipText("depart from");
		txtFrom.setBounds(90, 48, 86, 20);
		frame.getContentPane().add(txtFrom);
		txtFrom.setColumns(10);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				hm.put("dest",textField.getText());
			}
		});
		textField.setToolTipText("return");
		textField.setBounds(90, 88, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("applay");

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				webScrap();
			}
		});
	
		btnNewButton.setBounds(182, 168, 115, 60);
		frame.getContentPane().add(btnNewButton);
		
		maxPrice = new JTextField();
		maxPrice.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				hm.replace("max",maxPrice.getText());
			}
		});
		maxPrice.setBounds(46, 188, 86, 20);
		frame.getContentPane().add(maxPrice);
		maxPrice.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("max price");
		lblNewLabel_2.setBounds(46, 168, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}
	
	private void webScrap(){
		ChromeOptions co = new ChromeOptions();
		//here "--start-maximized" argument is responsible to maximize chrome browser
		co.addArguments("--start-maximized");
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\Drivers\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(co);
		
		driver.get("https://wizzair.com/en-gb/flights/timetable#/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	//////////
		
		TimeTableAFunc tt = new TimeTableAFunc(driver);
		tt.printPriceDeptRtnMaxPeriod(hm.get("origin"),hm.get("dest"),true);
		driver.quit();
	}
}
