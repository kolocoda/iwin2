(function($){  "use strict";
	// Define your library strictly...  
	jQuery(document).ready(function ($) {

		// Preloadify
		$(".minithumb, .gallery, .grid, .gallery-item, .postimg, .lightbox, .bio").preloadify({
			force_icon: "true",
			mode: "sequence"
		});

		// PrettyPhoto
		$("a[data-rel^='prettyPhoto']").prettyPhoto({
			theme: 'pp_default'
		});

		// Hover Socialicons
		$('.atpsocials li').hover(function () {
			$(this).find('span.ttip').fadeIn();
		}, function () {
			$(this).find('span.ttip').fadeOut();
		});

		/* --------------Planbox hover options----------------------- */
		$('.plan_box').hover(function () {
			$(".plan_info", this).stop().animate({
				top: '-430'
			}, {
				queue: false,
				duration: 300
			});
		}, function () {
			$(".plan_info", this).stop().animate({
				top: '0'
			}, {
				queue: false,
				duration: 300
			});
		});

		// Message box close
		$("span.close").click(function () {
			$(this).hide();
			$(this).parent().parent().animate({
				opacity: '0'
			}).slideUp(400);
		});

		// Vidoe Resize Fitvids
		$('.video-frame, .boxcontent, .video-stage, .video, .wp-video').fitVids();

		// Hide Scroll to Top
		// see style.css #back-top css rule
		$("#back-top").hide();
		// fade in and out block
		$(window).scroll(function () {
			if ($(this).scrollTop() > 100) {
				$('#back-top').fadeIn();
			} else {
				$('#back-top').fadeOut();
			}
		});
		// Scroll to top of the page body element
		$('#back-top a').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 800);
			return false;
		});

		/* ------------------------------------- */
		jQuery('.select_wrapper').each(function () {
			jQuery(this).prepend('<span>' + jQuery(this).find('.select option:selected').text() + '</span>');
		});
		jQuery('.select').live('change', function () {
			jQuery(this).prev('span').replaceWith('<span>' + jQuery(this).find('option:selected').text() + '</span>');
		});
		jQuery('.select').bind(jQuery.browser.msie ? 'click' : 'change', function(event) {
			jQuery(this).prev('span').replaceWith('<span>' + jQuery(this).find('option:selected').text() + '</span>');
		}); 

		// Fixed Header
		$(function(){
			
			jQuery("#sf-mobilemenu").removeAttr('class');		 
			var $aSelected = $('#wrapper').find('div');

			if( $aSelected.is('#fixedheader') ){
				// Check the initial Poistion of the Sticky Header
				var stickyHeaderTop = $('#fixedheader').offset().top;
				$(window).scroll(function(){
					if( $(window).scrollTop() > stickyHeaderTop ) {
						$('#fixedheader').addClass("fixed-header");
						if ( jQuery(window).width() > 1024) {
							$('.logo img').css({'transform':'scale(0.7)'});
						}
					} else {
						$('#fixedheader').removeClass("fixed-header");
						if ( jQuery(window).width() > 1024) {
							$('.logo img').css({'transform':'scale(1)'});
						}
					}
				});
			}
		});

		// Mobile Menu
		jQuery('.iva-mobile-dropdown').click(function(){
			jQuery('.iva-mobile-menu').slideToggle(500);
			return false;
		});
	 
		// Child Menu Toggle
		jQuery('.iva-children-indenter').click(function(){
			jQuery(this).parent().parent().toggleClass('iva-menu-open');
			jQuery(this).parent().parent().find('> ul').slideToggle();
			
			return false;
		});

		// Way Point Fade Animation
		jQuery('.iva_anim:not(.animated)').waypoint(function () {
			var animatedclass = jQuery(this).attr('data-id');
			jQuery(this).addClass('animate');
			jQuery(this).addClass('animated  ' + animatedclass + '');
		}, { offset:'85%' });


		// Addes Superfish css to ul#atp_menu
		jQuery("#atp_menu").superfish({ cssArrows: false} );


		// Fancy Toggle
		systoggle();
		// Addes Custom Data Attributes to Button Elements/Class
		buttondata();
		// Flex Slider
		flexslider();
		// Accordion
		accordion();
		// Progress Bar
		progressbar();
		// Custom Tabs
		tabs();
		// Hover image
		hoverimage();

		// Mobile Menu Executes on Window Resize
		mobilemenu();
		jQuery(window).resize(function () {
			mobilemenu();
		});
	});       
})();


