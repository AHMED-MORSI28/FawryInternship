package Product;

import java.time.LocalDate;

public class Product implements Shippable {

  private String  name;
  private double  price;
  private int     quantity;

  private IsExpire        isExpire;
  private RequireShipping requireShipping;

  private double   weight;           // kilograms
  private LocalDate productionDate;  // optional
  private Integer   expirationDays;  // optional (days)

  public Product(String name,
                 double price,
                 int quantity,
                 IsExpire isExpire,
                 RequireShipping requireShipping,
                 double weightKg) {

    this.name              = name;
    this.price             = price;
    this.quantity          = quantity;
    this.isExpire          = isExpire;
    this.requireShipping   = requireShipping;

    if (requireShipping == RequireShipping.needsShipping) {
      this.weight = weightKg;
    } else {
      this.weight = 0.0;
    }
  }

  /* getters */
  @Override public String getName()          { return name; }
  public  double getPrice()                  { return price; }
  public  int    getQuantity()               { return quantity; }
  @Override public double getWeight()        { return weight; }
  public  IsExpire        getIsExpire()      { return isExpire; }
  public  RequireShipping getRequireShipping(){ return requireShipping; }
  public  boolean isShippable()              { return requireShipping == RequireShipping.needsShipping; }

  /* expiry helpers */
  public void setProductionDate(java.sql.Date sqlDate) {
    if (sqlDate != null) this.productionDate = sqlDate.toLocalDate();
  }
  public void setExpirationDays(Integer days) { this.expirationDays = days; }

  public boolean hasExpired() {
    if (isExpire == IsExpire.mayNotExpire || productionDate == null || expirationDays == null)
      return false;
    LocalDate expiryDate = productionDate.plusDays(expirationDays);
    return expiryDate.isBefore(LocalDate.now());
  }

  /* stock adjustment */
  public void setQuantity(int newQty) { this.quantity = newQty; }

  /* helper */
  public double totalPrice(int qty) { return price * qty; }
}
