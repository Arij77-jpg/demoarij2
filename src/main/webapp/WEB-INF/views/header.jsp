<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Arij Shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/catalog.css">
</head>
<body>
<nav class="navbar">
    <a class="brand" href="${pageContext.request.contextPath}/catalog/products">Jakarta<span>Shop</span></a>
    <div class="nav-links">
        <a href="${pageContext.request.contextPath}/catalog/products">Products</a>
        <a href="${pageContext.request.contextPath}/cart">
            Cart <span class="count">${not empty cartService ? cartService.itemCount : (empty sessionScope.cart ? 0 : sessionScope.cart.size())}</span>
        </a>
        <a href="${pageContext.request.contextPath}/hello" style="opacity: 0.8; font-size: 0.85rem;">Lab 1 (/hello)</a>
    </div>
</nav>
<main class="container">
