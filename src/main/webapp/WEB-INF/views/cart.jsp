<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<h2>Your cart</h2>
<c:choose>
    <c:when test="${empty cart}">
        <div class="panel">
            <p class="muted">Your cart is empty.</p>
            <a class="btn" href="${pageContext.request.contextPath}/catalog/products">Browse products</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="panel">
            <c:set var="total" value="0"/>
            <c:forEach var="item" items="${cart}" varStatus="st">
                <c:set var="total" value="${total + item.price}"/>
                <div class="cart-row">
                    <span>${item.name}</span>
                    <span class="price"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="$"/></span>
                    <form method="post" action="${pageContext.request.contextPath}/cart/remove">
                        <input type="hidden" name="index" value="${st.index}">
                        <button class="btn danger small" type="submit">Remove</button>
                    </form>
                </div>
            </c:forEach>
            <div class="cart-row total">
                <span>Total</span>
                <span class="price"><fmt:formatNumber value="${total}" type="currency" currencySymbol="$"/></span>
            </div>
            <a class="btn" href="${pageContext.request.contextPath}/checkout">Go to checkout</a>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="footer.jsp" />