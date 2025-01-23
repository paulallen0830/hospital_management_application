import java.util.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.*;

class menu extends JFrame implements ActionListener
{
	JButton signin, signup;
	JLabel U, P, error, nu;
	JTextField u;
	JPasswordField p;
	public void centerFrameOnScreen()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	menu()
	{
		super("AUTHENTICATION PAGE");
		U = new JLabel("Username");
		u = new JTextField(100);
		P = new JLabel("Password");
		p = new JPasswordField(100);
		signin = new JButton("SIGN IN");
		nu = new JLabel("New user?");
		signup = new JButton("SIGN UP");
		error = new JLabel();
		Color purple = new Color(89, 12, 12);
		Color white = new Color(255, 255, 255);
		add(U);
		add(u);
		add(P);
		add(p);
		add(signin);
		add(nu);
		add(signup);
		add(error);
		setLayout(null);
		U.setBounds(30,30,100,20);
		u.setBounds(150,30,100,20);
		P.setBounds(30,70,100,20);
		p.setBounds(150,70,100,20);
		signin.setBounds(75,110,100,20);
		error.setBounds(50,150,200,20);
		getContentPane().setBackground(white);
		U.setForeground(purple);
		u.setBackground(purple);
		u.setForeground(white);
		P.setForeground(purple);
		p.setBackground(purple);
		p.setForeground(white);
		signin.setBackground(purple);
		signin.setForeground(white);
		nu.setForeground(purple);
		signup.setBackground(purple);
		signup.setForeground(white);
		error.setForeground(purple);
		setSize(300, 240);
		centerFrameOnScreen();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		signin.addActionListener(this);
		signup.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			String user=u.getText();
			String pass = new String(p.getPassword());	
			String query="select password from pass where username='"+user+"'";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.next())
			{
				String test=rs.getString("password");
				if(pass.equals(test))
				{
					signinframe s2 = new signinframe();
					JButton btn = (JButton)ae.getSource();
					JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
					currentFrame.dispose();
				}
				else{
					error.setText("Incorrect credentials");
				}
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		menu m = new menu();
	}
}

class signinframe extends JFrame implements ActionListener
{
	public void centerFrameOnScreen()
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	JButton make_appointment, manage_doctors, view_tables;
	signinframe()
	{
		make_appointment = new JButton("MAKE APPOINTMENT");
		manage_doctors = new JButton("MANAGE DOCTORS");
		view_tables = new JButton("VIEW DATA");
		add(make_appointment);
		add(manage_doctors);
		add(view_tables);
		setLayout(null);
		make_appointment.setBounds(39, 40, 210, 30);
		manage_doctors.setBounds(39,110,210,30);
		view_tables.setBounds(39,180,210,30);
		setSize(300,330);
		centerFrameOnScreen();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		make_appointment.addActionListener(this);
		manage_doctors.addActionListener(this);
		view_tables.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		if(source==make_appointment)
		{
			app_make_frame s2 = new app_make_frame();
		}
		else if(source==manage_doctors)
		{
			DoctorMenu d1 = new DoctorMenu();
		}
		else if(source==view_tables)
		{
			View_menu v = new View_menu();
		}
		JButton btn = (JButton)ae.getSource();
		JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
		currentFrame.dispose();
	}
}

