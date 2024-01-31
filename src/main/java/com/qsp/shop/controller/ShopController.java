package com.qsp.shop.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.postgresql.Driver;

import com.qsp.shop.model.Product;

public class ShopController {
	//addproduct 
	public int addProduct(int id, String name, int price, int quantity, boolean availability) {
		Connection connection=null;
		int rowsAffected = 0;
			try {
				Driver driver = new Driver();
				DriverManager.registerDriver(driver);
				FileInputStream file = new FileInputStream("dbconfig.properties");
				Properties properties = new Properties();
				properties.load(file);
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
				PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?)");
				prepareStatement.setInt(1, id);
				prepareStatement.setString(2, name);
				prepareStatement.setInt(3, price);
				prepareStatement.setInt(4, quantity);
				prepareStatement.setBoolean(5, availability);
				rowsAffected = prepareStatement.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rowsAffected;
	}
	//addMultiple products
	public boolean addMultipleProducts(ArrayList<Product> products) {
		Connection connection = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream file = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(file);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?)");
			for (Product product : products) {
				prepareStatement.setInt(1, product.getP_id());
				prepareStatement.setString(2, product.getP_name());
				prepareStatement.setInt(3, product.getP_price());
				prepareStatement.setInt(4, product.getP_quantity());
				prepareStatement.setBoolean(5, product.isP_availability());
				prepareStatement.addBatch();
			}
			prepareStatement.executeBatch();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	//fetch product using primary key
	public ResultSet fetchProduct(int id) {
		Connection connection = null;
		ResultSet resultSet = null;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream file = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(file);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM product WHERE p_id=?");
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultSet;
	}
	//remove product using primary key
	public int removeProduct(int id) {
		Connection connection = null;
		int executeUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream file = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(file);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM product WHERE p_id=?");
			prepareStatement.setInt(1, id);
			executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executeUpdate;
	}
	//update product name
	public int updateProductName(int id, String name) {
		Connection connection = null;
		int executeUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream file = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(file);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_name=? WHERE p_id=?");
			prepareStatement.setInt(2, id);
			prepareStatement.setString(1, name);
			executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executeUpdate;
	}
	//update product price
	public int updateProductPrice(int id, int price) {
		Connection connection = null;
		int executeUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream file = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(file);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_price=? WHERE p_id=?");
			prepareStatement.setInt(2, id);
			prepareStatement.setInt(1, price);
			executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executeUpdate;
	}
	//update product quantity
	public int updateProductQuantity(int id, int quantity) {
		Connection connection = null;
		int executeUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream file = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(file);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_quantity=? WHERE p_id=?");
			prepareStatement.setInt(1, quantity);
			prepareStatement.setInt(2, id);
			executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executeUpdate;
	}
	//update product availability
	public int updateProductAvailability(int id, int quantity) {
		Connection connection = null;
		int executeUpdate = 0;
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			FileInputStream file = new FileInputStream("dbconfig.properties");
			Properties properties = new Properties();
			properties.load(file);
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop_jdbc", properties);
			PreparedStatement prepareStatement = connection.prepareStatement("UPDATE product SET p_availability=? WHERE p_id=?");
			if (quantity>0) {
				prepareStatement.setBoolean(1, true);
			} else {
				prepareStatement.setBoolean(2, false);
			}
			prepareStatement.setInt(2, id);
			executeUpdate = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return executeUpdate;
	}
}