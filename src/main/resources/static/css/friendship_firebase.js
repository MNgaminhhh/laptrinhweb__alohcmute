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
const saveFriendshipFirebase = (user1, user2, frienshipStatus, friendshipId) => {
    var newdb = db.child("friendshipId"+friendshipId);

    newdb.set({
        user1_id: parseInt(user1),
        user2_id: parseInt(user2),
        frienshipStatus: frienshipStatus,
    });
}
const deleteFriendshipByUsers = (user1, user2) => {
    var friendshipRef = db;
    console.log("delete");
    user1 = parseInt(user1);
    user2 = parseInt(user2);

    friendshipRef.once('value', function(snapshot) {
        snapshot.forEach(function(childSnapshot) {
            var data = childSnapshot.val();
            if (data.user1_id === user1 && data.user2_id === user2) {
                childSnapshot.ref.remove();
            }
        });
    });
};

deleteFriendshipByUsers(1, 2);


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
    var user1 = parseInt(user1_id);
    var user2 = parseInt(user2_id);
    var friendship_status = status;
    var newFriendships = {
        "status": friendship_status
    }
    $.ajax({
        url: 'http://localhost:1999/api/friendship/?userId1='+user1+'&userId2='+user2,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newFriendships),
        success: function(data) {
            var friendshipId = data.friendshipId;
            console.log(friendshipId);
            saveFriendshipFirebase(user1, user2, friendship_status, friendshipId);
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
        success: function(data) {
            var friendshipId = data.friendshipId;
            saveFriendshipFirebase(user1, user2, friendship_status, friendshipId);
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
            deleteFriendshipByUsers(user1, user2)

        },
        error: function(error) {
            console.log(error.responseJSON.message)
        }
    })
}

function loadFriend() {
    var userId = parseInt($("#userId").text());
    var status = 'ACCEPTED';
    console.log(userId);
    var friendshipRef = db;

    friendshipRef.on('value', function(snapshot) {
        $("#friendshipForm").empty();
        snapshot.forEach(function(childSnapshot) {
            var data = childSnapshot.val();
            if (data.frienshipStatus === status && data.user1_id === userId) {
                var user2 = data.user2_id;
                $.ajax({
                    url: 'http://localhost:1999/api/profile/' + user2,
                    type: 'GET',
                    success: function(data) {
                        populateUserData(data);
                    },
                    error: function() {
                       
                    }
                });
            }
        });
    });
}

function populateUserData(data) {
    var friend = data;
    var card = '<div class="card" style="width: 18rem; height: 4rem">' +
        '<img src="http://localhost:1999/images/avatar.jpg" class="card-img-top" alt="...">' +
        '<div class="card-body">' +
        '<p class="card-text">' + friend.firstName + " " + friend.lastName + '</p>'+
        '<a href="http://localhost:1999/friendships/friend/profile/' + friend.userId + '" class="btn btn-add btn-primary profile-link">Trang Cá Nhân</a>'+
        '<button class="btn btn-add btn-primary" id="delete'+friend.userId+'">Xóa bạn</button></div></div>';
    $("#friendshipForm").append(card);
    $("#delete"+friend.userId).click(function() {
        console.log("delete");
        var userId1 = $("#userId").text();
        var userId2 = friend.userId;
        deleteFriendship(userId1, userId2);
        deleteFriendship(userId2, userId1);
    })
}

function loadRequested() {
    var userId = parseInt($("#userId").text());
    var status = 'REQUESTED';
    console.log(userId);
    var friendshipRef = db;

    friendshipRef.on('value', function(snapshot) {
        $("#friendshipForm").empty();
        snapshot.forEach(function(childSnapshot) {
            var data = childSnapshot.val();
            if (data.frienshipStatus === status && data.user1_id === userId) {
                var user2 = data.user2_id;
                $(".container1").empty();
                $.ajax({
                    url: 'http://localhost:1999/api/profile/' + user2,
                    type: 'GET',
                    success: function(data) {
                        populateReuqested(data);
                    },
                    error: function() {
                       
                    }
                });
            }
        });
    });
}

function populateReuqested(data) {
    var friend = data;
    var card = '<div class="card" style="width: 18rem; height: 4rem">'
        + '<img src="http://localhost:1999/images/avatar.jpg" class="card-img-top" alt="...">'
        + '<div class="card-body">'
        + '<p class="card-text">'+friend.firstName+" "+friend.lastName+'</p>'
        + '<button class="btn btn-add btn-primary" id="accepted'+friend.userId+'">Chấp nhận</button>'
        + '<button class="btn btn-add btn-primary" id="decline'+friend.userId+'">Từ chối</button>'
        +  '</div></div>';
    $(".container1").append(card);
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
        deleteFriendshipByUsers(userId1, userId2);
        deleteFriendshipByUsers(userId2, userId1);
    })
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
           
        }
    });

    function populateUserData(data) {
        for (var friend of data) {
            console.log(friend);
            var card = '<div class="card" style="width: 18rem; height: 4rem" >'
                + '<img src="http://localhost:1999/images/avatar.jpg" class="card-img-top" alt="...">'
                + '<div class="card-body">'
                + '<p class="card-text">'+friend.firstName+" "+friend.lastName+'</p>'
                + '<button class="btn btn-add btn-primary" id="addfr'+friend.userId+'">Thêm bạn</button>'
                +  '</div></div>';
            $(".container1").append(card);
    
            // Tạo một hàm để bắt giữ giá trị của friend tại thời điểm này
            (function(currentFriend) {
                document.getElementById("addfr" + currentFriend.userId).addEventListener("click", function() {
                    $("#addfr"+currentFriend.userId).text("Đã gửi lời mời kết bạn");
                    var userId1 = $("#userId").text();
                    var userId2 = currentFriend.userId;
                    var status = 'SENDED';
                    addFriendship(userId1, userId2, status);
                    status = 'REQUESTED';
                    addFriendship(userId2, userId1, status);
                    // saveFriendship(userId1, userId2, status);
                });
            })(friend);
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