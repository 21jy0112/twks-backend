package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Employee;
import model.MotionData;
import model.ScreenshotData;

public class TwksDBAccess {
	private Connection con = null;

	// TwksDao オブジェクト生成時に DB に接続
	public TwksDBAccess() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			String url = "jdbc:mysql://10.64.144.5:3306/21jygr03?characterEncoding=UTF-8";
			con = DriverManager.getConnection(url, "21jygr03", "21jygr03");
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	// DB 切断
	public void connectionClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public ArrayList<Employee> empFindAll() {
		String sql = "SELECT * FROM emp";
		ArrayList<Employee> ary = null;
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			ary = new ArrayList<Employee>();
			while (rs.next()) {
				ary.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
	
	public ArrayList<Employee> empSearchByDept(String dept) {
		String sql = "SELECT * FROM emp WHERE dept_code = ?";
		ArrayList<Employee> ary = null;
		try {
			Statement state = con.createStatement();
			PreparedStatement pstate = con.prepareStatement(sql);
			pstate.setString(1, dept);
			ResultSet rs = pstate.executeQuery();
			ary = new ArrayList<Employee>();
			while (rs.next()) {
				ary.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
	
	public ArrayList<Employee> empSearchByDeptAndId(String dept,String id) {
		String sql = "SELECT * FROM emp WHERE dept_code = ? AND emp_id = ?";
		ArrayList<Employee> ary = null;
		try {
			Statement state = con.createStatement();
			PreparedStatement pstate = con.prepareStatement(sql);
			pstate.setString(1, dept);
			pstate.setString(2, id);
			ResultSet rs = pstate.executeQuery();
			ary = new ArrayList<Employee>();
			while (rs.next()) {
				ary.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
	
	public ArrayList<Employee> empSearchByDeptAndName(String dept,String name) {
		String sql = "SELECT * FROM emp WHERE dept_code = ? AND emp_name LIKE ?";
		ArrayList<Employee> ary = null;
		try {
			Statement state = con.createStatement();
			PreparedStatement pstate = con.prepareStatement(sql);
			String param = "%"+name+"%";
			pstate.setString(1, dept);
			pstate.setString(2, param);
			ResultSet rs = pstate.executeQuery();
			ary = new ArrayList<Employee>();
			while (rs.next()) {
				ary.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
	
	public ArrayList<Employee> empSearchById(String id) {
		String sql = "SELECT * FROM emp WHERE emp_id = ?";
		ArrayList<Employee> ary = null;
		try {
			Statement state = con.createStatement();
			PreparedStatement pstate = con.prepareStatement(sql);
			pstate.setString(1, id);
			ResultSet rs = pstate.executeQuery();
			ary = new ArrayList<Employee>();
			while (rs.next()) {
				ary.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
	
	public ArrayList<Employee> empSearchByName(String name) {
		String sql = "SELECT * FROM emp WHERE emp_name LIKE ?";
		ArrayList<Employee> ary = null;
		try {
			Statement state = con.createStatement();
			PreparedStatement pstate = con.prepareStatement(sql);
			String param = "%"+name+"%";
			pstate.setString(1, param);
			ResultSet rs = pstate.executeQuery();
			ary = new ArrayList<Employee>();
			while (rs.next()) {
				ary.add(new Employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
						rs.getString(13),rs.getString(14)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
	
	public void addEmp(Employee emp) {
		Statement stmt = null;
		String sql = "INSERT INTO emp VALUES(?,?,?)";
		try {
			stmt = con.createStatement();
			PreparedStatement pstate = con.prepareStatement(sql);
			//pstate.setString(1, emp.getName());
			//pstate.setString(2, emp.getContent());
			//pstate.setString(3, emp.getTwitTime());
			pstate.execute();
			ResultSet rset = pstate.executeQuery();
			pstate.close();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	public ArrayList<MotionData> motionFindAll(){
		String sql = "SELECT * FROM motion_data";
		ArrayList<MotionData> ary = null;
		try {
			Statement state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			ary = new ArrayList<MotionData>();
			while (rs.next()) {
				ary.add(new MotionData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
	
	public ArrayList<ScreenshotData> screenshotSearchById(String id,String datetime){
		String sql = "SELECT * FROM screenshot_data WHERE emp_id = ? AND screenshot_data_datetime LIKE ?";
		ArrayList<ScreenshotData> ary = null;
		try {
			Statement state = con.createStatement();
			PreparedStatement pstate = con.prepareStatement(sql);
			String param = "%"+datetime+"%";
			pstate.setString(1, id);
			pstate.setString(2, param);
			ResultSet rs = pstate.executeQuery();
			ary = new ArrayList<ScreenshotData>();
			while (rs.next()) {
				ary.add(new ScreenshotData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ary;
	}
}
