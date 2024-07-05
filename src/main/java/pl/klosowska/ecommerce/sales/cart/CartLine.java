package pl.klosowska.ecommerce.sales.cart;

public class CartLine {

    int qty;
    String productId;

    public CartLine(String productId, int qty) {
        this.productId = productId;
        this.qty = qty;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQty() {
        return qty;
    }
}
