package coop.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (Item item : items) {
            sum = sum.add(item.getAmount());
        }
        return sum;
    }

    public static class Item {
        private final Product product;
        private final BigDecimal quantity;

        public Item(Product product, BigDecimal quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public BigDecimal getQuantity() {
            return quantity;
        }

        public BigDecimal getAmount() {
            return product.getPrice().multiply(quantity);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "product='" + product + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
