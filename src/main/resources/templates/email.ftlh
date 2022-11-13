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
        <img src="https://i.ibb.co/WymynFj/Screenshot-2022-11-13-150654.png" style="width:100%"></img>
    </div>
</html> 