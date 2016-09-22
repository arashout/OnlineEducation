var arrStreamers = ['TeamSp00ky', 'KingBlazey', 'randombreh']
var baseURL = 'https://api.twitch.tv/kraken/streams/';
var HEADER = "application/vnd.twitchtv.v3+json";
var clientSecret = "tdvq6lkqhy93eh4qwnwuzvhhycrd28y";
$(document).ready(function(){
    for(var i = 0; i < arrStreamers.length; i++){
        getResults(arrStreamers[i]);
    }
    $("#searchBox").keyup(function(event){
    if(event.keyCode == 13){
        getResults($("#searchBox").val())
    }
    });
    $('deleteBtn').click(function(e){
        console.log("Clicked");
    });
});
function getResults(streamer){
    $.ajax({
        url: baseURL + streamer,
        type: "GET",
        dataType: 'json',
        headers: {"Accept": HEADER,
                 "Client-ID": clientSecret},
        success: function (data) {
                    displayResults(data.stream, streamer);
                    },
        error: function (error) {
            accountClosed(streamer);
        }
    });
}
function accountClosed(streamer){
    var channelStatus = "Account Closed";
    var channelURL = "#";
    var userStatus = "accountClosed";
    appendToDivs(streamer, channelURL, channelStatus, userStatus);
}
function displayResults(streamObject, streamer){
    var channelStatus;
    var channelURL;
    var userStatus;
    if( streamObject == null){
        channelURL = "#";
        channelStatus = "Offline";
        userStatus = "offline";
    }
    else{
        channelURL = streamObject.channel.url;
        channelStatus = streamObject.channel.status;
        userStatus = "online";
    }
    appendToDivs(streamer, channelURL, channelStatus, userStatus);

}
function appendToDivs(streamerName, url, status, userStatus){
    $('#streamerContainer').append(
        $('<div/>')
        .attr({
            'id':streamerName,
            'align': 'center'
        })
        .addClass('streamerName '+ userStatus)
        .html("<a href=" + url + ">" + streamerName + "</a>")
    );
    $("#" + streamerName).show('normal').css("display", "block");
    $('#' + streamerName).append(
            $('<div/>')
        .addClass('status')
        .text(status)
    );
}