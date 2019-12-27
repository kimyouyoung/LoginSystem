package project.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteData {
	
	Connection con = null;
	
	public void write(String p_name, String p_id, String p_pw, String p_birth, int p_gender, String p_major, String p_hp, String p_email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        
	        String sql = "insert into userInfo (name, id, password, birth, gender, major, hp) values (?, ?, ?, ?, ?, ?, ?)";
	        String sql_major = "insert into userInfo (name, id, password, birth, gender, major, hp, email) values (?, ?, ?, ?, ?, ?, ?, ?)";
	        
	        
	        PreparedStatement pst;
	        if(p_email == null)
	        	pst = con.prepareStatement(sql);
	        else
	        	pst = con.prepareStatement(sql_major);
	        
	        String gender;
	        if(p_gender == 1)
	        	gender = "Male";
	        else
	        	gender = "Female";
	        
	        pst.setString(1, p_name);
	        pst.setString(2, p_id);
	        pst.setString(3, p_pw);
	        pst.setString(4, p_birth);
	        pst.setString(5, gender);
	        pst.setString(6, p_major);
	        pst.setString(7, p_hp);
	        if(p_email != null)
	        	pst.setString(8, p_email);
	        
	        
	        pst.executeUpdate();
	        
		}catch(ClassNotFoundException e){
	        System.out.println("드라이버 로딩 실패");
	    }
	    catch(SQLException e){
	        System.out.println("에러: " + e);
	        e.printStackTrace();
	    }
	    finally{
	        try{
	            if( con != null && !con.isClosed()){
	                con.close();
	            }
	        }
	        catch( SQLException e){
	            e.printStackTrace();
	        }
	    }
	}
	
	public void delete(int num) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        
	        String sql = "delete from userInfo where num=" + String.valueOf(num);
	        PreparedStatement pst = con.prepareStatement(sql);
	        
	        
	        pst.executeUpdate();
		}catch(ClassNotFoundException e){
	        System.out.println("드라이버 로딩 실패");
	    }
	    catch(SQLException e){
	        System.out.println("에러: " + e);
	        e.printStackTrace();
	    }
	    finally{
	        try{
	            if( con != null && !con.isClosed()){
	                con.close();
	            }
	        }
	        catch( SQLException e){
	            e.printStackTrace();
	        }
	    }
	}
	
	public void modify(String p_id, String p_pw, String p_birth, int p_gender, String p_major, String p_hp, String p_email) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        
	        String sql = "update userInfo set password=?, birth=?, gender=?, major=?, hp=? where id=?";
	        String sql_major = "update userInfo set password=?, birth=?, gender=?, major=?, hp=?, email=? where id=?";
	        PreparedStatement pst;
	        
	        if(p_email == null)
	        	pst = con.prepareStatement(sql);
	        else
	        	pst = con.prepareStatement(sql_major);
	        
	        String gender;
	        if(p_gender == 1)
	        	gender = "Male";
	        else
	        	gender = "Female";
	        
	        pst.setString(1, p_pw);
	        pst.setString(2, p_birth);
	        pst.setString(3, gender);
	        pst.setString(4, p_major);
	        pst.setString(5, p_hp);
	        if(p_email != null) {
	        	pst.setString(6, p_email);
	        	pst.setString(7, p_id);
	        }else {
	        	pst.setString(6, p_id);
	        }
	        
	        
	        pst.executeUpdate();
		}catch(ClassNotFoundException e){
	        System.out.println("드라이버 로딩 실패");
	    }
	    catch(SQLException e){
	        System.out.println("에러: " + e);
	        e.printStackTrace();
	    }
	    finally{
	        try{
	            if( con != null && !con.isClosed()){
	                con.close();
	            }
	        }
	        catch( SQLException e){
	            e.printStackTrace();
	        }
	    }
	}
	
	public void modify_part(String id, int data_num, String data) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        
	        String sql = null;
	        PreparedStatement pst;
	        
	        
	        if(data_num == 3) {
	        	sql = "update userInfo set password=?";
	        }else if(data_num == 4) {
	        	sql = "update userInfo set birth=?";
	        }else if(data_num == 5) {
	        	sql = "update userInfo set gender=?";
	        }else if(data_num == 6) {
	        	sql = "update userInfo set major=?";
	        }else if(data_num == 7) {
	        	sql = "update userInfo set email=?";
	        }else if(data_num == 8) {
	        	sql = "update userInfo set hp=?";
	        }
	        
	        pst = con.prepareStatement(sql);
	        pst.setString(1, data);
	        pst.executeUpdate();
	       
		}catch(ClassNotFoundException e){
	        System.out.println("드라이버 로딩 실패");
	    }
	    catch(SQLException e){
	        System.out.println("에러: " + e);
	        e.printStackTrace();
	    }
	    finally{
	        try{
	            if( con != null && !con.isClosed()){
	                con.close();
	            }
	        }
	        catch( SQLException e){
	            e.printStackTrace();
	        }
	    }
	}
}
