package Service;

import Product.*;
import java.io.*;
import java.sql.Date;
import java.util.*;

public class ProductLoader {

    public static List<Product> loadProductsFromCSV(String file) throws IOException {

        List<Product> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String row = br.readLine();                // header
            if (row == null) throw new IOException("CSV empty");

            while ((row = br.readLine()) != null) {

                String[] c = row.split(",", -1);

                String name        = c[0];
                double price       = Double.parseDouble(c[1]);
                int    qty         = Integer.parseInt(c[2]);
                IsExpire        ie = IsExpire.valueOf(c[3]);
                RequireShipping rs = RequireShipping.valueOf(c[4]);
                double weightKg     = Double.parseDouble(c[5]);

                Product p = new Product(name, price, qty, ie, rs, weightKg);

                if (c.length > 6 && !c[6].isEmpty())
                    p.setProductionDate(Date.valueOf(c[6]));
                if (c.length > 7 && !c[7].isEmpty())
                    p.setExpirationDays(Integer.parseInt(c[7]));

                list.add(p);
            }
        }
        return list;
    }
}
