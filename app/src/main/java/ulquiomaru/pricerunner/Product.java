package ulquiomaru.pricerunner;

import android.support.annotation.NonNull;

public class Product {

    private String name;
    private String barcode;
    private String price;
    private String source;
    private String timeValidated;
    private String origin;
    private String producerCode;
    private String productCode;

    Product() { }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    String getBarcode() {
        return barcode;
    }

    void setPrice(String price) {
        this.price = price;
    }

    String getPrice() {
        return price;
    }

    void setSource(String source) {
        this.source = source;
    }

    String getSource() {
        return source;
    }

    void setTimeValidated(String timeValidated) {
        this.timeValidated = timeValidated;
    }

    String getTimeValidated() {
        return timeValidated;
    }

    void setOrigin(String origin) {
        this.origin = origin;
    }

    String getOrigin() {
        return origin;
    }

    void setProducerCode(String producerCode) {
        this.producerCode = producerCode;
    }

    String getProducerCode() {
        return producerCode;
    }

    void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    String getProductCode() {
        return productCode;
    }

    @NonNull
    @Override
    public String toString() { // TODO
        return name;
    }
}
