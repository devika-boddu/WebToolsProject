This is the TravelPackaage

<!-- <h1>Package Details</h1>
    ID :  ${sessionScope.product.packageId}<br/>
    Name :  ${sessionScope.product.packageName}<br/>
    Description :  ${sessionScope.product.packageDescription}<br/>
    Cost :  ${sessionScope.product.packagePrice}<br/> -->


    <!DOCTYPE html>
    <html>
    <head>
        <title>Loop Example</title>
    </head>
    <body>
        <h1>Loop Example</h1>
        <ul>
            <c:forEach var="attribute" items="${sessionScope.product.values()}">
              <li>${attribute}</li>
            </c:forEach>
          </ul>
    </body>
    </html>
    