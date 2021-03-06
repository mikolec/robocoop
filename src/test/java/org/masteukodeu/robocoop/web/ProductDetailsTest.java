package org.masteukodeu.robocoop.web;

import org.masteukodeu.robocoop.model.Product;
import org.masteukodeu.robocoop.model.ProductDetails;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductDetailsTest {

    @Test
    public void testComplete() {
        ProductDetails item = getProductDetails(10, new BigDecimal(10));

        String status = item.getStatus();

        assertThat(status).isEqualTo("complete");
    }

    @Test
    public void testEmpty() {
        ProductDetails item = getProductDetails(10, new BigDecimal(0));

        String status = item.getStatus();

        assertThat(status).isEqualTo("empty");
    }

    @Test
    public void testIncomplete() {
        ProductDetails item = getProductDetails(10, new BigDecimal(5));

        String status = item.getStatus();

        assertThat(status).isEqualTo("incomplete");
    }

    @Test
    public void testCompleteIncomplete() {
        ProductDetails item = getProductDetails(10, new BigDecimal(15));

        String status = item.getStatus();

        assertThat(status).isEqualTo("complete-incomplete");
    }

    private ProductDetails getProductDetails(int transactionalQuantity, BigDecimal orderedQuantity) {
        return new ProductDetails(new Product("", "", null, "", "", transactionalQuantity), orderedQuantity);
    }
}