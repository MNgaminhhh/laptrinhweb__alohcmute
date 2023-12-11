$(document).ready(function() {
    $.ajax({
        url: 'http://localhost:1999/api/friendship/?userId1=1&status=ACCEPTED',
        type: 'GET',
        success: function (data) {
            populateFriendship(data);
        },
        error: function () {
            alert('Failed to fetch user data.');
        }
    })
    async function populateFriendship(data) {
        for (var friendship of data) {
            var userid = friendship.user2.userId
            console.log(userid)
            try {
            var profileData = await getProfile(userid);
            console.log(profileData);

        var card = '<div class="card mb-3" style="max-width: 540px;">' +
            '<div class="row g-0">' +
            '<div class="col-md-4">' +
            '<img src="..." class="img-fluid rounded-start" alt="...">' +
            '</div>' +
            '<div class="col-md-8">' +
            '<div class="card-body">' +
            '<h5 class="card-title">' + profileData.firstName+' '+profileData.lastName + '</h5>' + // Sử dụng thông tin từ profileData
            '</div>' +
            '</div>' +
            '</div>';

        $(".container1").append(card);
    } catch (error) {
        console.error('Error:', error);
    }
    }}

    function getProfile(userId) {
       return fetch(`/api/profile/${userId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
    }

    function init_button() {
        var buttons = '<button type="button" class="btn btn-primary" id="all-friend">Tất cả bạn bè</button>'
                    + '<button type="button" class="btn btn-primary" id="all-requested">Lời mời kết bạn</button>'
        $(".btn-group-vertical").append(buttons)
    }
    init_button()
    $("#all-requested").click(function() {
        window.location.href = "http://localhost:1999";
    });
})