<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<jsp:include page="header.jsp" />

<h2>Products</h2>
<div class="filters">
  <a class="chip ${empty selectedCategory ? 'active' : ''}" href="${pageContext.request.contextPath}/catalog/products">All</a>
  <a class="chip ${selectedCategory == 'electronics' ? 'active' : ''}" href="${pageContext.request.contextPath}/catalog/products?category=electronics">Electronics</a>
  <a class="chip ${selectedCategory == 'clothing' ? 'active' : ''}" href="${pageContext.request.contextPath}/catalog/products?category=clothing">Clothing</a>
  <a class="chip ${selectedCategory == 'books' ? 'active' : ''}" href="${pageContext.request.contextPath}/catalog/products?category=books">Books</a>
</div>

<div class="grid">
  <c:forEach var="product" items="${products}">
    <div class="product-card">
      <span class="tag">${product.category}</span>
      <h3><a href="${pageContext.request.contextPath}/catalog/product?id=${product.id}">${product.name}</a></h3>
      <p class="price"><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="$"/></p>
    </div>
  </c:forEach>
  <c:if test="${empty products}"><p class="muted">No products in this category.</p></c:if>
</div>

<jsp:include page="footer.jsp" />