class app_make_frame extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	String id_make(int toggle) 
	{
		Random random = new Random();
		int randomfourDigitNumber = random.nextInt(9000) + 1000;
		if(toggle==1)
		{
			return "P" + randomfourDigitNumber;
		}
		else
		{
			return "A" + randomfourDigitNumber;
		}
	}    
	JLabel PID, AID, pid, aid, NAME, AGE, NUMBER, DID, DATE, TIME, msg;
	JTextField name, age, number, did, date, time; 
	JButton appointment_confirm, back;
	app_make_frame()
	{
		msg=new JLabel();
		PID = new JLabel("PID");
		NAME = new JLabel("NAME");
		AGE = new JLabel("AGE");
		NUMBER = new JLabel("NUMBER");
		AID = new JLabel("AID");
		DID = new JLabel("DID");
		DATE = new JLabel("DATE");
		TIME = new JLabel("TIME");
		pid = new JLabel("");
		name = new JTextField(100);
		age = new JTextField(100);
		number = new JTextField(100);
		aid = new JLabel("");
		did = new JTextField(100);
		date = new JTextField(100);
		time = new JTextField(100);
		appointment_confirm = new JButton("CONFIRM APPOINTMENT");
		back = new JButton("BACK");
		add(msg);
		add(PID);
		add(NAME);
		add(AGE);
		add(NUMBER);
		add(AID);
		add(DID);
		add(DATE);
		add(TIME);
		add(pid);
		add(name);
		add(age);
		add(number);
		add(aid);
		add(did);
		add(date);
		add(time);
		add(appointment_confirm);
		add(back);
		setLayout(null);
		msg.setBounds(51, 380, 300, 30);
		PID.setBounds(39, 40, 150, 30);
		NAME.setBounds(39, 80, 150, 30);
		AGE.setBounds(39, 120, 150, 30);
		NUMBER.setBounds(39, 160, 150, 30);
		AID.setBounds(39, 200,150, 30);
		DID.setBounds(39, 240, 150, 30);
		DATE.setBounds(39, 280, 150, 30);
		TIME.setBounds(39, 320, 150, 30);
		pid.setBounds(210, 40, 150, 30);
		name.setBounds(210, 80, 150, 30);
		age.setBounds(210, 120, 150, 30);
		number.setBounds(210, 160, 150, 30);
		aid.setBounds(210, 200,150, 30);
		did.setBounds(210, 240, 150, 30);
		date.setBounds(210, 280, 150, 30);
		time.setBounds(210, 320, 150, 30);
		appointment_confirm.setBounds(51, 420, 300, 30);
		back.setBounds(51, 460, 300, 30);
		String patient_id = id_make(1);
		String appointment_id = id_make(0);
		pid.setText(patient_id);
		aid.setText(appointment_id);
		setSize(450,550);
		centerFrameOnScreen();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		appointment_confirm.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==appointment_confirm)
		{
			try
			{
				String said=aid.getText();
				String spid=pid.getText();
				String sdid=did.getText();
				String sdate=date.getText();
				String stime=time.getText();
				String sname=name.getText();
				String sage=age.getText();
				String snum=number.getText();
				String query1 = "INSERT INTO patient (PatientID, Patient_Name, Patient_Phno, Doc_ID, Ap_ID, Patient_Age) " + "VALUES ('" + spid + "','" + sname + "','" + snum + "','" + sdid + "','" + said + "','" + sage + "')";
				String query2 = "INSERT INTO appointment (Ap_ID, Patient_ID, Doc_ID, Date, Time, Status) " + "VALUES ('" + said + "','" + spid + "','" + sdid + "','" + sdate + "','" + stime + "','Scheduled')";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				st.executeUpdate(query1);
				st.executeUpdate(query2);
				msg.setText("Appointment successfull");
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}		
		}
		else if(ae.getSource()==back)
		{
			signinframe s2 = new signinframe();
			JButton btn = (JButton)ae.getSource();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
			currentFrame.dispose();
		}
	}
}

