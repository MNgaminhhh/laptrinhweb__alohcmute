
$(document).ready(function() {
    function init_button() {
        var buttons = '<button type="button" class="btn btn-primary" id="all-friend">Tất cả bạn bè</button>'
                    + '<button type="button" class="btn btn-primary" id="all-requested">Lời mời kết bạn</button>'
        $(".btn-group-vertical").append(buttons)
    }
    init_button()
    $("#all-requested").click(function() {
        window.location = "http://localhost:1999/friendships/friend_requested/1"
    });
    $("#all-friend").click(function() {
        window.location = "http://localhost:1999/friendships/friend/1"
    });
})