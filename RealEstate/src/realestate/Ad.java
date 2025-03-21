
package realestate;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class Ad {
    private int id;
    private String description;
    private int rooms;
    private int area;
    private int floors;
    private Category category;
    private Seller seller;
    private boolean freeOfCharge;
    private String imageUrl;
    private double latitude;
    private double longitude;
    private LocalDate createAt;
    
    public Ad(int id, String description, int rooms, int area, int floors,
              Category category, Seller seller, boolean freeOfCharge,
              String imageUrl, double latitude, double longitude, LocalDate createAt) {
        this.id = id;
        this.description = description;
        this.rooms = rooms;
        this.area = area;
        this.floors = floors;
        this.category = category;
        this.seller = seller;
        this.freeOfCharge = freeOfCharge;
        this.imageUrl = imageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createAt = createAt;
    }
    
public static List<Ad> loadFromCsv(String filename) {
    List<Ad> ads = new ArrayList<>();
    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(filename))) {
        String header = br.readLine();
        if (header == null) {
            System.out.println("CSV file is empty.");
            return ads;
        }
        
        String delimiter = ";";
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(delimiter, -1);
            if (parts.length < 14) {
                System.out.println("Skipping line (insufficient columns): " + line);
                continue;
            }
            try {
                int id = Integer.parseInt(parts[0].trim());
                int rooms = Integer.parseInt(parts[1].trim());
                
                String[] latLongParts = parts[2].trim().split(",", -1);
                double latitude = Double.parseDouble(latLongParts[0].trim());
                double longitude = Double.parseDouble(latLongParts[1].trim());
                
                int floors = Integer.parseInt(parts[3].trim());
                int area = Integer.parseInt(parts[4].trim());
                String description = parts[5].trim();
                
                boolean freeOfCharge = "1".equals(parts[6].trim());
                
                String imageUrl = parts[7].trim();
                java.time.LocalDate createAt = java.time.LocalDate.parse(parts[8].trim(), formatter);
                
                int sellerId = Integer.parseInt(parts[9].trim());
                String sellerName = parts[10].trim();
                String sellerPhone = parts[11].trim();
                Seller seller = new Seller(sellerId, sellerName, sellerPhone);
                
                int catId = Integer.parseInt(parts[12].trim());
                String catName = parts[13].trim();
                Category category = new Category(catId, catName);
                
                Ad ad = new Ad(id, description, rooms, area, floors, category, seller, 
                              freeOfCharge, imageUrl, latitude, longitude, createAt);
                ads.add(ad);
            } catch (Exception e) {
                System.out.println("Error parsing line: " + line);
                e.printStackTrace();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ads;
}



    
    public double distanceTo(double lat, double lon) {
        double dLat = this.latitude - lat;
        double dLon = this.longitude - lon;
        return Math.sqrt(dLat * dLat + dLon * dLon);
    }
    
    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getRooms() { return rooms; }
    public int getArea() { return area; }
    public int getFloors() { return floors; }
    public Category getCategory() { return category; }
    public Seller getSeller() { return seller; }
    public boolean isFreeOfCharge() { return freeOfCharge; }
    public String getImageUrl() { return imageUrl; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public LocalDate getCreateAt() { return createAt; }
    
    @Override
    public String toString() {
        return "Ad{id=" + id + ", description='" + description + "', area=" + area + 
               ", floor=" + floors + ", freeOfCharge=" + freeOfCharge + "}";
    }
}