class DoctorMenu extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	JLabel head;
	JButton add,update,del,back;
	DoctorMenu()
	{
		super("Doctor Menu");
		head=new JLabel("Doctor Menu");
		add=new JButton("Add");
		update=new JButton("Update");
		del=new JButton("Delete");
		back=new JButton("Back");
		add(head);
		add(add);
		add(update);
		add(del);
		add(back);
		setLayout(null);
		head.setBounds(20,20,200,20);
		add.setBounds(20,50,150,30);
		update.setBounds(20,90,150,30);
		del.setBounds(20,130,150,30);
		back.setBounds(50,210,100,30);
		setSize(210, 300);
		centerFrameOnScreen();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add.addActionListener(this);
		update.addActionListener(this);
		del.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source=ae.getSource();
		if (source==add)
		{
			AddDoctor a1=new AddDoctor();
		}
		else if (source==update)
		{
			ModifyDoctor m1=new ModifyDoctor();
		}
		else if (source==del)
		{
			RemoveDoctor r1=new RemoveDoctor();
		}
		else if (source==back)
		{
			signinframe s = new signinframe();
		}
		JButton btn = (JButton)ae.getSource();
		JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
		currentFrame.dispose();
	}
}

class AddDoctor extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
    	JLabel head,id_l,name_l,dept_l,phone_l,msg;
	JTextField id_t,name_t,dept_t,phone_t;
	JButton create,back;
	AddDoctor()
	{
		super("New Record");
		head=new JLabel("New Record");
		id_l=new JLabel("Doctor Id");
		name_l=new JLabel("Name");
		dept_l=new JLabel("Department");
		phone_l=new JLabel("Phone No.");
		msg=new JLabel();
		id_t=new JTextField();
		name_t=new JTextField();
		dept_t=new JTextField();
		phone_t=new JTextField();
		create=new JButton("Create");
		back=new JButton("Back");
		add(head);
		add(id_l);
		add(name_l);
		add(dept_l);
		add(phone_l);
		add(msg);
		add(id_t);
		add(name_t);
		add(dept_t);
		add(phone_t);
		add(create);
		add(back);
		setLayout(null);
		head.setBounds(20,20,200,20);
		id_l.setBounds(20,50,80,20);
		name_l.setBounds(20,80,80,20);
		dept_l.setBounds(20,110,80,20);
		phone_l.setBounds(20,140,80,20);
		msg.setBounds(20,170,200,20);
		id_t.setBounds(120,50,150,20);
		name_t.setBounds(120,80,150,20);
		dept_t.setBounds(120,110,150,20);
		phone_t.setBounds(120,140,150,20);
		create.setBounds(20,200,80,30);
		back.setBounds(120,200,80,30);
		setSize(300, 300);
		centerFrameOnScreen();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		create.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source=ae.getSource();
		if (source==create)
		{
			try
			{
				String id=id_t.getText();
				String name=name_t.getText();
				String dept=dept_t.getText();
				String phone=phone_t.getText();
				String query="insert into Doctor (Doc_ID,Doc_Name,Doc_Dept,Doc_Phno) values ('"+id+"','"+name+"','"+dept+"','"+phone+"')";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				st.executeUpdate(query);
				msg.setText("Record added successfully.");
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (source==back)
		{
			dispose();
			JButton btn = (JButton)ae.getSource();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
			currentFrame.dispose();
			DoctorMenu d = new DoctorMenu(); 
		}
	}
}

class ModifyDoctor extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	JLabel head,id_l,name_l,dept_l,phone_l,msg;
	JTextField id_t,name_t,dept_t,phone_t;
	JButton fetch,update,back;
	ModifyDoctor()
	{
		super("Edit Record");
		head=new JLabel("Edit Record");
		id_l=new JLabel("Doctor Id");
		name_l=new JLabel("Name");
		dept_l=new JLabel("Department");
		phone_l=new JLabel("Phone No.");
		msg=new JLabel();
		id_t=new JTextField();
		name_t=new JTextField();
		dept_t=new JTextField();
		phone_t=new JTextField();
		fetch=new JButton("Fetch");
		update=new JButton("Update");
		back=new JButton("Back");
		add(head);
		add(id_l);
		add(name_l);
		add(dept_l);
		add(phone_l);
		add(msg);
		add(id_t);
		add(name_t);
		add(dept_t);
		add(phone_t);
		add(fetch);
		add(update);
		add(back);
		setLayout(null);
		head.setBounds(20,20,200,20);
		id_l.setBounds(20,50,80,20);
		name_l.setBounds(20,80,80,20);
		dept_l.setBounds(20,110,80,20);
		phone_l.setBounds(20,140,80,20);
		msg.setBounds(20,170,200,20);
		id_t.setBounds(120,50,150,20);
		name_t.setBounds(120,80,150,20);
		dept_t.setBounds(120,110,150,20);
		phone_t.setBounds(120,140,150,20);
		fetch.setBounds(20,200,80,30);
		update.setBounds(120,200,80,30);
		back.setBounds(220,200,80,30);
		setSize(400, 300);
		centerFrameOnScreen();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		fetch.addActionListener(this);
		update.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source=ae.getSource();
		if (source==fetch)
		{
			try
			{
				String id=id_t.getText();
				String query="select Doc_Name,Doc_Dept,Doc_Phno from Doctor where Doc_ID='"+id+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(query);
				if(rs.next())
				{
					String name=rs.getString("Doc_Name");
					String dept=rs.getString("Doc_Dept");
					String phno=rs.getString("Doc_Phno");
					name_t.setText(name);
					dept_t.setText(dept);
					phone_t.setText(phno);
				}
				rs.close();
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (source==update)
		{
			try
			{
				String id=id_t.getText();
				String name=name_t.getText();
				String dept=dept_t.getText();
				String phone=phone_t.getText();
				String query1="update Doctor set Doc_Name='"+name+"' where Doc_ID='"+id+"'";
				String query2="update Doctor set Doc_Dept='"+dept+"' where Doc_ID='"+id+"'";
				String query3="update Doctor set Doc_Phno='"+phone+"' where Doc_ID='"+id+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				st.executeUpdate(query1);
				st.executeUpdate(query2);
				st.executeUpdate(query3);
				msg.setText("Record updated successfully.");
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (source==back)
		{
			dispose();
			JButton btn = (JButton)ae.getSource();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
			currentFrame.dispose();
			DoctorMenu d = new DoctorMenu(); 
		}
	}
}

class RemoveDoctor extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}  
	JLabel head,id_l,msg;
	JTextField id_t;
	JButton delete,back;
	RemoveDoctor()
	{
		super("Remove Record");
		head=new JLabel("Remove Record");
		id_l=new JLabel("Doctor Id");
		msg=new JLabel();
		id_t=new JTextField();
		delete=new JButton("Delete");
		back=new JButton("Back");
		add(head);
		add(id_l);
		add(msg);
		add(id_t);
		add(delete);
		add(back);
		setLayout(null);
		head.setBounds(20,20,200,20);
		id_l.setBounds(20,50,80,20);
		msg.setBounds(20,80,200,20);
		id_t.setBounds(120,50,150,20);
		delete.setBounds(20,110,80,30);
		back.setBounds(120,110,80,30);
		setSize(300, 200);
		centerFrameOnScreen();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		delete.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source=ae.getSource();
		if (source==delete)
		{
			try
			{
				String id=id_t.getText();
				String query="delete from Doctor where Doc_ID='"+id+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				st.executeUpdate(query);
				msg.setText("Record deleted successfully.");
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
		else if (source==back)
		{
			dispose();
			JButton btn = (JButton)ae.getSource();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
			currentFrame.dispose();
			DoctorMenu d = new DoctorMenu(); 
		}
	}
}

class View_menu extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	JButton view_appointments, view_doctors, view_patients, back;
	View_menu()
	{
		view_appointments = new JButton("VIEW APPOINTMENTS");
		view_doctors = new JButton("VIEW DOCTORS");
		view_patients = new JButton("VIEW PATIENTS");
		back = new JButton("BACK");
		add(view_appointments);
		add(view_doctors);
		add(view_patients);
		add(back);
		setLayout(null);
		view_appointments.setBounds(39, 40, 210, 30);
		view_doctors.setBounds(39,80,210,30);
		view_patients.setBounds(39,120,210,30);
		back.setBounds(39, 160, 210, 30);
		setSize(300,270);
		centerFrameOnScreen();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		view_appointments.addActionListener(this);
		view_doctors.addActionListener(this);
		view_patients.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		if(source==view_appointments)
		{
			app_view a = new app_view();
		}
		else if(source==view_patients)
		{
			p_view p =new p_view();
		}
		else if(source==view_doctors)
		{
			d_view d = new d_view();
		}
		else if(source==back)
		{
			signinframe s = new signinframe();   
		}
		JButton btn = (JButton)ae.getSource();
		JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
		currentFrame.dispose();
	}
}

class app_view extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	JLabel AID, PID, DID, DATE, TIME;
	JTextField pid, did, date, time, aid;
	JButton back, display;
	app_view()
	{	
		AID = new JLabel("Enter AID");
		PID = new JLabel("Patient ID");
		DID = new JLabel("Doctor ID");
		DATE = new JLabel("DATE");
		TIME = new JLabel("TIME");
		pid = new JTextField();
		did = new JTextField();
		date = new JTextField();
		time = new JTextField();
		aid = new JTextField();
		back = new JButton("BACK");
		display = new JButton("DISPLAY");
		add(back);
		add(display);
		add(AID);
		add(PID);
		add(DID);
		add(DATE);
		add(TIME);
		add(aid);
		add(pid);
		add(did);
		add(date);
		add(time);
		setLayout(null);
		AID.setBounds(40, 40, 150, 30);
		PID.setBounds(40, 80, 150, 30);
		DID.setBounds(40, 120, 150, 30);
		DATE.setBounds(40, 160, 150, 30);
		TIME.setBounds(40, 200, 150, 30);
		aid.setBounds(210, 40, 150, 30);
		pid.setBounds(210, 80, 150, 30);
		did.setBounds(210, 120, 150, 30);
		date.setBounds(210, 160, 150, 30);
		time.setBounds(210, 200, 150, 30);
		display.setBounds(50, 250, 300, 30);
		back.setBounds(50, 290, 300, 30);
		setSize(450,390);
		centerFrameOnScreen();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		display.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		if(source==back)
		{
			View_menu v = new View_menu();
			JButton btn = (JButton)ae.getSource();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
			currentFrame.dispose();
		}
		else if(source==display)
		{
			try
			{
				String said=aid.getText();
				String query="select Patient_ID,Doc_ID,Date,Time from Appointment where Ap_ID='"+said+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(query);
				if(rs.next())
				{
					String spid=rs.getString("Patient_ID");
					String sdid=rs.getString("Doc_ID");
					String sdate=rs.getString("Date");
					String stime=rs.getString("Time");
					pid.setText(spid);
					did.setText(sdid);
					date.setText(sdate);
					time.setText(stime);
				}
				rs.close();
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}

class d_view extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	JLabel DID, NAME, DEPT, NO;
	JTextField did, name, dept, no;
	JButton back, display;
	d_view()
	{
		DID = new JLabel("Enter Doctor ID");
		NAME = new JLabel("Name");
		DEPT = new JLabel("Department");
		NO = new JLabel("Phone number");
		did = new JTextField();
		name = new JTextField();
		dept = new JTextField();
		no = new JTextField();
		back = new JButton("BACK");
		display = new JButton("DISPLAY");
		add(back);
		add(display);
		add(DID);
		add(NAME);
		add(DEPT);
		add(NO);
		add(did);
		add(name);
		add(dept);
		add(no);
		setLayout(null);
		DID.setBounds(40, 40, 150, 30);
		NAME.setBounds(40, 80, 150, 30);
		DEPT.setBounds(40, 120, 150, 30);
		NO.setBounds(40, 160, 150, 30);
		did.setBounds(210, 40, 150, 30);
		name.setBounds(210, 80, 150, 30);
		dept.setBounds(210, 120, 150, 30);
		no.setBounds(210, 160, 150, 30);
		display.setBounds(50, 210, 300, 30);
		back.setBounds(50, 250, 300, 30);
		setSize(450,350);
		centerFrameOnScreen();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		display.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		if(source==back)
		{
			View_menu v = new View_menu();
			JButton btn = (JButton)ae.getSource();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
			currentFrame.dispose();
		}
		else if(source==display)
		{
			try
			{
				String sid=did.getText();
				String query="select Doc_Name,Doc_Dept,Doc_Phno from Doctor where Doc_ID='"+sid+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(query);
				if(rs.next())
				{
					String sname=rs.getString("Doc_Name");
					String sdept=rs.getString("Doc_Dept");
					String sphno=rs.getString("Doc_Phno");
					name.setText(sname);
					dept.setText(sdept);
					no.setText(sphno);
				}
				rs.close();
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}

class p_view extends JFrame implements ActionListener
{
	public void centerFrameOnScreen() 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int) screenSize.getWidth() / 2;
		int centerY = (int) screenSize.getHeight() / 2;
		int frameX = centerX - getWidth() / 2;
		int frameY = centerY - getHeight() / 2;
		setLocation(frameX, frameY);
	}
	JLabel PID, NAME, AGE, NO, DOB;
	JTextField pid, name, age, no, dob;
	JButton back, display;
	p_view()
	{
		PID = new JLabel("Enter Patient ID");
		NAME = new JLabel("Name");
		AGE = new JLabel("Age");
		NO = new JLabel("Phone number");
		DOB = new JLabel("Year of Birth");
		pid = new JTextField();
		name = new JTextField();
		age = new JTextField();
		no = new JTextField();
		dob = new JTextField();
		back = new JButton("BACK");
		display = new JButton("DISPLAY");
		add(back);
		add(display);
		add(PID);
		add(NAME);
		add(AGE);
		add(NO);
		add(DOB);
		add(pid);
		add(name);
		add(age);
		add(no);
		add(dob);
		setLayout(null);
		PID.setBounds(40, 40, 150, 30);
		NAME.setBounds(40, 80, 150, 30);
		AGE.setBounds(40, 120, 150, 30);
		NO.setBounds(40, 160, 150, 30);
		DOB.setBounds(40, 200, 150, 30);
		pid.setBounds(210, 40, 150, 30);
		name.setBounds(210, 80, 150, 30);
		age.setBounds(210, 120, 150, 30);
		no.setBounds(210, 160, 150, 30);
		dob.setBounds(210, 200, 150, 30);
		display.setBounds(50, 250, 300, 30);
		back.setBounds(50, 290, 300, 30);
		setSize(450,390);
		centerFrameOnScreen();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		display.addActionListener(this);
		back.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();
		if(source==back)
		{
			View_menu v = new View_menu();
			JButton btn = (JButton)ae.getSource();
			JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btn);
			currentFrame.dispose();
		}
		else if(source==display)
		{
			try
			{
				String spid=pid.getText();
				String query="select Patient_Name,Patient_Age,Patient_Phno,Patient_DOB from Patient where PatientID='"+spid+"'";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","dillw@@d_pass_IS_we@k_#WTF");
				Statement st = con.createStatement();
				ResultSet rs=st.executeQuery(query);
				if(rs.next())
				{
					String sname=rs.getString("Patient_Name");
					String sage=rs.getString("Patient_Age");
					String sphno=rs.getString("Patient_Phno");
					String sdob=rs.getString("Patient_DOB");
					int sdob1int = 2023-Integer.parseInt(sage);
					String sdob1 = Integer.toString(sdob1int);//String.parseString(sdob1int);
					name.setText(sname);
					age.setText(sage);
					no.setText(sphno);
					dob.setText(sdob1);
				}
				rs.close();
				st.close();
				con.close();
			}
			catch(ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
		}
	} 
}