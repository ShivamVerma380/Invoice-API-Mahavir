<!doctype html>
<html lang="en">
    <div>
        <img src="https://i.ibb.co/XLQJ6vd/Screenshot-2022-11-13-135108.png" style="width:100%"></img>     
        <table border="1" cellspacing="0" cellpadding="1" style="width:100%">
            <tr class="tableHeader">
                <td style="color:#000000"><b>Invoice Number</b></td>
                <td style="color:#000000"><b>Order Date</b></td>
                <td style="color:#000000"><b>Order Number</b></td>
            </tr>               
            <tr class="tableBody">
                <td style="color:#000000">${user.invoiceId}</td>
                <td style="color:#000000">${user.orderDate}</td>
                <td style="color:#000000">${user.orderNumber}</td>
            </tr>                        
        </table> 
        <table border="1" cellspacing="0" cellpadding="1" style="width:100%">
            <tr class="tableHeader">
                <td style="color:#000000"><b>Bill To:</b></td>
                <td style="color:#000000"><b>Ship To:</b></td>
            </tr>               
            <tr class="tableBody">
                <td style="color:#000000"><b>${user.name}</b><br/>${user.billingAddress}<br/>${user.billingCity}-${user.billingPincode}<br/>${user.billingState}</td>
                <td style="color:#000000"><b>${user.name}</b><br/>${user.shippingAddress}<br/>${user.shippingCity}-${user.shippingPincode}<br/>${user.shippingState}</td>
            </tr>                        
        </table> 
        <br/>
        <table border="1" cellspacing="0" cellpadding="1" style="width:100%">
            <tr class="tableHeader">
                <td>Sr No.</td>
                <td>Products Details</td>
                <td>HSN Code</td>
                <td>GST</td>
                <td>QTY</td>
                <td>Rate</td>
                <td>Amount</td>
            </tr>
            <#foreach product in user.products>                  
                <tr class="tableBody" style="width:100%">
                    <td>1</td>
                    <td>${product.name}<br/>Serial Nos:${product.serialNos}<br/>Sales Person:${product.salesPerson}<br/>Delivery Type:${product.deliveryType}<br/>Del Date:${product.deliveryDate}<br/> Transporter Name:${product.transporterName}<br/>Mobile Number:${product.mobileNumber}</td>
                    <td>${product.hsnCode}</td>
                    <td>${product.gst}</td>
                    <td>${product.quantity}</td>
                    <td>${product.rate}</td>
                    <td>${product.amount}</td>
                </tr>
            </#foreach>                             
        </table>
        <br/>
        <img src="https://i.ibb.co/WymynFj/Screenshot-2022-11-13-150654.png" style="width:100%"></img>
    </div>
</html> 