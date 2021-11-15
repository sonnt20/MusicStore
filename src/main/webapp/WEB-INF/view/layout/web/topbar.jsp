<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__search">
        <form action="#">
            <input type="text" placeholder="Search and hit enter...">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <div class="offcanvas__logo">
        <a href="./index.html"><img src="<%=request.getContextPath()%>/assets/project/style/web/img/logo.png" alt=""></a>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__social">
        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-pinterest"></i></a>
        <a href="#"><i class="fa fa-instagram"></i></a>
        <a href="#"><i class="fa fa-dribbble"></i></a>
    </div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<header class="header">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-8">
                <div class="header__logo">
                    <a href="./index.html"><img src="<%=request.getContextPath()%>/assets/project/style/web/img/logo.png" alt=""></a>
                </div>
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="active"><a href="./index.html">Home</a></li>
                        <li><a href="./about.html">About</a></li>
                        <li><a href="./episodes.html">Episodes</a></li>
                        <li><a href="#">Pages</a>
                            <ul class="dropdown">
                                <li><a href="./about.html">About</a></li>
                                <li><a href="./episodes-details.html">Episodes</a></li>
                                <li><a href="./blog.html">Blog</a></li>
                                <li><a href="./blog-details.html">Blog Details</a></li>
                            </ul>
                        </li>
                        <li><a href="./contact.html">Contact</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-4">
                <div class="header__right">
                    <div class="header__right__search">
                        <form action="#">
                            <input type="text" placeholder="Search and hit enter...">
                            <button type="submit"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                    <div class="header__right__social">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-pinterest"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                        <a href="#"><i class="fa fa-dribbble"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>
<!-- Header Section End -->