/* ---------------Toggle---------------------- */
function systoggle(){
	jQuery(".toggle-title").click(function () {
		jQuery(this).next(".toggle_content").slideToggle({ duration: 200 });
	});

	jQuery(".toggle-title").toggle(function () {
		jQuery(this).addClass("active");
	}, function () {
		jQuery(this).removeClass("active");
	});

	jQuery("#trigger").click(function () {
		jQuery(this).next("#sticky").slideToggle({duration: 300, easing: 'easeOutQuart' });
	});

	jQuery("#trigger").toggle(function () {
		jQuery(this).animate({ top: 0 }, 50).animate({ top: 0 }, 50).animate({ top: 0 }, 800).addClass("active");
	}, function () {
		jQuery(this).animate({ top: 0 }, 50).animate({ top: 0 }, 50).animate({ top: 0 }, 800).removeClass("active");
	});
}

function buttondata(){
	jQuery(".btn").hover(function () {
		var $hoverBg = jQuery(this).attr('btn-hoverBg');
		var $hoverColor = jQuery(this).attr('btn-hoverColor');
		var $borderhoverColor = jQuery(this).attr('btn-hoverborder');

		if ($hoverBg !== undefined) {
			jQuery(this).css('background-color', $hoverBg);
		} else {}
			if ($borderhoverColor !== undefined) {
				jQuery(this).css('border-color', $borderhoverColor);
			} else {}
			if ($hoverColor !== undefined) {
				jQuery('span', this).css('color', $hoverColor);
			} else {}
			
	}, function () {
		var $btnbg = jQuery(this).attr('btn-bg');
		var $btncolor = jQuery(this).attr('btn-color');
		var $btnborder = jQuery(this).attr('btn-border');
		if ($btnbg !== undefined) {
			jQuery(this).css('background-color', $btnbg);
		}
		if ($btnborder !== undefined) {
			jQuery(this).css('border-color', $btnborder);
		}
		if ($btncolor !== undefined) {
			jQuery('span', this).css('color', $btncolor);
		}
	});
}

function flexslider(){
	jQuery('.flexslider').flexslider({
		animation: "slide",
		controlsContainer: ".flex-container",
		slideshowSpeed: 3000,
		animationDuration: 1000,
		directionNav: true,
		controlNav: false,
		mousewheel: false,
		slideshow: false
	});
}

function  accordion(){
	jQuery('.ac_wrap ').each(function () {
		tabid = jQuery(this).attr('id');
		//jQuery("#" + tabid + " .ac_title:eq(1)").addClass("active");
		jQuery("#" + tabid + " .ac_content:not('.active')").hide();
	});

	jQuery(".ac_wrap .ac_title").click(function () {
		jQuery(this).next(".ac_content").slideToggle(400, 'swing').siblings(".ac_content:visible").slideUp(400, 'swing');
		jQuery(this).toggleClass("active");
		jQuery(this).siblings(".ac_title").removeClass("active");
	});
}

function progressbar(){
	jQuery('.progress_bar').each(function () {
		jQuery(this).appear(function() {
			var percent = jQuery(this).attr('data_width');
			jQuery(this).animate({
				width: percent
			}, 1500);
		},{
			accX: 0, accY: -150
		});
	});
}

