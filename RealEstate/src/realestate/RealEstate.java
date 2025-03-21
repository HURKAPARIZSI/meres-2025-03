
package realestate;

import java.text.DecimalFormat;
import java.util.List;

public class RealEstate {

    public static void main(String[] args) {
        List<Ad> ads = Ad.loadFromCsv("realestates.csv");
        
        double totalArea = 0;
        int count = 0;
        for (Ad ad : ads) {
            if (ad.getFloors() == 0) {
                totalArea += ad.getArea();
                count++;
            }
        }
        double avgArea = (count > 0) ? totalArea / count : 0;
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("6. feladat:");
        System.out.println("A földszinti ingatlanok átlagos alapterülete: " + df.format(avgArea));
        
        if (!ads.isEmpty()) {
            double distance = ads.get(0).distanceTo(47.4164220114023, 19.066342425796986);
            System.out.println("7. feladat:");
            System.out.println("Az első hirdetés távolsága a megadott koordinátától: " + df.format(distance));
        }
        
        double targetLat = 47.4164220114023;
        double targetLon = 19.066342425796986;
        Ad closestAd = null;
        double minDistance = Double.MAX_VALUE;
        for (Ad ad : ads) {
            if (ad.isFreeOfCharge()) {
                double dist = ad.distanceTo(targetLat, targetLon);
                if (dist < minDistance) {
                    minDistance = dist;
                    closestAd = ad;
                }
            }
        }
        System.out.println("8. feladat:");
        if (closestAd != null) {
            System.out.println("A legközelebbi tehermentes ingatlan adatai:");
            System.out.println(closestAd);
        } else {
            System.out.println("Nincs tehermentes ingatlan az adatforrásban.");
        }
    }
}

