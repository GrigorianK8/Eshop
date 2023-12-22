package org.example.manager;

import org.example.db.DBConnectionProvider;
import org.example.model.Category;
import org.example.model.Product;

import java.sql.*;

public class ProductManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void addProduct(Product product) {
        String query = "INSERT INTO product (name, description, price, quantity, category_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getCategoryId());
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                int id = generatedKey.getInt(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProduct(Product product) {
        if (getProductById(product.getId()) == null) {
            System.out.println("Product with ID " + product.getId() + " does not exist!");
            return;
        }
        String query = "UPDATE product SET name = ?, description = ?, price = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductById(int id) {
        if (getProductById(id) == null) {
            System.out.println("Product with ID " + id + " does not exist");
            return;
        }
        String sql = "DELETE FROM product WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int productId) {
        Product product = null;
        String query = "SELECT * FROM product WHERE id = " + productId;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("category_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void printAllProducts() {
        String sql = "SELECT * FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("All products: ");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("Product ID: " + id);
                System.out.println("Product Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printSumOfProductPrices() {
        String sql = "SELECT SUM(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                double sum = resultSet.getDouble(1);
                System.out.println("Sum of product prices: " + sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMaxOfPriceProduct() {
        String sql = "SELECT MAX(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                double max = resultSet.getDouble(1);
                System.out.println("Max product price: " + max);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printMinOfPriceProduct() {
        String sql = "SELECT MIN(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                double min = resultSet.getDouble(1);
                System.out.println("Min product price: " + min);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAvgOfProductPrices() {
        String sql = "SELECT AVG(price) FROM product";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                double avg = resultSet.getDouble(1);
                System.out.println("Average product price: " + avg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