function tabs(){
	jQuery('.systabspane ').each(function () {
		tabid = jQuery(this).attr('id');
		jQuery("#" + tabid + " .tab_content").hide(); // Hide all tab conten divs by default
		
		if ( document.location.hash!=='') {
			var tabName = window.location.hash;
				// Shorten #tab2 to just last digit "2".
				 var tabNumber = tabName.substr(4,1);
				jQuery("#" + tabid + " .tab_content:eq("+tabNumber+")").show(); // Show the first div of tab content by default
				jQuery("#" + tabid + " ul.tabs li:nth-child("+tabNumber+")").addClass("current"); // Show the current by default
			}else{
				jQuery("#" + tabid + " .tab_content:first").show(); // Show the first div of tab content by default
				jQuery("#" + tabid + " ul.tabs li:first").addClass("current"); // Show the current by default
			}	

				 
		});
		jQuery("ul.tabs li").click(function () { //Fire the click event
			tab_id = jQuery(this).parents('.systabspane').attr("id");
			
			var activeTab = jQuery(this).attr("id"); // Catch the click link
			window.location.hash = activeTab;
			jQuery("#" + tab_id + " ul li").removeClass("current"); // Remove pre-highlighted link
			jQuery(this).addClass("current"); // set clicked link to highlight state
			jQuery("#" + tab_id + " .tab_content").hide(); // hide currently visible tab content div
			jQuery(activeTab).fadeIn(600); // show the target tab content div by matching clicked link.
		});
}

function hoverimage(){
	jQuery("a[data-rel^='prettyPhoto']").each(function () {
		var $image = jQuery(this).contents("img");
		$hoverclass = 'hovervideo';

		if (jQuery(this).attr('href').match(/(jpg|gif|jpeg|png|tif)/)) $hoverclass = 'hoverimage';

		if ($image.length > 0) {
			var $hoverbg = jQuery("<span class='" + $hoverclass + "'></span>").appendTo(jQuery(this));

			jQuery(this).bind('mouseenter', function () {
				$height = $image.height();
				$width = $image.width();
				$pos = $image.position();
				$hoverbg.css({
					height: $height,
					width: $width,
					top: $pos.top,
					left: $pos.left
				});
			});
		}

		jQuery("a[data-rel^='prettyPhoto']").contents("img").hover(function () {
			jQuery(this).stop().animate({
				"opacity": "0.3"
			}, 200);
			jQuery("span[class^=hover]").stop().animate({
				"opacity": "1"
			});
		}, function () {
			jQuery(this).stop().animate({
				"opacity": "1"
			}, 200);
			jQuery("span[class^=hover]").stop().animate({
				"opacity": "0"
			});
		});
	});
}

function mobilemenu(){
	// show meun starting from iPad Portrait
	if( window.innerWidth < 768 ){
		jQuery('.header #menuwrap').hide();
	}else {
		jQuery('.header #menuwrap').show();
		jQuery('.iva-mobile-menu').hide();
	}
}

function MySlider(interval, id) {

	var slides, cnt, amount, i;

	function run() {
		// hiding previous image and showing next
		jQuery(slides[i]).fadeOut('slow', function () {
			// Animation complete.
			i++;
			if (i >= amount) i = 0;
			jQuery(slides[i]).fadeIn('slow');

			// updating counter
			cnt.text(i + 1 + ' / ' + amount);
		});
		// loop
		setTimeout(run, interval);
	}

	//slides = $('.testimonials > li');
	slides = jQuery('#' + id + ' .testimonials > li');
	cnt = jQuery('#counter');
	amount = slides.length;
	i = 0;

	// updating counter
	cnt.text(i + 1 + ' / ' + amount);

	if (amount > 1) setTimeout(run, interval);
}

/*!
 * classie - class helper functions
 * from bonzo https://github.com/ded/bonzo
 * 
 * classie.has( elem, 'my-class' ) -> true/false
 * classie.add( elem, 'my-new-class' )
 * classie.remove( elem, 'my-unwanted-class' )
 * classie.toggle( elem, 'my-class' )
 */

/*jshint browser: true, strict: true, undef: true */
/*global define: false */

(function (window) {

	'use strict';

	// class helper functions from bonzo https://github.com/ded/bonzo

	function classReg(className) {
		return new RegExp("(^|\\s+)" + className + "(\\s+|$)");
	}

	// classList support for class management
	// altho to be fair, the api sucks because it won't accept multiple classes at once
	var hasClass, addClass, removeClass;

	if ('classList' in document.documentElement) {
		hasClass = function (elem, c) {
			return elem.classList.contains(c);
		};
		addClass = function (elem, c) {
			elem.classList.add(c);
		};
		removeClass = function (elem, c) {
			elem.classList.remove(c);
		};
	} else {
		hasClass = function (elem, c) {
			return classReg(c).test(elem.className);
		};
		addClass = function (elem, c) {
			if (!hasClass(elem, c)) {
				elem.className = elem.className + ' ' + c;
			}
		};
		removeClass = function (elem, c) {
			elem.className = elem.className.replace(classReg(c), ' ');
		};
	}

	function toggleClass(elem, c) {
		var fn = hasClass(elem, c) ? removeClass : addClass;
		fn(elem, c);
	}

	var classie = {
		// full names
		hasClass: hasClass,
		addClass: addClass,
		removeClass: removeClass,
		toggleClass: toggleClass,
		// short names
		has: hasClass,
		add: addClass,
		remove: removeClass,
		toggle: toggleClass
	};

	// transport
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(classie);
	} else {
		// browser global
		window.classie = classie;
	}

})(window);



