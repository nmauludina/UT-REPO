public class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public double getOrderPrice() {
        return menuItem.harga * quantity;
    }

    // Getters and Setters
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
