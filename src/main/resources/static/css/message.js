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

const db = firebase.database().ref('Conversations');
const msdb = firebase.database().ref('Messages')
const saveConversation = (mem_1, mem_2, conversationId) => {
    var newdb = db.child(conversationId);

    newdb.set({
        mem1_id: parseInt(mem_1),
        mem2_id: parseInt(mem_2),
    });
}

const saveMessage = (conversationId, message, createAt, senderId) => {
    var newdb = msdb.child(conversationId);
    var msg = newdb.push();
    msg.set({
        "content": message,
        "createAt": createAt,
        "senderId": senderId,
    });
}

function loadData(conversationId) {
    const messagesRef = firebase.database().ref(`Messages/${conversationId}`);

    messagesRef.on('value', (snapshot) => {
      $(".content").empty();
      snapshot.forEach((childSnapshot) => {
        const message = childSnapshot.val();
        $(".content").append(`
          <div class="message">
            <p>${message.content}</p>
            <span>${message.createAt}</span>
            <span>${message.senderId}</span>
          </div>
        `);
      });
    });
  }

function createButtonNewMessage() {
    $(".user-left").append('<div class="dropdown">'+
    '<button class="btn btn-secondary dropdown-toggle new-msg" type="button" data-bs-toggle="dropdown" aria-expanded="false">Tin nhắn mới</button>'+
    '<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="list-friend">'+
    '</ul></div>')
}

function loadConversation() {
    var member1 = parseInt($("#userId").text());
    var conversationRef = db;

    conversationRef.on('value', function(snapshot){
        $(".user-group").empty();
        snapshot.forEach(function(childSnapshot){
            var id = childSnapshot.key;
            console.log(id);
            var data = childSnapshot.val();
            var mem=-1;
            if (data.mem1_id === member1) {
                mem = data.mem2_id;
            }
            else if (data.mem2_id===member1) {
                mem = data.mem1_id;
            }
            if (mem!=-1) {
                $.ajax({
                    url: 'http://localhost:1999/api/profile/' + mem,
                    type: 'GET',
                    success: function(data) {
                        populateUserData(data, id);
                    },
                    error: function() {
                        alert('Failed to fetch user data.');
                    }
                });
            }
        })
    })
}

function populateUserData(data, id) {
    var friend = data;
    $(".user-group").append(`<div class="conversation_id" id="`+id+`"><div class="card mb-3 card-profile" id="card-of-user`+friend.userId+`" style="max-width: 540px;">
                                <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="https://img.hoidap247.com/picture/question/20200508/large_1588936738888.jpg" class="img-fluid rounded-start" alt="...">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                    <h5 class="card-title">`+friend.firstName + ' ' + friend.lastName+`</h5>
                                    <p class="card-text"><small class="text-body-secondary">Message</small></p>
                                    <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                                    </div>
                                </div>
                                </div>
                                </div></div>`)
}


function loadFriend() {
    var userId = parseInt($("#userId").text());
    var status = 'ACCEPTED';
    $.ajax({
        url: 'http://localhost:1999/api/profile/'+userId+'/?status='+status,
        type: 'GET',
        success: function(data) {
            loadData(data);
        },
        error: function() {
            alert(error.text());
        }
    })
    function loadData(data) {
        for (friend of data) {
            $("#list-friend").append('<li class="friend" id="card-friend'+friend.userId+'"><img src="https://img.hoidap247.com/picture/question/20200508/large_1588936738888.jpg" alt="Avatar" width="50" height="50">'+
            '<span>'+friend.firstName+" "+friend.lastName+'</span></li>')
        }
    }
}

function showMessage(userId, conservationId){
    $.ajax({
        url: "http://localhost:1999/api/profile/"+userId,
        type: "GET",
        success: function(data) {
            var name = data.firstName+" "+data.lastName;
            $(".chat-area").show(); 
            $(".profile-name").text(name);
            $(".Information").attr("id",data.userId);
            $(".Information").closest(".conversation").attr("id","conId"+conservationId)
        }
    })
    loadData(conservationId);
}

$(document).ready(function () {
    createButtonNewMessage();
    $(".user-left").append('<div class="user-group"></div>');
    $(".chat-area").hide(); 
    $(".btn-group-vertical").remove();
    loadFriend();
    loadConversation();
});

$(document).on("click", ".card-profile", function(){
    var cardId = $(this).attr("id");
    var userId = cardId.replace("card-of-user","");
    var conversationId = $(this).closest('.conversation_id').attr('id');
    showMessage(userId, conversationId);
});

$(document).on("click", ".friend", function(){
    var cardId = $(this).attr("id");
    var userId2 = cardId.replace("card-friend","");
    var userId1 = $("#userId").text();
    var newConversation = {
    }
    $.ajax({
        url: 'http://localhost:1999/api/conversation/?member1='+userId1+'&member2='+userId2,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newConversation),
        success: function(data, conversationId) {
            saveConversation(userId1, userId2, data.conversationId);
            conversationId = data.conservationId;
            alert("succeed");
        },
        error: function() {
            alert("error");
        }
    })
});

$(document).on("click", ".btn-send", function(){
    var user1 = $("#userId").text();
    var conversationId = $(".conversation").attr("id");
    console.log(conversationId);
    conversationId = conversationId.replace("conId","");
    conversationId = parseInt(conversationId);
    var message = $("#chat-area").val();
    var now = new Date();
    var createdAt = now.toISOString();
    if (message!="") {
        $("#chat-area").val("");
        saveMessage(conversationId, message, createdAt, user1);
    }
});