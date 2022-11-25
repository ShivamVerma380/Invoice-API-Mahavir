package mahavir.invoice.model;


import org.springframework.stereotype.Component;

@Component
public class Product {
    
    private String name;
    private String serialNos;
    private String salesPerson;
    private String deliveryType;
    private String deliveryDate;
    private String transporterName;
    private String mobileNumber;
    private String hsnCode;
    private String gst;
    private String quantity;
    private String rate;
    private String amount;

    

    

    public Product() {
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSerialNos() {
        return serialNos;
    }
    public void setSerialNos(String serialNos) {
        this.serialNos = serialNos;
    }
    public String getSalesPerson() {
        return salesPerson;
    }
    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }
    public String getDeliveryType() {
        return deliveryType;
    }
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String getTransporterName() {
        return transporterName;
    }
    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getHsnCode() {
        return hsnCode;
    }
    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }
    public String getGst() {
        return gst;
    }
    public void setGst(String gst) {
        this.gst = gst;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    

    
}