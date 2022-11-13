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
        <table border="1" cellspacing="0" cellpadding="1" style="width:100%">
            <tr class="tableHeader">
                <td style="color:#000000"><b>Sr No.</b></td>
                <td style="color:#000000"><b>Products Details</b></td>
                <td style="color:#000000"><b>HSN Code</b></td>
                <td style="color:#000000"><b>GST</b></td>
                <td style="color:#000000"><b>QTY</b></td>
                <td style="color:#000000"><b>Rate</b></td>
                <td style="color:#000000"><b>Amount</b></td>
            </tr>
            <#foreach product in user.products>                  
                <tr class="tableBody" style="width:100%">
                    <td style="color:#000000">#</td>
                    <td style="color:#000000">${product.name}<br/>Serial Nos:${product.serialNos}<br/>Sales Person:${product.salesPerson}<br/>Delivery Type:${product.deliveryType}<br/>Del Date:${product.deliveryDate}<br/> Transporter Name:${product.transporterName}<br/>Mobile Number:${product.mobileNumber}</td>
                    <td style="color:#000000">${product.hsnCode}</td>
                    <td style="color:#000000">${product.gst}</td>
                    <td style="color:#000000">${product.quantity}</td>
                    <td style="color:#000000">${product.rate}</td>
                    <td style="color:#000000">${product.amount}</td>
                </tr>
            </#foreach>                             
        </table>
        <img src="https://i.ibb.co/WymynFj/Screenshot-2022-11-13-150654.png" style="width:100%"></img>
    </div>
</html> 