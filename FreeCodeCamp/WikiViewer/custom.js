$(document).ready(function() {
    $('#searchBox').on('input',function(e){
         $("#contentContainer").empty();
        wikiAPICall($("#searchBox").val());
    });
});

function wikiAPICall(searchItem) {
    //API CALL to wikipedia
    $.ajax({
        url: 'https://en.wikipedia.org/w/api.php',
        data: {
            origin: '*',
            action: 'opensearch',
            meta: 'userinfo',
            format: 'json',
            limit: '10',
            search: searchItem
        },
        xhrFields: {
            withCredentials: false
        },
        dataType: 'json'
    }).done(function(data) {
        //Pass titles, descriptions and links
        displayResults(data);
    });
}

function displayResults(results) {
    var titlesArray = results[1];
    var descArray = results[2];
    var linksArray = results[3];
    for(var i=0; i < titlesArray.length; i++){
    $('#contentContainer').append(
        $('<div/>')
        .attr({
            'id':'title' + i.toString(),
            'align': 'center'
        })
        .addClass('title')
        .html("<a href=" + linksArray[i] + ">" + titlesArray[i] + "</a>")
    );
    $("#title" + i.toString()).show('normal').css("display", "block");
    $('#title'+i.toString()).append(
        $('<div/>')
        .attr('id','desc' + i.toString())
        .addClass('desc')
        .text(descArray[i])
    );
    }
}

