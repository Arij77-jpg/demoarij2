<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="header.jsp" />

<h2>Checkout</h2>
<c:if test="${not empty confirmationName}">
    <div class="panel success">Thank you, <strong>${confirmationName}</strong>! Your order has been received.</div>
</c:if>
<c:if test="${empty confirmationName}">
    <div class="panel">
        <form method="post" action="${pageContext.request.contextPath}/checkout" class="form">
            <label>Name</label>
            <input type="text" name="name" required>
            <label>Email</label>
            <input type="email" name="email" required>
            <label>Address</label>
            <textarea name="address" rows="3" required></textarea>
            <button class="btn" type="submit">Place order</button>
        </form>
    </div>
</c:if>

<jsp:include page="footer.jsp" />