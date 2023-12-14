import { initializeApp } from "firebase/app";
import { getDatabase, ref } from "firebase/database";


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
  
  // Initialize Firebase
  const app = initializeApp(firebaseConfig);
  const db = getDatabase();

function addProfileUser(data) {
    $(".user-left").append('<div class="user-group"></div>')
    for (var p_user of data) {
        $(".user-group").append(`<div class="card mb-3 card-profile" id="card-of-user`+p_user.userId+`" style="max-width: 540px;">
                                <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="" class="img-fluid rounded-start" alt="...">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                    <h5 class="card-title">`+p_user.firstName + ' ' + p_user.lastName+`</h5>
                                    <p class="card-text"><small class="text-body-secondary">Message</small></p>
                                    <p class="card-text"><small class="text-body-secondary">Last updated 3 mins ago</small></p>
                                    </div>
                                </div>
                                </div>
                                </div>`)
    }
}

function fetchAPI() {   
    var userId = $("#userId").text();
    $.ajax({
        url: "http://localhost:1999/api/profile/"+userId+"/message",
        type: "GET",
        success: function(data) {
            addProfileUser(data);
        },
        error: function() {
            alert('faild')
        }
    })
    console.log(userId);
}

function showMessage(userId){
    $.ajax({
        url: "http://localhost:1999/api/profile/"+userId,
        type: "GET",
        success: function(data) {
            var name = data.firstName+" "+data.lastName;
            $(".chat-area").show(); 
            $(".profile-name").text(name);
        }
    })
}

$(document).ready(function () {
    fetchAPI();
    $(".chat-area").hide(); 
    $(".btn-group-vertical").remove();
});

$(document).on("click", ".card-profile", function(){
    var cardId = $(this).attr("id");
    var userId = cardId.replace("card-of-user","");
    showMessage(userId);
});