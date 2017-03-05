package movieCrawler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class saveToSql{
	public static void main (String[] args) throws java.sql.SQLException{
		String driver = "com.mysql.jdbc.Driver";        //调用mysql驱动器
        String url = "jdbc:mysql://192.168.235.20:3306/seeing?characterEncoding=utf-8";  //mysql的默认端口设为3306, database为具体连接的数据库名字
        String user = "root";  //用户名
        String password = "iiip"; //密码
        
    	String[] aa = {"爱乐之城","生化危机：终篇","乘风破浪","功夫瑜伽","极限特工：终极回归"};

        try{
        	Class.forName(driver);
            Connection  conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed()){
            	System.out.println("Succeed!");
            	Statement statement = conn.createStatement();   //statament是用于执行sql语句的容器
            	
            	for(int i=0;i<5;i++){
            		File file = new File("/home/jm/workspace/movieSql/movieData/views_content/"+aa[i]+"_views_content.txt");
                	Scanner input = new Scanner(file);
                	while(input.hasNextLine()){
                		String a = input.nextLine();
                		if(aa != null){
                			String[] mm = a.split("`");
                			
//               			插入票房数据
//                			if(mm.length==7){
//                    			String boxOfficesql = "insert into boxOffice(movie_name,week,release_time,people_number,week_boxOffice,total_boxOffice,days) values('"+mm[0]+"',"+ Integer.valueOf(mm[1]) +",'"+ mm[2] +"',"+ mm[3] +","+ mm[4] +","+ mm[5] +","+ mm[6]+");";
//                    			System.out.println(boxOfficesql);
//                       			statement.execute(boxOfficesql);
//                			}
              			
//                			插入新闻
//                			String movieName = aa[i]; 		
//                			if(mm.length==5){
//                    			String newsql = "replace into news(movie_name,news_title,news_url,origin,release_time,content) values('"+movieName+"','"+mm[0]+"','"+ mm[1] +"','"+ mm[2] +"','"+ mm[3] +"','"+ mm[4] +"');";
//                    			System.out.println(newsql);
//                				statement.execute(newsql);
//                			}
                   			
                			//插入影评
                			String movieName = aa[i];
                			if(mm.length == 2){
                				String viewsql = "replace into view_comment(movie_name,username,comment) values('"+movieName+"','"+mm[0]+"','"+ mm[1] +"');";
                				System.out.println(viewsql);
                				statement.execute(viewsql);
                			}


                		}
                	}
                	input.close();
                    }
            	}        	
            }catch(Exception e){
            	e.printStackTrace();
            	} 
        }

	}