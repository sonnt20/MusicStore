<script charset="UTF-8" src="<%=request.getContextPath()%>/assets/project/angularjs/web/home.js"></script>
<script src="https://www.youtube.com/iframe_api"></script>
<script src="https://cdn.rawgit.com/labnol/files/master/yt.js"></script>
<!-- Hero Section Begin -->
<section ng-app="FrameworkBase" ng-controller="frameworkCtrl">
    <section class="hero spad set-bg"
             data-setbg="<%=request.getContextPath()%>/assets/project/style/web/img/hero/hero-bg.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="hero__text">
                        <h5><span class="icon_calendar"></span> 10 Dec 2019</h5>
                        <h2>Ep 05: Astronomy Binoculars A Great</h2>
                        <a href="#" class="primary-btn">Subscribe with iTunes</a>
                        <a href="#" class="primary-btn white-btn">Subscribe with RSS</a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="hero__pic set-bg"
                         data-setbg="<%=request.getContextPath()%>/assets/project/style/web/img/hero/hero-video.png">
                        <a href="https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/249690664&color=%23ff5500&auto_play=false&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true&visual=true"
                           class="play-btn video-popup">
                            <img src="<%=request.getContextPath()%>/assets/project/style/web/img/hero/play-btn.png"
                                 alt=""></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="single__track">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="single__track__item">
                            <div class="single__track__item__pic">
                                <img src="<%=request.getContextPath()%>/assets/project/style/web/img/hero/hero-track.jpg"
                                     alt="">
                            </div>
                            <div class="single__track__item__text">
                                <h5>Understanding Operating</h5>
                                <span>Kyle Hawkins</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="single__track__option">
                            <div class="jp-jplayer jplayer" data-ancestor=".jp_container"
                                 data-url="<%=request.getContextPath()%>/assets/project/style/web/music-files/2.mp3">
                            </div>
                            <div class="jp-audio jp_container" role="application" aria-label="media player">
                                <div class="jp-gui jp-interface">
                                    <!-- Player Controls -->
                                    <div class="player_controls_box">
                                        <button class="jp-play player_button" tabindex="0"></button>
                                    </div>
                                    <!-- Progress Bar -->
                                    <div class="player_bars">
                                        <div class="jp-progress">
                                            <div class="jp-seek-bar">
                                                <div>
                                                    <div class="jp-play-bar">
                                                        <div class="jp-current-time" role="timer" aria-label="time">0:00
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="jp-duration ml-auto" role="timer" aria-label="duration">00:00</div>
                                    </div>
                                    <!-- Volume Controls -->
                                    <div class="jp-volume-controls">
                                        <button class="jp-mute" tabindex="0"><span
                                                class="icon_volume-high"></span></button>
                                        <div class="jp-volume-bar">
                                            <div class="jp-volume-bar-value" style="width: 0%;"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="jp-btns">
                                    <a href="#"><i class="social_share"></i> Share</a>
                                    <a href="#"><i class="fa fa-download"></i> Download</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->
    <!-- Podcast Section Begin -->
    <section class="podcast spad">
        <div class="container">
            <div class="podcast__top">
                <div class="row">
                    <div class="col-lg-5 col-md-5">
                        <h2>Live & Upcoming</h2>
                    </div>
                    <div class="col-lg-7 col-md-7">
                        <ul class="filter__controls">
                            <li class="active" data-filter="*">All</li>
                            <li data-filter=".entrepreneurship">Entrepreneurship</li>
                            <li data-filter=".media">Media</li>
                            <li data-filter=".tech">Tech</li>
                            <li data-filter=".tutorials">Tutorials</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row podcast-filter">
                <div ng-repeat="item in songs" class="col-lg-12 mix entrepreneurship">
                    <div class="podcast__item">
                        <div class="podcast__item__pic">
                            <img src='data:image/jpeg;base64,{{item.image}}' width="280" height="280" alt="">
                        </div>
                        <div class="podcast__item__text">
                            <a href="#" class="heart-icon"><i class="fa fa-heart"></i></a>
                            <ul>
                                <li><span class="icon_calendar"></span>{{item.releaseDate}}</li>
                                <li><span class="icon_profile"></span> by John Smith</li>
                                <li><span class="icon_tags_alt"></span> Radio, Musican, Camp</li>
                            </ul>
                            <h4>{{item.name}}</h4>
                            <p>{{item.description}}</p>
<%--                            <div class="track__option">--%>
<%--                                <div class="jp-jplayer jplayer" data-ancestor=".jp_container-1"--%>
<%--                                     data-url="{{item.linkFull}}"></div>--%>
                            <audio controls autoplay="false">
                                <source src="{{item.linkFull}}" type="audio/mpeg">
                            </audio>
                                <div class="jp-audio jp_container-1" role="application" aria-label="media player">
                                    <div class="jp-gui jp-interface">
                                        <!-- Player Controls -->
                                        <div class="player_controls_box">
                                            <button class="jp-play player_button" tabindex="0"></button>
                                        </div>
                                        <!-- Progress Bar -->
                                        <div class="player_bars">
                                            <div class="jp-progress">
                                                <div class="jp-seek-bar">
                                                    <div>
                                                        <div class="jp-play-bar">
                                                            <div class="jp-current-time" role="timer" aria-label="time">
                                                                0:00
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="jp-duration ml-auto" role="timer" aria-label="duration">00:00
                                            </div>
                                        </div>
                                        <!-- Volume Controls -->
                                        <div class="jp-volume-controls">
                                            <button class="jp-mute" tabindex="0"><span
                                                    class="icon_volume-high"></span></button>
                                            <div class="jp-volume-bar">
                                                <div class="jp-volume-bar-value" style="width: 0%;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="jp-btns">
                                        <a href="#"><i class="social_share"></i> Share</a>
                                        <a href="#"><i class="fa fa-download"></i> Download</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>
    <!-- Podcast Section End -->

    <!-- Call To Action Section Begin -->
    <section class="callto spad set-bg"
             data-setbg="<%=request.getContextPath()%>/assets/project/style/web/img/call-bg.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="callto__text">
                        <h2>Support The Show</h2>
                        <p>Enjoy listening to our podcast? Consider making a donation!</p>
                        <a href="#" class="primary-btn">Make a Donation</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

</section>
