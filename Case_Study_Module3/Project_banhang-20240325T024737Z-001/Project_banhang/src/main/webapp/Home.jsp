<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page| Chaien Model Shoes Store Manage Product | Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--begin of menu-->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home">Chaien Model Shoes Store | Home Page</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="Login.jsp">Manager Account</a>
                        </li>
                        <li class="nav-item">
                            <p class="nav-link">Hello Alias</p>
                        </li>
                        <li class="nav-item">
                            <p class="nav-link">Love the Fasio</p>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="Login.jsp">Login</a>
                        </li>
                    </ul>

                    <form action="${pageContext.request.contextPath}/home" method="get" class="form-inline my-2 my-lg-0"id="searchForm">
                        <div class="input-group input-group-sm">
                            <input name="kw" value="${kw}" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                            <input type="hidden" name="action" value="search">
                            <input type="hidden" name="categories" value="${categories}">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
<%--                        <p class="btn btn-success btn-sm ml-3" href="#">--%>
<%--                            <i class="fa fa-shopping-cart"></i> Cart--%>
<%--                            <span class="badge badge-light">3</span>--%>
<%--                        </p>--%>
                    </form>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Chaien Model Shoes Store</h1>
                <p class="lead text-muted mb-0">Uy tín tạo nên thương hiệu với hơn 10 năm cung cấp các sản phầm giày nhập siêu xịn xò.</p>
            </div>
        </section>
        <!--end of menu-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Category</a></li>
                            <li class="breadcrumb-item active" aria-current="#">Sub-category</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${listCC}" var="o">
                                <li class="list-group-item text-white"><a href="${pageContext.request.contextPath}/home?action=category&id=${o.cid}">${o.cname}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Last product</div>
                        <div class="card-body">
                            <img class="img-fluid" src="${p.image}" />
                            <h5 class="card-title">${p.name}</h5>
                            <p class="card-text">${p.title}</p>
                            <p class="bloc_left_price">${p.price} $</p>
                        </div>
                    </div>
                </div>

                <div class="col-sm-9">
                    <div class="row">
                        <c:forEach items="${listP}" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${o.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="#" title="View Product">${o.name}</a></h4>
                                        <p class="card-text show_txt">${o.title}</p>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${o.price} $</p>
                                            </div>
                                            <div class="col">
                                                <a href="#" class="btn btn-success btn-block">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>

        <!-- Footer -->
        <footer class="text-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-lg-4 col-xl-3">
                        <h5>About</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <p class="mb-0">
                            Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression.
                        </p>
                    </div>

                    <div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
                        <h5>Informations</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><p>USA Bitis Sale Big 99%</p></li>
                            <li><p>Europe Big new Sale</p></li>
                            <li><p>Sports Nike Big Product</p></li>
                            <li><p>Other Product</p></li>
                        </ul>
                    </div>

                    <div class="col-md-3 col-lg-2 col-xl-2 mx-auto">
                        <h5>Mã giảm giá</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><p>Mua 1 tặng 1</p></li>
                            <li><p>Mua 2 tặng 3 Giảm giá 30%</p></li>
                            <li><p>Mua 3 tặng 4 Giảm 50k</p></li>
                            <li><p>Mua 4 giảm 70% + Phiếu mua miễn phí 1 ngày</p></li>
                        </ul>
                    </div>

                    <div class="col-md-4 col-lg-3 col-xl-3">
                        <h5>Contact</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><i class="fa fa-home mr-2"></i> Chaien Model Shoes Store Bitis Store | Bootstrap CRUD Data Table for Database with Modal Form</li>
                            <li><i class="fa fa-envelope mr-2"></i> nhathuynguyenhai@gmail.com</li>
                            <li><i class="fa fa-phone mr-2"></i> + 84 84 81 01 999</li>
                            <li><i class="fa fa-print mr-2"></i> + 84 84 81 01 999</li>
                        </ul>
                    </div>
                    <div class="col-12 copyright mt-3">
                        <p class="float-left">
                            <a href="#">Back to top</a>
                        </p>
                        <p class="text-right text-muted">created with <i class="fa fa-heart"></i> by <a href="https://t-php.fr/43-theme-ecommerce-bootstrap-4.html"><i>t-php</i></a> | <span>v. 1.0</span></p>
                    </div>
                </div>
            </div>
        </footer>
        <script>
            /*
            document.getElementById("searchForm").addEventListener("submit", function(event) {
                event.preventDefault(); // Ngăn chặn việc gửi yêu cầu POST mặc định
                var searchInputValue = document.getElementById("searchInput").value.trim();
                if (searchInputValue.length > 0) {
                    // Tạo URL tìm kiếm dựa trên ký tự nhập vào
                    var searchAction = "home?action=search&name=" + encodeURIComponent(searchInputValue);
                    // Thay đổi action của form
                    document.getElementById("searchForm").action = searchAction;
                    // Gửi yêu cầu POST
                    document.getElementById("searchForm").submit();
                } else {
                    // Xử lý nếu không có ký tự nhập vào
                    alert("Please enter a search term.");
                }
            });
             */
        </script>
    </body>
</html>

