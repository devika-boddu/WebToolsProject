<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<style>
* {box-sizing: border-box}
body {font-family: "Lato", sans-serif;}

/* Style the tab */
.tab {
  float: left;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
  width: 20%;
  height: 900px;
}

/* Style the buttons inside the tab */
.tab button {
  display: block;
  background-color: inherit;
  color: black;
  padding: 22px 16px;
  width: 100%;
  border: none;
  outline: none;
  text-align: left;
  cursor: pointer;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current "tab button" class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  float: left;
  padding: 0px 12px;
  border: 1px solid #ccc;
  width: 80%;
  border-left: none;
  height: 900px;
  overflow: hidden;
}
.Card-Parent{
    display: flex;
    flex-wrap: wrap;
    margin: 1.5%;
    /* flex-direction: row; */
    width: 100%;
    padding: 25px;
    justify-content: space-between;
}
.Card-Parent > div {
     flex: 33%; 
    margin: 0.1%;
    margin-left: 0.2%;
    padding: 20px;
}

.Card {
    width: 175%;
    /* flex-basis : 50% */
    margin: 2%;
    box-shadow: 0 0 25px #0000004d;
    /* border-radius: 15px; */
    list-style: none;
}
.in{
        border-top-style: hidden;
        border-right-style: hidden;
        border-left-style: hidden;
        border-bottom-style: hidden;
        background-color: #ffffff;
        color: #ffffff;
      
}
.in:focus {
    outline: none;
  }

 
  .cart {
    padding: 5px;
    display: inline-block;
    padding: 1rem 1rem;
    width: 5%;
  }
