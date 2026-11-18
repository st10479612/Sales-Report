/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.salsereport;

import java.util.Scanner;

// Interface defining the required operations
interface IProducts {
    int calculateTotalSales(int[][] sales);
    double calculateAverageSales(int[][] sales);
    String findMostPopularProduct(String[] productNames, int[][] sales);
}

// Product class implementing the IProducts interface
class Product implements IProducts {
    
    // Method to calculate the total number of products sold across all months and products
    @Override
    public int calculateTotalSales(int[][] sales) {
        int totalSales = 0;
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                totalSales += sales[i][j];
            }
        }
        return totalSales;
    }

    // Method to calculate the average number of products sold per product
    @Override
    public double calculateAverageSales(int[][] sales) {
        int totalSales = calculateTotalSales(sales);
        int totalProducts = sales.length;
        return (double) totalSales / totalProducts;
    }

    // Method to determine the most popular product based on the highest total sales
    @Override
    public String findMostPopularProduct(String[] productNames, int[][] sales) {
        int maxSales = -1;
        int bestProductIndex = 0;

        for (int i = 0; i < sales.length; i++) {
            int currentProductTotal = 0;
            for (int j = 0; j < sales[i].length; j++) {
                currentProductTotal += sales[i][j];
            }
            if (currentProductTotal > maxSales) {
                maxSales = currentProductTotal;
                bestProductIndex = i;
            }
        }

        return productNames[bestProductIndex] + " (" + maxSales + ")";
    }
}

// Main execution class named SalesReport
public class SalesReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Single-dimensional array for the three top-performing mattress products
        String[] productNames = {
            "Cosy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };

        // Two-dimensional array to store sales: 3 products (rows) x 3 months (columns)
        int[][] sales = new int[3][3];

        // Prompt user to enter the number of sales for each product across 3 months
        for (int i = 0; i < productNames.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter number of sales for month " + (j + 1) + " for " + productNames[i] + ": ");
                sales[i][j] = scanner.nextInt();
            }
        }

        // Instantiate the Product class
        Product productReport = new Product();

        // Calculate metrics using interface methods
        int totalProductSales = productReport.calculateTotalSales(sales);
        double averageProductSales = productReport.calculateAverageSales(sales);
        String mostPopularProduct = productReport.findMostPopularProduct(productNames, sales);

        // Display the final formatted sales report matching the sample output
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("************************************************==");
        System.out.println("TOTAL PRODUCT SALES: " + totalProductSales);
        System.out.println("AVERAGE PRODUCT SALES: " + (int) averageProductSales);
        System.out.println("MOST POPULAR PRODUCT: " + mostPopularProduct);

        scanner.close();
    }
}