//Section Video jQuery
// @uses : Mediaelement.js jQuery as a dependency
jQuery(window).load(function ($) {
	setTimeout(function () {
		if(jQuery(window).width() > 900) {
			if(jQuery('#boxed') .length > 0 ) {
				$width = jQuery('#boxed').width();
			} else {
				$width = jQuery('html').width();
			}
			jQuery('.iva-section-video').css('visibility', 'visible');
			jQuery('.iva-section-video').css('width',$width);
		}else{
			jQuery('.iva-section-video').hide();
		}
	}, 4000);

});

/**
 * Copyright (c) 2007-2014 Ariel Flesler - aflesler<a>gmail<d>com | http://flesler.blogspot.com
 * Licensed under MIT
 * @author Ariel Flesler
 * @version 1.4.14
 * http://flesler.blogspot.com/2007/10/jqueryscrollto.html
 */
;(function(k){'use strict';k(['jquery'],function($){var j=$.scrollTo=function(a,b,c){return $(window).scrollTo(a,b,c)};j.defaults={axis:'xy',duration:0,limit:!0};j.window=function(a){return $(window)._scrollable()};$.fn._scrollable=function(){return this.map(function(){var a=this,isWin=!a.nodeName||$.inArray(a.nodeName.toLowerCase(),['iframe','#document','html','body'])!=-1;if(!isWin)return a;var b=(a.contentWindow||a).document||a.ownerDocument||a;return/webkit/i.test(navigator.userAgent)||b.compatMode=='BackCompat'?b.body:b.documentElement})};$.fn.scrollTo=function(f,g,h){if(typeof g=='object'){h=g;g=0}if(typeof h=='function')h={onAfter:h};if(f=='max')f=9e9;h=$.extend({},j.defaults,h);g=g||h.duration;h.queue=h.queue&&h.axis.length>1;if(h.queue)g/=2;h.offset=both(h.offset);h.over=both(h.over);return this._scrollable().each(function(){if(f==null)return;var d=this,$elem=$(d),targ=f,toff,attr={},win=$elem.is('html,body');switch(typeof targ){case'number':case'string':if(/^([+-]=?)?\d+(\.\d+)?(px|%)?$/.test(targ)){targ=both(targ);break}targ=win?$(targ):$(targ,this);if(!targ.length)return;case'object':if(targ.is||targ.style)toff=(targ=$(targ)).offset()}var e=$.isFunction(h.offset)&&h.offset(d,targ)||h.offset;$.each(h.axis.split(''),function(i,a){var b=a=='x'?'Left':'Top',pos=b.toLowerCase(),key='scroll'+b,old=d[key],max=j.max(d,a);if(toff){attr[key]=toff[pos]+(win?0:old-$elem.offset()[pos]);if(h.margin){attr[key]-=parseInt(targ.css('margin'+b))||0;attr[key]-=parseInt(targ.css('border'+b+'Width'))||0}attr[key]+=e[pos]||0;if(h.over[pos])attr[key]+=targ[a=='x'?'width':'height']()*h.over[pos]}else{var c=targ[pos];attr[key]=c.slice&&c.slice(-1)=='%'?parseFloat(c)/100*max:c}if(h.limit&&/^\d+$/.test(attr[key]))attr[key]=attr[key]<=0?0:Math.min(attr[key],max);if(!i&&h.queue){if(old!=attr[key])animate(h.onAfterFirst);delete attr[key]}});animate(h.onAfter);function animate(a){$elem.animate(attr,g,h.easing,a&&function(){a.call(this,targ,h)})}}).end()};j.max=function(a,b){var c=b=='x'?'Width':'Height',scroll='scroll'+c;if(!$(a).is('html,body'))return a[scroll]-$(a)[c.toLowerCase()]();var d='client'+c,html=a.ownerDocument.documentElement,body=a.ownerDocument.body;return Math.max(html[scroll],body[scroll])-Math.min(html[d],body[d])};function both(a){return $.isFunction(a)||$.isPlainObject(a)?a:{top:a,left:a}}return j})}(typeof define==='function'&&define.amd?define:function(a,b){if(typeof module!=='undefined'&&module.exports){module.exports=b(require('jquery'))}else{b(jQuery)}}));

