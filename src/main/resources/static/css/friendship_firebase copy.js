const firebaseConfig = {
    apiKey: "AIzaSyAYTzE1F3gZiHU274TiSb5FV85VtCpRw9Q",
    authDomain: "alohcmute-71b8f.firebaseapp.com",
    databaseURL: "https://alohcmute-71b8f-default-rtdb.firebaseio.com",
    projectId: "alohcmute-71b8f",
    storageBucket: "alohcmute-71b8f.appspot.com",
    messagingSenderId: "682697103488",
    appId: "1:682697103488:web:799a8dbf97c95ec5ea6e65",
    measurementId: "G-M4CGJ5VVKH"
};

firebase.initializeApp(firebaseConfig);

const db = firebase.database().ref('Friendships');
const saveFriendship = (user1, user2, frienshipStatus, friendshipId) => {
    var newdb = db.child("userId"+friendshipId);

    newdb.set({
        user1_id: user1,
        user2_id: user2,
        frienshipStatus: frienshipStatus,
    });
}

function executeFunctionBasedOnTopic() {
    var divElement = $('[data-topic]');
    if (divElement.length > 0) {
        var topicValue = divElement.attr('data-topic');
        if (topicValue === 'friend') {
            loadFriend();
        } else if (topicValue === 'requested') {
            loadRequested();
        } else {
            loadAddFriend();
        }
    }
}

function addFriendship(user1_id, user2_id, status){
    var user1 = user1_id;
    var user2 = user2_id;
    var friendship_status = status;
    var newFriendships = {
        "status": friendship_status
    }
    $.ajax({
        url: 'http://localhost:1999/api/friendship/?userId1='+user1+'&userId2='+user2,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newFriendships),
        success: function(response) {
            location.reload();
        },
        error: function(error) {
            console.log(error.responseJSON.message)
        }
    })
}

function updateStatus(user1_id, user2_id, status) {
    var user1 = user1_id;
    var user2 = user2_id;
    var friendship_status = status;
    console.log("ACCEPTED ",user2);
    $.ajax({
        url: 'http://localhost:1999/api/friendship/edit-status/?userId1='+user1+'&userId2='+user2+'&status='+friendship_status,
        type: 'PUT',
        success: function() {
            location.reload();
        },
        error: function(error) {
            console.log(error.responseJSON.message)
        }
    })
    
}

function deleteFriendship(user1_id, user2_id) {
    var user1 = user1_id;
    var user2 = user2_id;
    $.ajax({
        url: 'http://localhost:1999/api/friendship/delete/?userId1='+user1+'&userId2='+user2,
        type: 'DELETE',
        success: function() {
            location.reload();
        },
        error: function(error) {
            console.log(error.responseJSON.message)
        }
    })
}

function loadFriend() {
    var userId = $("#userId").text();
    console.log(userId);
    $.ajax({
        url: 'http://localhost:1999/api/profile/'+userId+'/?status=ACCEPTED',
        type: 'GET',
        success: function (data) {
            populateUserData(data)
        },
        error: function () {
            alert('Failed to fetch user data.');
        }
    });

    function populateUserData(data) {
        for (var friend of data) {
            var shortBio = friend.bio.length > 50 ? friend.bio.substring(0, 50) + '...' : friend.bio;
            var card = '<div class="user-info-card">'
                + '<img src="/images/avatar.jpg" class="img" alt="">'
                + '<div class="card-body">'
                + '<p class="card-text">' + friend.firstName + " " + friend.lastName + '</p>'
                + '<p class="card-bio">' + shortBio + '</p>'
                + '<a href="http://localhost:1999/friendships/friend/profile/' + friend.userId + '" class="btn btn-add btn-primary profile-link">Trang Cá Nhân</a>'
                + '<button class="btn btn-add btn-primary" id="delete' + friend.userId + '">Xóa Kết Bạn</button>'
                + '</div></div>';
            $("#friendshipForm").append(card);

            $("#delete" + friend.userId).click(function () {
                var userId1 = $("#userId").text();
                var userId2 = friend.userId;
                deleteFriendship(userId1, userId2);
                deleteFriendship(userId2, userId1);
            });
        }
    }
    
    
    
}

function loadRequested() {
    var userId = $("#userId").text();
    console.log(userId);
    $.ajax({
        url: 'http://localhost:1999/api/profile/'+userId+'/?status=REQUESTED',
        type: 'GET',
        success: function (data) {
            populateUserData(data)
        },
        error: function () {
            alert('Failed to fetch user data.');
        }
    });

    function populateUserData(data) {
        for (var friend of data) {
            var shortBio = friend.bio.length > 50 ? friend.bio.substring(0, 50) + '...' : friend.bio;
            var card = '<div class="user-info-card">'
                + '<img src="/images/avatar.jpg" class="img" alt="">'
                + '<div class="card-body">'
                + '<p class="card-text">'+friend.firstName+" "+friend.lastName+'</p>'
                + '<p class="card-bio">'+shortBio+'</p>'
                + '<button class="btn btn-add btn-primary" id="accepted'+friend.userId+'">Chấp nhận</button>'
                + '<button class="btn btn-add btn-primary" id="decline'+friend.userId+'">Từ chối</button>'
                +'</div></div>';
            $("#friendshipForm").append(card );
            $("#accepted"+friend.userId).click(function() {
                var userId1 = $("#userId").text();
                var userId2 = friend.userId;
                var status = 'ACCEPTED';
                updateStatus(userId1, userId2, status);
                updateStatus(userId2, userId1, status);
            })
            $("#decline"+friend.userId).click(function() {
                console.log("delete");
                var userId1 = $("#userId").text();
                var userId2 = friend.userId;
                deleteFriendship(userId1, userId2);
                deleteFriendship(userId2, userId1);
            })
        }
    }
}

function loadAddFriend() {
    var userId = $("#userId").text();
    console.log(userId);
    $.ajax({
        url: 'http://localhost:1999/api/profile/'+userId+'/notfriend',
        type: 'GET',
        success: function (data) {
            populateUserData(data)
        },
        error: function () {
            alert('Failed to fetch user data.');
        }
    });

    function populateUserData(data) {
        for (var friend of data) {
            var shortBio = friend.bio.length > 50 ? friend.bio.substring(0, 50) + '...' : friend.bio;
            var card = '<div class="user-info-card">'
                + '<img src="/images/avatar.jpg" class="img" alt="">'
                + '<div class="card-body">'
                + '<p class="card-text">'+friend.firstName+" "+friend.lastName+'</p>'
                + '<p class="card-bio">'+shortBio+'</p>'
                + '<button class="btn btn-add btn-primary" id="addfr'+friend.userId+'">Thêm bạn</button>'
                +  '</div></div>';

            $("#friendshipForm").append(card );
            $("#addfr" + friend.userId).click(function() {
                var userId1 = $("#userId").text();
                var userId2 = friend.userId;
                var status = 'SENDED';
                addFriendship(userId1, userId2, status);
                status = 'REQUESTED';
                addFriendship(userId2, userId1, status);
                // saveFriendship(userId1, userId2, status);
            });
        }
    }
}

// Gọi hàm khi trang tải xong
$(document).ready(function() {
    executeFunctionBasedOnTopic();
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
});