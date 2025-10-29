/**
 * Shamir's Secret Sharing - Java Implementation
 * 
 * Student Assignment Project
 * Assignment: Hashira Placements Assignment - Catalog
 * Student: Satya
 * Date: October 29, 2025
 * Duration: 45 minutes
 * Language: Java
 * 
 * This program solves Shamir's Secret Sharing by:
 * 1. Reading JSON input with encoded roots in various bases
 * 2. Decoding values from different bases to decimal
 * 3. Using Lagrange interpolation to find the polynomial
 * 4. Extracting the secret (constant term) by evaluating at x=0
 */

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShamirSecretInput {
    
    static class Point {
        BigDecimal x;
        BigDecimal y;
        
        Point(long x, BigInteger y) {
            this.x = BigDecimal.valueOf(x);
            this.y = new BigDecimal(y);
        }
    }
    
    public static BigInteger decodeValue(String value, int base) {
        return new BigInteger(value, base);
    }
    
    public static BigDecimal lagrangeInterpolation(List<Point> points, BigDecimal x) {
        BigDecimal result = BigDecimal.ZERO;
        int n = points.size();
        
        for (int i = 0; i < n; i++) {
            BigDecimal term = points.get(i).y;
            
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    BigDecimal numerator = x.subtract(points.get(j).x);
                    BigDecimal denominator = points.get(i).x.subtract(points.get(j).x);
                    term = term.multiply(numerator).divide(denominator, 50, RoundingMode.HALF_UP);
                }
            }
            
            result = result.add(term);
        }
        
        return result;
    }
    
    public static String extractValue(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"?([^,\"\\}]+)\"?");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
    
    public static String processTestCase(String jsonContent) {
        StringBuilder output = new StringBuilder();
        
        // Extract n and k
        int n = Integer.parseInt(extractValue(jsonContent, "n"));
        int k = Integer.parseInt(extractValue(jsonContent, "k"));
        
        output.append("n (total roots): ").append(n).append("\n");
        output.append("k (minimum required roots): ").append(k).append("\n");
        output.append("Polynomial degree: ").append(k - 1).append("\n\n");
        
        List<Point> points = new ArrayList<>();
        
        // Extract each point
        for (int i = 1; i <= n; i++) {
            Pattern pointPattern = Pattern.compile(
                "\"" + i + "\"\\s*:\\s*\\{[^}]*\"base\"\\s*:\\s*\"([^\"]+)\"[^}]*\"value\"\\s*:\\s*\"([^\"]+)\"[^}]*\\}"
            );
            Matcher matcher = pointPattern.matcher(jsonContent);
            
            if (matcher.find()) {
                int base = Integer.parseInt(matcher.group(1));
                String value = matcher.group(2);
                
                BigInteger yValue = decodeValue(value, base);
                points.add(new Point(i, yValue));
                
                output.append("Point ").append(i).append(": base=").append(base)
                      .append(", encoded=").append(value)
                      .append(", decoded=").append(yValue).append("\n");
            }
        }
        
        List<Point> selectedPoints = points.subList(0, Math.min(k, points.size()));
        output.append("\nUsing first ").append(selectedPoints.size()).append(" points for interpolation\n");
        
        BigDecimal secret = lagrangeInterpolation(selectedPoints, BigDecimal.ZERO);
        output.append("\n*** SECRET (Constant term c): ")
              .append(secret.setScale(0, RoundingMode.HALF_UP))
              .append(" ***\n");
        
        return output.toString();
    }
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java ShamirSecretInput <json-file-path>");
            System.out.println("Example: java ShamirSecretInput testcase1.json");
            System.out.println("\nOr run with embedded test cases:");
            System.out.println("java ShamirSecretInput test");
            return;
        }
        
        try {
            String jsonContent;
            
            if (args[0].equals("test")) {
                // Use embedded test case
                jsonContent = "{\n" +
                    "    \"keys\": {\n" +
                    "        \"n\": 4,\n" +
                    "        \"k\": 3\n" +
                    "    },\n" +
                    "    \"1\": {\n" +
                    "        \"base\": \"10\",\n" +
                    "        \"value\": \"4\"\n" +
                    "    },\n" +
                    "    \"2\": {\n" +
                    "        \"base\": \"2\",\n" +
                    "        \"value\": \"111\"\n" +
                    "    },\n" +
                    "    \"3\": {\n" +
                    "        \"base\": \"10\",\n" +
                    "        \"value\": \"12\"\n" +
                    "    },\n" +
                    "    \"6\": {\n" +
                    "        \"base\": \"4\",\n" +
                    "        \"value\": \"213\"\n" +
                    "    }\n" +
                    "}";
                System.out.println("Using embedded test case");
            } else {
                jsonContent = new String(Files.readAllBytes(Paths.get(args[0])));
                System.out.println("Processing: " + args[0]);
            }
            
            System.out.println("=".repeat(50));
            String result = processTestCase(jsonContent);
            System.out.println(result);
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