/*
Plugin: jQuery Parallax
Version 1.1.3
Author: Ian Lunn
Twitter: @IanLunn
Author URL: http://www.ianlunn.co.uk/
Plugin URL: http://www.ianlunn.co.uk/plugins/jquery-parallax/

Dual licensed under the MIT and GPL licenses:
http://www.opensource.org/licenses/mit-license.php
http://www.gnu.org/licenses/gpl.html
*/

(function( $ ){
	var $window = $(window);
	var windowHeight = $window.height();

	$window.resize(function () {
		windowHeight = $window.height();
	});

	$.fn.parallax = function(xpos, speedFactor, outerHeight) {
		var $this = $(this);
		var getHeight;
		var firstTop;
		var paddingTop = 0;
		
		//get the starting position of each element to have parallax applied to it      
		$this.each(function(){
			firstTop = $this.offset().top;
		});

		if (outerHeight) {
			getHeight = function(jqo) {
				return jqo.outerHeight(true);
			};
		} else {
			getHeight = function(jqo) {
				return jqo.height();
			};
		}
			
		// setup defaults if arguments aren't specified
		if (arguments.length < 1 || xpos === null) xpos = "50%";
		if (arguments.length < 2 || speedFactor === null) speedFactor = 0.1;
		if (arguments.length < 3 || outerHeight === null) outerHeight = true;
		
		// function to be called whenever the window is scrolled or resized
		function update(){
			var pos = $window.scrollTop();              

			$this.each(function(){
				var $element = $(this);
				var top = $element.offset().top;
				var height = getHeight($element);

				// Check if totally above or totally below viewport
				if (top + height < pos || top > pos + windowHeight) {
					return;
				}

				$this.css('backgroundPosition', xpos + " " + Math.round((firstTop - pos) * speedFactor) + "px");
			});
		}       

		$window.bind('scroll', update).resize(update);
		update();
	};
})(jQuery);

// Parallax Background for Section Fullwidth Shortcode
jQuery(document).ready(function ($) { 
	$('.section_row.parallaxsection, .section_video.parallaxsection').each(function(){
		var $id = $(this).attr('id');
		$('#'+$id + ".parallaxsection").parallax("50%", 0.2);
	});
});

// Fixed Header
jQuery(document).ready(function ($) {
	var $aSelected = $('#wrapper').find('div');
	if( $aSelected.is('#fixedheader') ){
		// Check the initial Poistion of the Sticky Header
		var stickyHeaderTop = $('#fixedheader').offset().top;
		$(window).scroll(function(){
			if( $(window).scrollTop() > stickyHeaderTop ) {
				$('#fixedheader').addClass("fixed-header");
				if ( jQuery(window).width() > 1024) {
					$('.logo img').css({'transform':'scale(0.7)'});
				}
			} else {
				$('#fixedheader').removeClass("fixed-header");
				if ( jQuery(window).width() > 1024) {
					$('.logo img').css({'transform':'scale(1)'});
				}
			}
		});
	}
});

// FadeIn on Scroll
jQuery(document).ready(function ($) { /* Every time the window is scrolled ... */
	$(window).scroll(function () {
		/* Check the location of each desired element */
		$('.demo').each(function (i) {
			var bottom_of_object = $(this).position().top + $(this).outerHeight();
			var bottom_of_window = $(window).scrollTop() + $(window).height();

			/* If the object is completely visible in the window, fade it it */
			if (bottom_of_window > bottom_of_object) {
				$(this).animate({'opacity': '1'}, 300);
			}
		});
	});
});