</style>
</head>
<body>
        <div class="tab">
          <button class="tablinks" onclick="openPanel(event, 'Home')" >Home</button>
          <button class="tablinks" onclick="openPanel(event, 'Products')" id="defaultOpen">Products</button>
          <button class="tablinks" onclick="openPanel(event, 'Cart')">Cart</button>
        </div>
        
        <div id="Home" class="tabcontent">
            <h2>Hi
                <c:out value="${sessionScope.currentUser.name}" />
            </h2>
            <h4>Welcome to Nuemart!----</h4>
        </div>

        
    <div id="Products" class="tabcontent">
        <p>Products Page</p>
        <form method="post" name="products" action="products.htm">
            <div class="container py-5">
                <div class="row">
                    <div>
                        <form method = "post" action = "products.htm">
                            <label>Search by Package Name: </label>
                            <input type = "text" name = "textEntered"/>
                            <input type = "submit" class="btn btn-warning search" value = "Search" name = "userSelectedOption"/>
                            <input type = "submit" class="btn btn-warning sort" value = "Sort" name = "userSelectedOption"/>
                            <input type = "submit" value = "1" name = "userSelectedOption"/>
                            <input type = "submit" value = "2" name = "userSelectedOption"/>
                            <input type = "submit" value = "3" name = "userSelectedOption"/>
                            <div class = "Card-Parent searchedItems">
                                <!-- <h3>Hi</h3>
                                <c:out value="${sessionScope.optionSelected}" /> -->
                                <c:set var="optionSelected" scope="session" value="${sessionScope.optionSelected}"/>  
                                <c:if test="${optionSelected == '1'}"> 
                                    <c:forEach var="attribute" items="${sessionScope.searchedItems}">
                                        <div class="Card">

                                            <img src="${attribute.image}" alt="x" width="250" height="175">
                                            <li>${attribute.packageId}</li>
                                            <li>${attribute.packageName}</li>
                                            <li>${attribute.packagePrice}</li>
                                            <li>${attribute.packageDescription}</li>
                                            <!-- <input  type="submit" value="${attribute.packageId}" name="userSelectedOption" /> -->
                                            
                                            <input  type="hidden" value="${attribute.packageId}" name="pId" />
                                            <!-- <input  type="submit" value="View Cart" name="userSelectedOption" /> -->
                                            <div class="buttons">
                                                <div class="cart" >
                                                    <input class="in" type="submit" id="addToCart_${attribute.packageId}" value="" name="userSelectedOption" />
                                                    <span class="icon"><i class="fa fa-shopping-cart"></i></span>
                                                </div>
                                                <div class="cart" >
                                                    <input class="in" type="submit" id="addToWishlist_${attribute.packageId}" value="" name="userSelectedOption" />
                                                    <span class="icon"><i class="fa fa-heart"></i></span>
                                                </div>
                                                <div class="cart">
                                                    <input class="in" type="submit" id="view_${attribute.packageId}" value="" name="userSelectedOption" />
                                                    <span class="icon"><i class="fa fa-eye"></i></span>
                                                    </div>
                                            </div>
                                            <script>
                                                function concatenateStrings(text1, text2, inputId) {
                                                    let result = text1 + ' ' + text2; // Concatenate with a space in between
                                                    document.getElementById(inputId).setAttribute("value", result);
                                                }
                                                
                                                //concatenateStrings(`<i class='fa fa-shopping-cart'></i> Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");

                                                concatenateStrings("Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");
                                                concatenateStrings("Add To Wishlist", "${attribute.packageId}", "addToWishlist_${attribute.packageId}");
                                                concatenateStrings("View", "${attribute.packageId}", "view_${attribute.packageId}");

                                            </script>
                                        </div>
                                        
                                    </c:forEach>
                                </c:if>
                                
                            </div>
                        </form>
                    </div>
                    <div>
                        <form method = "post" action = "products.htm">
                            <label>Sort by Package Name: </label>
                            <!-- <input type = "submit" class="btn btn-warning sort" value = "Sort" name = "userSelectedOption"/> -->
                            <div class = "Card-Parent sortedItems">
                                <c:set var="optionSelected" scope="session" value="${sessionScope.optionSelected}"/>  
                                <c:if test="${optionSelected == '2'}"> 
                                    <c:forEach var="attribute" items="${sessionScope.sortedItems}">
                                    <div class="Card">
                                        <img src="${attribute.image}" alt="x" width="300" height="250">
                                        <li>${attribute.packageId}</li>
                                        <li>${attribute.packageName}</li>
                                        <li>${attribute.packagePrice}</li>
                                        <li>${attribute.packageDescription}</li>
                                        <!-- <input  type="submit" value="${attribute.packageId}" name="userSelectedOption" /> -->
                                        
                                        <input  type="hidden" value="${attribute.packageId}" name="pId" />
                                        <!-- <input  type="submit" value="View Cart" name="userSelectedOption" /> -->
                                        <div class="buttons">
                                            <div class="cart" >
                                                <input class="in" type="submit" id="addToCart_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-shopping-cart"></i></span>
                                            </div>
                                            <div class="cart" >
                                                <input class="in" type="submit" id="addToWishlist_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-heart"></i></span>
                                            </div>
                                            <div class="cart">
                                                <input class="in" type="submit" id="view_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-eye"></i></span>
                                                </div>
                                        </div>
                                        <script>
                                            function concatenateStrings(text1, text2, inputId) {
                                                let result = text1 + ' ' + text2; // Concatenate with a space in between
                                                document.getElementById(inputId).setAttribute("value", result);
                                            }
                                            
                                            //concatenateStrings(`<i class='fa fa-shopping-cart'></i> Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");

                                            concatenateStrings("Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");
                                            concatenateStrings("Add To Wishlist", "${attribute.packageId}", "addToWishlist_${attribute.packageId}");
                                            concatenateStrings("View", "${attribute.packageId}", "view_${attribute.packageId}");

                                        </script>
                                    </div>
                                    
                                </c:forEach>
                                </c:if>
                                
                            </div>
                        </form>
                    </div>
                    <div>
                        <form method = "post" action = "products.htm">
                            <label>Page Numbers</label>
                            <input type = "submit" value = "1" name = "userSelectedOption"/>
                            <input type = "submit" value = "2" name = "userSelectedOption"/>
                            <input type = "submit" value = "3" name = "userSelectedOption"/>
                        </form>
                    </div>
                        <!-- <thead>
                            <tr>
                                <th>User Name : </th>
                                <th>Assessment Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead> -->
                    <div class="Card-Parent products">
                        
                            <!-- <ul> -->
                                <!-- <c:set target="${sessionScope}" property="searchedItems" value='${ "${sessionScope.optionSelected}" == "1" ? searchedItems : products}' /> -->
                                <c:set var="optionSelected" scope="session" value="${sessionScope.optionSelected}"/>  
                                <c:if test="${optionSelected == '0' }"> 
                                    <c:forEach var="attribute" items="${sessionScope.initialProducts}">
                                    <div class="Card">
                                        <img src="${attribute.image}" alt="x" width="300" height="250">
                                        <li>${attribute.packageId}</li>
                                        <li>${attribute.packageName}</li>
                                        <li>${attribute.packagePrice}</li>
                                        <li>${attribute.packageDescription}</li>
                                        <!-- <input  type="submit" value="${attribute.packageId}" name="userSelectedOption" /> -->
                                        
                                        <input  type="hidden" value="${attribute.packageId}" name="pId" />
                                        <!-- <input  type="submit" value="View Cart" name="userSelectedOption" /> -->
                                        <div class="buttons">
                                            <div class="cart" >
                                                <input class="in" type="submit" id="addToCart_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-shopping-cart"></i></span>
                                            </div>
                                            <div class="cart" >
                                                <input class="in" type="submit" id="addToWishlist_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-heart"></i></span>
                                            </div>
                                            <form method="get" action="view.htm">
                                                <div class="cart">
                                                    <input class="in" type="submit" id="view_${attribute.packageId}" value="" name="userSelectedOption" />
                                                    <span class="icon"><i class="fa fa-eye"></i></span>
                                                </div>
                                            </form>
                                            
                                        </div>
                                        <script>
                                            function concatenateStrings(text1, text2, inputId) {
                                                let result = text1 + ' ' + text2; // Concatenate with a space in between
                                                document.getElementById(inputId).setAttribute("value", result);
                                            }
                                            
                                            //concatenateStrings(`<i class='fa fa-shopping-cart'></i> Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");

                                            concatenateStrings("Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");
                                            concatenateStrings("Add To Wishlist", "${attribute.packageId}", "addToWishlist_${attribute.packageId}");
                                            concatenateStrings("View", "${attribute.packageId}", "view_${attribute.packageId}");

                                        </script>
                                    </div>
                                    
                                </c:forEach>
                                </c:if>
                                <c:if test="${optionSelected == '3' }"> 
                                    <c:forEach var="attribute" items="${sessionScope.paginationResults}">
                                    <div class="Card">
                                        <img src="${attribute.image}" alt="x" width="300" height="250">
                                        <li>${attribute.packageId}</li>
                                        <li>${attribute.packageName}</li>
                                        <li>${attribute.packagePrice}</li>
                                        <li>${attribute.packageDescription}</li>
                                        <!-- <input  type="submit" value="${attribute.packageId}" name="userSelectedOption" /> -->
                                        
                                        <input  type="hidden" value="${attribute.packageId}" name="pId" />
                                        <!-- <input  type="submit" value="View Cart" name="userSelectedOption" /> -->
                                        <div class="buttons">
                                            <div class="cart" >
                                                <input class="in" type="submit" id="addToCart_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-shopping-cart"></i></span>
                                            </div>
                                            <div class="cart" >
                                                <input class="in" type="submit" id="addToWishlist_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-heart"></i></span>
                                            </div>
                                            <div class="cart">
                                                <input class="in" type="submit" id="view_${attribute.packageId}" value="" name="userSelectedOption" />
                                                <span class="icon"><i class="fa fa-eye"></i></span>
                                            </div>
                                        </div>
                                        <script>
                                            function concatenateStrings(text1, text2, inputId) {
                                                let result = text1 + ' ' + text2; // Concatenate with a space in between
                                                document.getElementById(inputId).setAttribute("value", result);
                                            }
                                            
                                            //concatenateStrings(`<i class='fa fa-shopping-cart'></i> Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");

                                            concatenateStrings("Add To Cart", "${attribute.packageId}", "addToCart_${attribute.packageId}");
                                            concatenateStrings("Add To Wishlist", "${attribute.packageId}", "addToWishlist_${attribute.packageId}");
                                            concatenateStrings("View", "${attribute.packageId}", "view_${attribute.packageId}");

                                        </script>
                                    </div>
                                    
                                </c:forEach>
                                </c:if>
                                <!-- </ul> -->
                    </div>  
                </div>
            </div>
        </form>
    </div>
    <div id="Cart" class="tabcontent">
        <p>Cart Tab----</p>
        <form method="post" name="products" action="products.htm">
            <div class="container py-5">
                <div class="Card-Parent">
                    <c:forEach var="attribute" items="${sessionScope.travelPackagesCart}">

                        <div class="Card" onload="myFunction()">
                            <img src="${attribute.image}" alt="x" width="300" height="250">
                            <li>${attribute.packageId}</li>
                            <li>${attribute.packageName}</li>
                            <li>${attribute.packagePrice}</li>
                            <li>${attribute.packageDescription}</li>
                            <label for="qty">Quantity</label><span id="myText"></span>
                            <select name="qty" id="qty">
                                <option name="qtySelected" value="1">1</option>
                                <option name="qtySelected" value="2">2</option>
                                <option name="qtySelected" value="3">3</option>
                                <option name="qtySelected" value="4">4</option>
                            </select>
                        </div>
                        <script>
                            function myFunction(){
                            const qty = document.querySelector('#qty');
                            let selectedValue = qty.value;
                            
                            qty.addEventListener('change', () => {
                                selectedValue = qty.value;
                                document.getElementById("myText").innerHTML = selectedValue;
                                console.log('Selected value:', selectedValue);
                            });}
                            </script>
                    </c:forEach>
                </div>

                <input type="submit" id="updateQuantity" value="Total Price" name="userSelectedOption" />
                Cost : <c:out value="${sessionScope.aTotalCost}"/> <br/>
                <input type="submit" id="processOrder" value="Pay" name="userSelectedOption" />
                
            </div>
        </form>
        <form method = "get" name="payments" action = "payment">
            <input type="submit" id="processOrder" value="Payment" name="userSelectedOption" />
        </form>  
    </div>
        
        <script>
        function openPanel(evt, cityName) {
          var i, tabcontent, tablinks;
          tabcontent = document.getElementsByClassName("tabcontent");
          for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
          }
          tablinks = document.getElementsByClassName("tablinks");
          for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
          }
          document.getElementById(cityName).style.display = "block";
          evt.currentTarget.className += " active";
        }
        
        // Get the element with id="defaultOpen" and click on it
        document.getElementById("defaultOpen").click();
        </script>


   
</body>
</html> 
