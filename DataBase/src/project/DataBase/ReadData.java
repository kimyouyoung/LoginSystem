package project.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.ResultSet;

public class ReadData {
	
	Connection con = null;
	
	public int read(String p_id, String p_pw) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        System.out.println("연결 성공");
	        
	        String query = "select * from userInfo;";
	        
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        
	        int count = 0;
	       
	        while(rs.next()) {
	        	int num = rs.getInt("num");
	        	String name = rs.getString("name");
	        	String id = rs.getString("id");
	        	String password = rs.getString("password");
	        	String birth = rs.getString("birth");
	        	
	        	System.out.printf("%d, %s, %s, %s, %s\n", num, name, id, password, birth);
	        	
	        	if(p_id.equals(id) && p_pw.equals(password)) {
	        		count++;
	        	}
	        }
	        
	        if(count == 0) 
	        	return 1;
	        
	       
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
		
		return 0;
	}
	
	public boolean readId(String p_id) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        
	        String query = "select * from userInfo;";
	        
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        
	       
	        while(rs.next()) {
	        	String id = rs.getString("id");
	        	
	        	if(p_id.equals(id)) {
	        		return false;
	        	}
	        }
	       
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
		
		
		return true;
	}
	
	public String return_data(String id, int catagory) {
		String data = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        
	        String query = "select * from userInfo;";
	        
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        
	       
	        while(rs.next()) {
	        	String p_id = rs.getString("id");
	        	if(p_id.equals(id)) {
	        		if(catagory == 1) {
	        			data = rs.getString("name");
	        			break;
	        		}else if(catagory == 2) {
	        			data = rs.getString("password");
	        			break;
	        		}else if(catagory == 3) {
	        			data = rs.getString("birth");
	        			break;
	        		}else if(catagory == 4) {
	        			data = rs.getString("gender");
	        			break;
	        		}else if(catagory == 5) {
	        			data = rs.getString("major");
	        			break;
	        		}else if(catagory == 6) {
	        			data = rs.getString("email");
	        			break;
	        		}else {
	        			data = rs.getString("hp");
	        			break;
	        		}
	        	}
	        	
	        }
	       
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
		return data;
	}
	
	public int return_num(String id) {
		int num = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        
	        String query = "select * from userInfo;";
	        
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        
	       
	        while(rs.next()) {
	        	String p_id = rs.getString("id");
	        	if(p_id.equals(id)) {
	        		num = rs.getInt("num");
	        		break;
	        	}
	        	
	        }
	       
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
		
		return num;
	}
	
	@SuppressWarnings("null")
	public Vector<String[]> read_Alldata(){
		
		Vector<String[]> data = new Vector<String[]>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        System.out.println("연결 성공");
	        
	        String query = "select * from userInfo;";
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        String[] str = null;
	       
	        while(rs.next()) {
	        	str = new String[9];
	        	
	        	str[0] = String.valueOf(rs.getInt("num"));
	        	str[1] = rs.getString("name");
	        	str[2] = rs.getString("id");
	        	str[3] = rs.getString("password");
	        	str[4] = rs.getString("birth");
	        	str[5] = rs.getString("gender");
	        	str[6] = rs.getString("major");
	        	str[7] = rs.getString("email");
	        	str[8] = rs.getString("hp");
	        	
	        	
	        	data.add(str);
	        }
	        
	        
	       
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
		
		return data;
	}
	
	
	public Vector<String[]> return_part(int data_num, String data_con){
		Vector<String[]> data = new Vector<String[]>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        System.out.println("연결 성공");
	        
	        String query = "select * from userInfo;";
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        String[] str = null;
	        
	        String[] type = {"num", "name", "id", "password", "birth", "gender", "major", "email", "hp"};
	       
	        while(rs.next()) {
	        	
	        	if(data_con.equals(rs.getString(type[data_num]))) {
	        		str = new String[9];
		        	
		        	str[0] = String.valueOf(rs.getInt("num"));
		        	str[1] = rs.getString("name");
		        	str[2] = rs.getString("id");
		        	str[3] = rs.getString("password");
		        	str[4] = rs.getString("birth");
		        	str[5] = rs.getString("gender");
		        	str[6] = rs.getString("major");
		        	str[7] = rs.getString("email");
		        	str[8] = rs.getString("hp");
		        	
		        	data.add(str);
        		}
	        }
	        
	        
	       
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
		
		return data;
	}
	
	public String return_id(String data, int cata) {
		String id = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost/login?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        
	        con = DriverManager.getConnection(url, "root", "1234");
	        System.out.println("연결 성공");
	        
	        String query = "select * from userInfo;";
	        
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);
	       
	        while(rs.next()) {
	        	if(cata == 1) {
	        		int num = rs.getInt("num");
	        		if(num == Integer.parseInt(data)) {
	        			id = rs.getString("id");
	        			break;
	        		}
	        	}else {
	        		String hp = rs.getString("hp");
	        		if(hp.equals(data)) {
	        			id = rs.getString("id");
	        			break;
	        		}
	        	}
	        }
	        
	        
	       
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
		
		
		return id;
	}
	
}
