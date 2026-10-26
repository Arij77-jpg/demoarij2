<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<a class="back" href="${pageContext.request.contextPath}/catalog/products">&larr; Back to products</a>
<div class="panel">
    <span class="tag">${product.category}</span>
    <h2>${product.name}</h2>
    <p class="price big"><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$"/></p>
    <form method="post" action="${pageContext.request.contextPath}/cart/add">
        <input type="hidden" name="productId" value="${product.id}">
        <button class="btn" type="submit">Add to cart</button>
    </form>
</div>

<jsp:include page="footer.jsp" />