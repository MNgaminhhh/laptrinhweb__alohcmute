$(document).ready(function() {
    function init_button() {
        var buttons = '<button type="button" class="btn btn-primary" id="all-friend">Tất cả bạn bè</button>'
                    + '<button type="button" class="btn btn-primary" id="all-requested">Lời mời kết bạn</button>'
                    + '<button type="button" class="btn btn-primary" id="add-friend">Thêm bạn</button>'
        $(".btn-group-vertical").append(buttons)
    }
    init_button();
    $("#all-requested").click(function() {
        var currentUrl = window.location.href;
        var newUrl;
        if (currentUrl.includes('/friend/')) {
            newUrl = currentUrl.replace('/friend/', '/friend_requested/');
        } else if (currentUrl.includes('/add_friend/')) {
            newUrl = currentUrl.replace('/add_friend/', '/friend_requested/');
        }
        else {
            newUrl = currentUrl;
        }
        window.location.href = newUrl;
    });
    $("#all-friend").click(function() {
        var currentUrl = window.location.href;
        var newUrl;
        if (currentUrl.includes('/friend_requested/')) {
            newUrl = currentUrl.replace('/friend_requested/', '/friend/');
        } else if (currentUrl.includes('/add_friend/')) {
            newUrl = currentUrl.replace('/add_friend/', '/friend/');
        }
        else {
            newUrl = currentUrl;
        }
        window.location.href = newUrl;
    });
    $("#add-friend").click(function() {
        var currentUrl = window.location.href;
        var newUrl;
        if (currentUrl.includes('/friend_requested/')) {
            newUrl = currentUrl.replace('/friend_requested/', '/add_friend/');
        } else if (currentUrl.includes('/friend/')) {
            newUrl = currentUrl.replace('/friend/', '/add_friend/');
        }
        else {
            newUrl = currentUrl;
        }
        window.location.href = newUrl;
    });
})