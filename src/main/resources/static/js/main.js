var radio = document.getElementsByName('choice'); // form replacement
for (var i = 0; i < radio.length; i++) {
    radio[i].onchange = function() {
        if (this.value == "s") {  // form for an signup
            console.log('1');
            $('.signup').addClass('signup-active');
            $('.reg').removeClass('reg-active');
            $('.signup-choice').removeClass('hidden_question');
            $('.signup-choice').addClass('active_question');
            $('.reg-choice').removeClass('active_question');
            $('.reg-choice').addClass('hidden_question');
        }
        else if (this.value == "r") {  // form for a reg
            console.log('2');
            $('.reg').addClass('reg-active');
            $('.signup').removeClass('signup-active');
            $('.reg-choice').removeClass('hidden_question');
            $('.reg-choice').addClass('active_question');
            $('.signup-choice').removeClass('active_question');
            $('.signup-choice').addClass('hidden_question');
        }
    }
}

/*  SWIPER IVENTS */

let SwiperIvents = new Swiper(".mySwiperIvents", {
    loop: true, // бесконечный слайдер
    slidesPerView: 3,
    spaceBetween: 50,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
});

/*  SWIPER TEACHERS */

let SwiperTeachers = new Swiper(".mySwiperTeachers", {
    loop: true, // бесконечный слайдер
    slidesPerView: 1,
    spaceBetween: 50,
    mousewheel: {  // прокрутка мышью с помощью колеса
        sensitivity: 1,
    },
    pagination: {
        el: ".swiper-pagination",
        dynamicBullets: true,
    },
    autoplay: {
        delay: 5000,
    },
});

/*  SPOILER  */

$(document).ready(function () {

    $('.spoiler-title').click(function (event) {

        if ($('.spoiler').hasClass('one')) {
            $('.spoiler-title').not($(this)).removeClass('active');
            $('.spoiler-text').not($(this).next()).slideUp(300);
        }

        $(this).toggleClass('active').next().slideToggle(300);
    });
});


