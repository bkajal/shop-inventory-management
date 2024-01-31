package com.qsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class ShopView {
	static Scanner myInput = new Scanner(System.in);
	static Product product = new Product();
	static ShopController shopController = new ShopController();
	static {
		System.out.println(" ------------------------------------------ ");
		System.out.println("| --WELCOME TO SHOP INVENTORY MANAGEMENT-- |");
		System.out.println(" ------------------------------------------ ");
	}
	public static void main(String[] args) {
		do {
			System.out.println(" ----------------------------------");
			System.out.println("| --Select Operation to perform-- |");
			System.out.println(" ----------------------------------");
			System.out.println("-->1.Add Products\n-->2.Remove Product\n-->3.Update Product Details\n-->4.Fetch Product\n-->0.EXIT");
			System.out.print("\n=> Enter digit respective to desired option : ");
			int userInput = myInput.nextInt();
			myInput.nextLine();
			
			switch(userInput) {
			case 0 :
				//exit the program
				myInput.close();
				System.out.println(" -------------");
				System.out.println("| ---EXITED-- |");
				System.out.println(" -------------");
				System.exit(0);
				break;
			case 1 :
				//Add product
				System.out.print(" ==> How Many Products you want to Add ? : ");
				int productCount = myInput.nextInt();
				myInput.nextLine();
				if (productCount==1) {
					System.out.print("Enter product id : ");
					int i_p_id = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter product name : ");
					String i_p_name = myInput.nextLine();
					System.out.print("Enter product price : ");
					int i_p_price = myInput.nextInt();
					myInput.nextLine();
					System.out.print("Enter product quantity : ");
					int i_p_quantity = myInput.nextInt();
					myInput.nextLine();
					boolean i_p_availability = false;
					if (i_p_quantity>0) {
						i_p_availability = true;
					}
					if ((shopController.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability))!=0) {
						System.out.println(" -------------------");
						System.out.println("| --Product Added-- |");
						System.out.println(" -------------------");
					} else {
						System.out.println(" -----------------------");
						System.out.println("| --Product not Added-- |");
						System.out.println(" -----------------------");
					}
				} else {
					boolean toContinue = true;
					ArrayList<Product> products = new ArrayList<Product>();
					do {
						Product product = new Product();
						System.out.print("Enter product id : ");
						product.setP_id(myInput.nextInt());
						myInput.nextLine();
						System.out.print("Enter product name : ");
						product.setP_name(myInput.nextLine());
						System.out.print("Enter product price : ");
						product.setP_price(myInput.nextInt());
						myInput.nextLine();
						System.out.print("Enter product quantity : ");
						int quantity = myInput.nextInt();
						product.setP_quantity(quantity);
						myInput.nextLine();
						boolean availability = false;
						if (quantity>0) {
							availability = true;
						}
						product.setP_availability(availability);
						products.add(product);
						System.out.println("Press 1 to continue adding products |OR| Press 0 to stop adding products");
						int toAdd = myInput.nextInt();
						myInput.nextLine();
						if(toAdd==0)
						{
							toContinue=false;
						}
					}
					while(toContinue);
					if (shopController.addMultipleProducts(products)) {
						System.out.println(" ------------------------------");
						System.out.println("| --Products have been Added-- |");
						System.out.println(" ------------------------------");
					} else {
						System.out.println(" ------------------------");
						System.out.println("| --Products not Added-- |");
						System.out.println(" ------------------------");
					}
					
				}
				break;
			case 2 :
				//remove product
				System.out.print("Enter product id to remove : ");
				int productToRemove = myInput.nextInt();
				myInput.nextLine();
				if (shopController.removeProduct(productToRemove) != 0) {
					System.out.println(" ------------------------------");
					System.out.println("| --PRODUCT HAS BEEN REMOVED-- | ");
					System.out.println(" ------------------------------");
				} else {
					System.out.println(" ----------------------------------------------------------- ");
					System.out.println("| --PRODUCT with ID : "+productToRemove+" does not exists-- |");
					System.out.println(" ----------------------------------------------------------- ");
					System.out.println("           ---------------------------------------- ");
					System.out.println("          | --Removal of product is not possible-- |");
					System.out.println("           ---------------------------------------- ");
					
				}
				break;
			case 3 :
				//Update product features
				try {
					System.out.print("Enter product id to update : ");
					int productToUpdate = myInput.nextInt();
					myInput.nextLine();
					//Find Product to update
					ResultSet product = shopController.fetchProduct(productToUpdate);
					if (product.next()) {
						System.out.println("What do you want to update ? ");
						System.out.println("-->1.Name\n-->2.Price\n-->3.Quantity");
						System.out.print("==> Enter number respective to desired option : ");
						byte updateOption = myInput.nextByte();
						myInput.nextLine();
						switch(updateOption) {
						case 1 :
							//Update product name
							System.out.print("Enter name to update : ");
							String nameToUpdate = myInput.nextLine();
							if (shopController.updateProductName(productToUpdate, nameToUpdate) != 0) {
								System.out.println(" -------------------------- ");
								System.out.println("| --PRODUCT NAME UPDATED-- |");
								System.out.println(" -------------------------- ");
							} else {
								System.out.println(" ------------------------------------ ");
								System.out.println("| --PRODUCT NAME CANNOT BE UPDATED-- |");
								System.out.println(" ------------------------------------ ");
							}
							break;
						case 2 :
							//Update product price
							System.out.print("Enter price to update : ");
							int priceToUpdate = myInput.nextInt();
							myInput.nextLine();
							if (shopController.updateProductPrice(productToUpdate, priceToUpdate) != 0) {
								System.out.println(" --------------------------- ");
								System.out.println("| --PRODUCT PRICE UPDATED-- |");
								System.out.println(" --------------------------- ");
							} else {
								System.out.println(" ------------------------------------- ");
								System.out.println("| --PRODUCT PRICE CANNOT BE UPDATED-- |");
								System.out.println(" ------------------------------------- ");
							}
							break;
						case 3 :
							//Update product quantity
							System.out.print("Enter quantity to update : ");
							int quantityToUpdate = myInput.nextInt();
							myInput.nextLine();
							if (shopController.updateProductQuantity(productToUpdate, quantityToUpdate) != 0) {
								System.out.println(" ------------------------------ ");
								System.out.println("| --PRODUCT QUANTITY UPDATED-- |");
								System.out.println(" ------------------------------ ");
								shopController.updateProductAvailability(productToUpdate, quantityToUpdate);
							} else {
								System.out.println(" ---------------------------------------- ");
								System.out.println("| --PRODUCT QUANTITY CANNOT BE UPDATED-- |");
								System.out.println(" ---------------------------------------- ");
							}
							break;
						}
					} else {
						System.out.println(" ----------------------------------------------------------- ");
						System.out.println("| --PRODUCT with ID : "+productToUpdate+" does not exists-- |");
						System.out.println(" ----------------------------------------------------------- ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case 4 :
				//Find Product
				try {
					System.out.print("Enter product id to fetch : ");
					int productToFind = myInput.nextInt();
					myInput.nextLine();
					ResultSet fetchProduct = shopController.fetchProduct(productToFind);
					// System.out.println(fetchProduct); --> ResultSet reference will be created even if the id is present in database or not.
					boolean next = fetchProduct.next();
					if (next) {
						System.out.println(" ------------------------------------------------ ");
						System.out.println("|             --PRODUCT DETAILS--                |");
						System.out.println("|             ID : "+ fetchProduct.getInt(1)+"                             |");
						System.out.println("|             NAME : "+fetchProduct.getString(2)+"                     |");
						System.out.println("|             PRICE : "+ fetchProduct.getInt(3)+"                         |");
						System.out.println("|             QUANTITY : "+ fetchProduct.getInt(4)+"                      |");
						System.out.println(" ------------------------------------------------ ");
						if (fetchProduct.getBoolean(5)) {
							System.out.println(" -------------------------------------- ");
							System.out.println("  | --AVAILABILITY : PRODUCT AVAILABLE-- |");
							System.out.println(" -------------------------------------- ");
						} else {
							System.out.println(" ------------------------------------------ ");
							System.out.println("  | --AVAILABILITY : PRODUCT NOT AVAILABLE-- |");
							System.out.println(" ------------------------------------------ ");
						}
						System.out.println();
					} else {
						System.out.println(" ----------------------------------------------------------- ");
						System.out.println("| --PRODUCT with ID : "+productToFind+" does not exists-- |");
						System.out.println(" ----------------------------------------------------------- ");
						
						System.out.println();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default :
				System.out.println(" ----------------------- ");
				System.out.println("  | --INVALID SELECTION-- |");
				System.out.println(" ----------------------- ");
			}
		}while(true);
	}

}
