package pl.klosowska.ecommerce.sales.cart;

public class CartLine {

    private int qty;
    private String productId;

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
