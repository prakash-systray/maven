<div class="tab-pane fade" id="images" role="tabpanel">
<div id="vendorImageDetailsLoaderDiv"></div>
		
                        
                        <div class="vertical-tab">
                            <ul class="nav nav-tabs flex-column" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#slider" role="tab" aria-selected="true" onclick="loadSliderImages();">Slider</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#gallery" role="tab" aria-selected="false" onclick="loadGalleryImages();">Gallery</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#menu" role="tab" aria-selected="false" onclick="loadMenuImages();">Menu</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#videos" role="tab" aria-selected="false" onclick="loadVideosImages();">Videos</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade show active" id="slider" role="tabpanel">
                                    <jsp:include page="sliderImages.jsp" />
                                </div>
                                <div class="tab-pane fade" id="gallery" role="tabpanel">
                                   <jsp:include page="galleryImages.jsp" />
                                </div>
                                <div class="tab-pane fade" id="menu" role="tabpanel">
                                   <jsp:include page="menuImages.jsp" />
                                </div>
                                 <div class="tab-pane fade" id="videos" role="tabpanel">
                                   <jsp:include page="vendorVideos.jsp" />
                                </div>
                            </div>
                        </div>
		
</div>
	 	