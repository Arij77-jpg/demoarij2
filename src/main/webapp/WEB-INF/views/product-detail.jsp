<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%--@elvariable id="product" type="com.hellohorizon.demoarij2.model.ProductDTO"--%>
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

<!-- Chapter 4 Activity 1: Customer Reviews -->
<div class="panel" style="margin-top: 1.5rem;">
    <h3>Customer Reviews (${empty product.reviews ? 0 : product.reviews.size()})</h3>
    <c:choose>
        <c:when test="${empty product.reviews}">
            <p class="muted" style="margin-top: 0.5rem;">No reviews yet for this product.</p>
        </c:when>
        <c:otherwise>
            <div style="margin-top: 1rem; display: flex; flex-direction: column; gap: 0.75rem;">
                <c:forEach var="rev" items="${product.reviews}">
                    <div style="background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 0.75rem 1rem;">
                        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 0.25rem;">
                            <strong>${rev.author}</strong>
                            <span style="color: #f59e0b; font-weight: bold;">
                                <c:forEach begin="1" end="${rev.rating}">&#9733;</c:forEach> (${rev.rating}/5)
                            </span>
                        </div>
                        <p style="margin: 0; color: #475569;">${rev.comment}</p>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="footer.jsp" />
