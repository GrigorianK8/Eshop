package org.example;

import org.example.command.Commands;
import org.example.manager.CategoryManager;
import org.example.manager.ProductManager;
import org.example.model.Category;
import org.example.model.Product;

import java.util.Scanner;

public class EshopMain implements Commands {
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static CategoryManager CATEGORY_MANAGER = new CategoryManager();
    private final static ProductManager PRODUCT_MANAGER = new ProductManager();


    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String command = SCANNER.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryById();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductById();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductById();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    printSumOfProducts();
                    break;
                case PRINT_MAX_OF_PRICE_PRODUCT:
                    printMaxOfPriceProduct();
                    break;
                case PRINT_MIN_OF_PRICE_PRODUCT:
                    printMinOfPriceProduct();
                    break;
                case PRINT_AVG_OF_PRICE_PRODUCT:
                    printAvgOfProduct();
                    break;
                default:
                    System.out.println("Unknown command!");
            }
        }
    }

    private static void printAvgOfProduct() {
        PRODUCT_MANAGER.printAvgOfProductPrices();
    }

    private static void printMinOfPriceProduct() {
        PRODUCT_MANAGER.printMinOfPriceProduct();
    }

    private static void printMaxOfPriceProduct() {
        PRODUCT_MANAGER.printMaxOfPriceProduct();
    }

    private static void printSumOfProducts() {
        PRODUCT_MANAGER.printSumOfProductPrices();
    }

    private static void deleteProductById() {
        PRODUCT_MANAGER.printAllProducts();
        System.out.println("Input product ID: ");
        int productId = Integer.parseInt(SCANNER.nextLine());
        Product productExist = PRODUCT_MANAGER.getProductById(productId);
        if (productExist == null) {
            System.out.println("Product with ID " + productId + " does not exist!");
        }
        PRODUCT_MANAGER.deleteProductById(productId);
        System.out.println("Product is removed!");
    }

    private static void editProductById() {
        PRODUCT_MANAGER.printAllProducts();
        System.out.println("Input product ID: ");
        int productId = Integer.parseInt(SCANNER.nextLine());
        Product productExist = PRODUCT_MANAGER.getProductById(productId);
        if (productExist == null) {
            System.out.println("Product with ID " + productId + " does not exist!");
            return;
        }
        System.out.println("Existing Product Details:");
        System.out.println("Name: " + productExist.getName());
        System.out.println("Description: " + productExist.getDescription());
        System.out.println("Price: " + productExist.getPrice());
        System.out.println("Quantity: " + productExist.getQuantity());
        System.out.println("Input new product name: ");
        String productName = SCANNER.nextLine();
        System.out.println("Input new description: ");
        String productDescription = SCANNER.nextLine();
        System.out.println("Input new product price: ");
        double productPrice = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Input new product quantity: ");
        int productQuantity = Integer.parseInt(SCANNER.nextLine());
        productExist.setName(productName);
        productExist.setDescription(productDescription);
        productExist.setPrice(productPrice);
        productExist.setQuantity(productQuantity);
        PRODUCT_MANAGER.editProduct(productExist);
        System.out.println("Product edited!");
    }

    private static void addProduct() {
        System.out.println("Input new product name: ");
        String productName = SCANNER.nextLine();
        System.out.println("Input description: ");
        String productDescription = SCANNER.nextLine();
        System.out.println("Input product price: ");
        double productPrice = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Input product quantity: ");
        int productQuantity = Integer.parseInt(SCANNER.nextLine());
        CATEGORY_MANAGER.printAllCategories();
        System.out.println("Input product categoryID: ");
        int productCategoryId = Integer.parseInt(SCANNER.nextLine());
        Product newProduct = new Product(0, productName, productDescription, productPrice, productQuantity, productCategoryId);
        PRODUCT_MANAGER.addProduct(newProduct);
        System.out.println("Product added!");
    }

    private static void deleteCategoryById() {
        CATEGORY_MANAGER.printAllCategories();
        System.out.println("Input category ID: ");
        int categoryId = Integer.parseInt(SCANNER.nextLine());
        Category categoryExist = CATEGORY_MANAGER.getCategoryById(categoryId);
        if (categoryExist == null) {
            System.out.println("Category with ID " + categoryId + " does not exist!");
            return;
        }
        CATEGORY_MANAGER.deleteCategoryById(categoryId);
        System.out.println("Category is removed!");
    }

    private static void editCategoryById() {
        CATEGORY_MANAGER.printAllCategories();
        System.out.println("Input category ID: ");
        int categoryId = Integer.parseInt(SCANNER.nextLine());
        Category categoryExist = CATEGORY_MANAGER.getCategoryById(categoryId);
        if (categoryExist == null) {
            System.out.println("Category with ID " + categoryId + " does not exist!");
            return;
        }
        System.out.println("Input new category name: ");
        String newCategoryName = SCANNER.nextLine();
        categoryExist.setName(newCategoryName);
        CATEGORY_MANAGER.editCategory(categoryExist);
        System.out.println("Category edited!");
    }

    private static void addCategory() {
        System.out.println("Input category name: ");
        String categoryName = SCANNER.nextLine();
        Category newCategory = new Category(categoryName);
        CATEGORY_MANAGER.add(newCategory);
        System.out.println("Category added!");
    }
}
