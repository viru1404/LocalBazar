package org.lcbzr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lcbzr.model.Shop;

public class SearchDao {

	private static final String DRIVER_NAME="org.postgresql.Driver";//JDBC Driver name
	private static final String URL="jdbc:postgresql://qdjjtnkv.db.elephantsql.com/";//Database URL
	private static final String DB_NAME="tbbvztbv";
	//Database credentials
	private static final String USER_NAME="tbbvztbv";
	private static final String PASSWORD="nyITOsaqOfWjHLGkzg6A8BIBRNhrtFqm";
	
	Connection connection;
	
	public List<Shop> getShops(){
		
		List<Shop> shops=new ArrayList<>();
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				//Create Statement
				//stmt1=connection.prepareStatement(query1);
			   String query="SELECT * FROM app1_shop;";
			   stmt=connection.prepareStatement(query);
			   
			   resultSet=stmt.executeQuery();
			
			   while(resultSet.next()){
				   Shop shop=new Shop();
				   shop.setCost(resultSet.getString(2));
				   shop.setShopId(resultSet.getString(2));
				   shop.setName(resultSet.getString(3));
				   shop.setAddress(resultSet.getString(5));
				   shop.setLat(resultSet.getDouble(6));
				   shop.setLon(resultSet.getDouble(7));
				   shop.setHomeDelivery(resultSet.getBoolean(8));
				   //shop.setOpeningTime(openingTime);
				   shops.add(shop);
				   
			   }
			 //  System.out.println("No of shops is "+shops.size());
			  
			}   
		   catch(SQLException e){
			   e.printStackTrace();
		   }
			catch(InstantiationException e){
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			catch(IllegalAccessException e){
				e.printStackTrace();
			}
		   finally {
			
			   try{
				 
				   if(stmt!=null){
					   stmt.close();
				   }
				   if(resultSet!=null){
					   resultSet.close();
				   }
				   if(connection!=null){
					   connection.close();
				   }
			   }
			   catch(SQLException e){
				   e.printStackTrace();
			   }
		}
		
		
		return shops;
		
		
	}
	
	public List<Shop> getShopsDummy(int pId){

		List<Shop> shops=new ArrayList<>();
		PreparedStatement stmt=null;
		ResultSet resultSet=null;
		try{
			//Register JDBC Driver
				Class.forName(DRIVER_NAME).newInstance();
				//open a connection
				connection=DriverManager.getConnection(URL+DB_NAME,USER_NAME, PASSWORD);
			
				//Create Statement
				//stmt1=connection.prepareStatement(query1);
			   String query="SELECT * FROM app1_junction inner join app1_shop on app1_junction"
			   		+ ".sid_id=app1_shop.id WHERE app1_junction.pid_id=?";
			   stmt=connection.prepareStatement(query);
			   stmt.setInt(1, pId);
			   resultSet=stmt.executeQuery();
			
			   
			  while(resultSet.next()){
				   Shop shop=new Shop();
				  // shop.setShopId(resultSet.getString(7));
				   shop.setCost(resultSet.getString(2));
				   System.out.println(resultSet.getString(7));
				   shop.setName(resultSet.getString(8));
				   System.out.println(resultSet.getString(8));
				   shop.setAddress(resultSet.getString(10));
				   System.out.println(resultSet.getString(10));
				   shop.setLat(resultSet.getDouble(11));
				   System.out.println(resultSet.getString(11));
				   shop.setLon(resultSet.getDouble(12));
				   System.out.println(resultSet.getString(12));
				   shop.setHomeDelivery(resultSet.getBoolean(13));
				   System.out.println(resultSet.getString(13));
				   //shop.setOpeningTime(openingTime);
				   shops.add(shop);
				   System.out.println(shops.toString()+" Shop Length is "+shops.size());
			   }
			 //  System.out.println("No of shops is "+shops.size());
			  
			}   
		   catch(SQLException e){
			   e.printStackTrace();
		   }
			catch(InstantiationException e){
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			catch(IllegalAccessException e){
				e.printStackTrace();
			}
		   finally {
			
			   try{
				 
				   if(stmt!=null){
					   stmt.close();
				   }
				   if(resultSet!=null){
					   resultSet.close();
				   }
				   if(connection!=null){
					   connection.close();
				   }
			   }
			   catch(SQLException e){
				   e.printStackTrace();
			   }
		}
		
		
		return shops;
		
	
	}
}
