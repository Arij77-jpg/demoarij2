<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<h2>Checkout</h2>

<c:if test="${not empty errorMessage}">
    <div class="panel" style="background: #fef2f2; border: 1px solid #f87171; color: #b91c1c; margin-bottom: 1rem;">
        <strong>Error:</strong> ${errorMessage}
        <div style="margin-top: 0.5rem;">
            <a class="btn" href="${pageContext.request.contextPath}/catalog/products">Add products to cart</a>
        </div>
    </div>
</c:if>

<c:if test="${not empty confirmationName}">
    <div class="panel success" style="background: #f0fdf4; border: 1px solid #86efac; color: #166534;">
        <h3>Order Placed Successfully!</h3>
        <p>Thank you, <strong>${confirmationName}</strong>! Your order has been received and confirmed.</p>
        <p style="margin-top: 1rem;">
            <a class="btn" href="${pageContext.request.contextPath}/catalog/products">Continue Shopping</a>
        </p>
    </div>
</c:if>

<c:if test="${empty confirmationName}">
    <div class="panel">
        <c:if test="${not empty cart}">
            <div style="margin-bottom: 1.5rem; padding-bottom: 1rem; border-bottom: 1px solid #e2e8f0;">
                <h4>Order Summary (${cart.size()} items)</h4>
                <p>Total: <strong><fmt:formatNumber value="${discountedTotal != null ? discountedTotal : subtotal}" type="currency" currencySymbol="$"/></strong></p>
            </div>
        </c:if>
        <form method="post" action="${pageContext.request.contextPath}/checkout" class="form">
            <label>Name</label>
            <input type="text" name="name" required placeholder="Your full name">
            <label>Email</label>
            <input type="email" name="email" required placeholder="your.email@example.com">
            <label>Address</label>
            <textarea name="address" rows="3" required placeholder="Shipping address"></textarea>
            <button class="btn" type="submit">Place order</button>
        </form>
    </div>
</c:if>

<jsp:include page="footer.jsp" />
