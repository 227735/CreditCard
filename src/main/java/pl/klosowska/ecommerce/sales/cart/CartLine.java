package pl.klosowska.ecommerce.sales.cart;

public class CartLine {

    private Integer qty;
    private String productId;

    public CartLine(String productId, Integer qty) {
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
